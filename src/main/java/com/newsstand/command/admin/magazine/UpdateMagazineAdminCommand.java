package com.newsstand.command.admin.magazine;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.magazine.Category;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.magazine.Publisher;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.image.ImageService;
import com.newsstand.service.image.ImageServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.service.publisher.PublisherService;
import com.newsstand.service.publisher.PublisherServiceImpl;
import com.newsstand.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class is used to handle POST requests to update magazine.
 */
public class UpdateMagazineAdminCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(UpdateMagazineAdminCommand.class);

	private static MagazineService magazineService;
	private static PublisherService publisherService;
	private static CategoryService categoryService;
	private static ImageService imageService;

	private static String magazinesPage;
	private static String loginPage;

	public UpdateMagazineAdminCommand(){
		LOGGER.info("Initializing UpdateMagazineAdminCommand");

		magazineService = MagazineServiceImpl.getInstance();
		publisherService = PublisherServiceImpl.getInstance();
		categoryService = CategoryServiceImpl.getInstance();
		imageService = ImageServiceImpl.getInstance();

		MappingProperties properties = MappingProperties.getInstance();
		magazinesPage = properties.getProperty("adminMagazinesPage");
		loginPage = properties.getProperty("loginPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = magazinesPage;

		if(request.getSession().getAttribute("authenticated") != null &&
				request.getSession().getAttribute("authenticated").equals(true) &&
				!request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
			LOGGER.info("User not authorized");
			resultPage = loginPage;
		}
		else if(request.getParameter("id") != null && request.getParameter("title") != null &&
				request.getParameter("quantity") != null && request.getParameter("price") != null &&
				request.getParameter("publisher") != null && request.getParameter("category") != null &&
				request.getParameter("description") != null) {
			try {
				Long id = Long.parseLong(request.getParameter("id"));
				Magazine oldMagazine = magazineService.findMagazineById(id);

				//get category
				Category category = new Category();
				category.setId(Long.parseLong(request.getParameter("category")));

				//get publisher
				Publisher publisher = new Publisher();
				publisher.setId(Long.parseLong(request.getParameter("publisher")));

				//get image
				Part filePart = request.getPart("image");
				Long imageId = null;

				if(filePart != null && !Paths.get(filePart.getSubmittedFileName()).getFileName().toString().isEmpty()) {
					InputStream image = filePart.getInputStream();
					imageId = imageService.createImage(image);

					//delete old image
					if(oldMagazine.getImageId() != null) {
						imageService.deleteImageById(oldMagazine.getImageId());
					}
				}

				Magazine magazine = new Magazine();
				magazine.setId(id);
				magazine.setTitle(request.getParameter("title"));
				magazine.setDescription(request.getParameter("description"));
				magazine.setPrice(Float.parseFloat(request.getParameter("price")));
				magazine.setQuantity(Long.parseLong(request.getParameter("quantity")));
				magazine.setCategory(category);
				magazine.setPublisher(publisher);
				magazine.setImageId(imageId == null ? oldMagazine.getImageId() : imageId);

				magazineService.updateMagazine(magazine);

				request.setAttribute("updateSuccess", true);

				List<Magazine> items = magazineService.getPage(1, 10);
				Page<Magazine> page = new Page<>(items, 1, 10);
				request.setAttribute("page", page);


				resultPage = magazinesPage;
			}
			catch (NumberFormatException ex) {
				LOGGER.info("Couldn't parse " + request.getParameter("id")
						                      + ", "
						                      + request.getParameter("category")
						                      + ", "
						                      + request.getParameter("publisher")
						                      + " to long");
			} catch (ServletException | IOException ex) {
				LOGGER.error(ex.getMessage());
			}

		}

		return resultPage;
	}
}
