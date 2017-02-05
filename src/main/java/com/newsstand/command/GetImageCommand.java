package com.newsstand.command;

import com.newsstand.dao.image.MysqlImageDaoImpl;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.image.ImageService;
import com.newsstand.service.image.ImageServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is used to handle GET requests to retrieve images.
 */
public class GetImageCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(GetImageCommand.class);

	private static ImageService imageService;

	private static String errorpage;

	public GetImageCommand(){
		LOGGER.info("Initializing GetImageCommand");

		imageService = new ImageServiceImpl(MysqlImageDaoImpl.getInstance());

		MappingProperties properties = MappingProperties.getInstance();
		errorpage = properties.getProperty("error404Page");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");

		String resultPage = errorpage;

		if(request.getParameter("id") != null) {
			try {
				Long id = Long.parseLong(request.getParameter("id"));

				byte[] image =  imageService.findImageById(id);

				if(image != null && image.length != 0) {
					response.setContentType("image/png");
					response.setContentLength(image.length);
					response.getOutputStream().write(image);
				}
			}
			catch (NumberFormatException ex) {
				LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
			} catch (IOException e) {
				LOGGER.info(e.getMessage());
			}

		}

		return resultPage;
	}
}
