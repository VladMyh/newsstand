package com.newsstand.service.magazine;

import com.newsstand.dao.magazine.MagazineDao;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
import com.newsstand.model.magazine.Magazine;
import org.apache.log4j.Logger;

import java.util.List;

public class MagazineServiceImpl implements MagazineService {

    private static final Logger LOGGER = Logger.getLogger(MagazineServiceImpl.class);

    private static MagazineServiceImpl INSTANCE;
    private static MagazineDao magazineDao;

    private MagazineServiceImpl() {
        LOGGER.info("Initializing MagazineServiceImpl");

        magazineDao = MysqlMagazineDaoImpl.getInstance();
    }

    public static MagazineServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MagazineServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public Magazine findMagazineById(Long id) {
        LOGGER.info("Finding magazine by id " + id);
        return magazineDao.findMagazineById(id);
    }

    @Override
    public List<Magazine> findLatestAdded(Integer limit) {
        LOGGER.info("Finding " + limit + " latest magazines");
        return magazineDao.findLastNMagazines(limit);
    }
}
