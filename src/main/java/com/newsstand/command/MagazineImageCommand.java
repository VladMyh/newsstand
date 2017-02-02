package com.newsstand.command;

import com.newsstand.properties.MappingProperties;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is used to handle GET requests to retrieve magazine image.
 */
public class MagazineImageCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(MagazineImageCommand.class);

	private static MagazineService magazineService;

	private static String errorpage;

	public MagazineImageCommand(){
		LOGGER.info("Initializing MagazineImageCommand");

		magazineService = MagazineServiceImpl.getInstance();

		MappingProperties properties = MappingProperties.getInstance();
		errorpage = properties.getProperty("error404Page");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");

		String resultPage = errorpage;

		if(request.getParameter("id") != null) {
			try {
				Long id = Long.parseLong(request.getParameter("id"));

				byte[] image =  magazineService.findImageByMagazineId(id);

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
