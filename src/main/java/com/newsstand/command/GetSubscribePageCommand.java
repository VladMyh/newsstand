package com.newsstand.command;

import com.newsstand.dao.image.MysqlImageDaoImpl;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
import com.newsstand.dao.subscription.MysqlSubscriptionDaoImpl;
import com.newsstand.dao.subscription.MysqlSubscriptionTypeDao;
import com.newsstand.dao.user.MysqlUserDaoImpl;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.service.subscription.SubscriptionService;
import com.newsstand.service.subscription.SubscriptionServiceImpl;
import com.newsstand.service.subscription.SubscriptionTypeService;
import com.newsstand.service.subscription.SubscriptionTypeServiceImpl;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the subscription page.
 */
public class GetSubscribePageCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(GetSubscribePageCommand.class);

    private static MagazineService magazineService;
    private static SubscriptionTypeService subscriptionTypeService;
    private static SubscriptionService subscriptionService;
    private static UserService userService;

    private static String subscribePage;
    private static String mainPage;
    private static String loginPage;
    private static String subscriptionSuccessPage;

    public GetSubscribePageCommand(){
        LOGGER.info("Initializing GetSubscribePageCommand");

        magazineService = new MagazineServiceImpl(MysqlMagazineDaoImpl.getInstance(),
                                                  MysqlImageDaoImpl.getInstance());
        subscriptionTypeService = new SubscriptionTypeServiceImpl(MysqlSubscriptionTypeDao.getInstance());
        subscriptionService = new SubscriptionServiceImpl(MysqlSubscriptionDaoImpl.getInstance());
        userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        subscribePage = properties.getProperty("subscribePage");
        mainPage = properties.getProperty("mainPage");
        loginPage = properties.getProperty("loginPage");
        subscriptionSuccessPage = properties.getProperty("subscriptionSuccessPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = mainPage;

        if(request.getSession().getAttribute("authenticated") == null &&
           request.getSession().getAttribute("authenticated").equals(true)) {
            resultPage = loginPage;
        }
        else if(request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                Magazine magazine = magazineService.findMagazineById(id);

                if(magazine != null) {
                    request.setAttribute("magazine", magazine);
                    request.setAttribute("subscriptionTypes", subscriptionTypeService.findAll());
                    resultPage = subscribePage;
                }
                else {
                    LOGGER.info("Magazine with id " + id + " doesn't exist");
                }
            }
            catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
            }
        }

        return resultPage;
    }
}
