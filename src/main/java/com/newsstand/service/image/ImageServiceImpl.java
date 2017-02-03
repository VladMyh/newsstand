package com.newsstand.service.image;

import com.newsstand.dao.image.ImageDao;
import com.newsstand.dao.image.MysqlImageDaoImpl;
import org.apache.log4j.Logger;

import java.io.InputStream;

public class ImageServiceImpl implements ImageService {

	private static final Logger LOGGER = Logger.getLogger(ImageServiceImpl.class);

	private static ImageServiceImpl INSTANCE;
	private static ImageDao imageDao;

	private ImageServiceImpl() {
		LOGGER.info("Initializing ImageServiceImpl");

		imageDao = MysqlImageDaoImpl.getInstance();
	}

	public static ImageServiceImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new ImageServiceImpl();
		}
		return INSTANCE;
	}

	@Override
	public byte[] findImageById(Long id) {
		LOGGER.info("Finding magazine image by id " + id);

		return imageDao.findImageById(id);
	}

	@Override
	public Long createImage(InputStream image) {
		LOGGER.info("Creating new image");

		return imageDao.createImage(image);
	}
}
