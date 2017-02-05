package com.newsstand.service.image;

import com.newsstand.dao.image.ImageDao;
import com.newsstand.dao.image.MysqlImageDaoImpl;
import org.apache.log4j.Logger;

import java.io.InputStream;

public class ImageServiceImpl implements ImageService {

	private static final Logger LOGGER = Logger.getLogger(ImageServiceImpl.class);

	private ImageDao imageDao;

	public ImageServiceImpl(ImageDao imageDao) {
		LOGGER.info("Initializing ImageServiceImpl");

		this.imageDao = imageDao;
	}

	@Override
	public byte[] findImageById(Long id) {
		LOGGER.info("Finding magazine image by id " + id);

		if(id == null) {
			return null;
		}

		return imageDao.findImageById(id);
	}

	@Override
	public Long createImage(InputStream image) {
		LOGGER.info("Creating new image");

		if(image == null) {
			return null;
		}

		return imageDao.createImage(image);
	}

	@Override
	public boolean deleteImageById(Long id) {
		LOGGER.info("Deleting image by id " + id);

		if(id == null) {
			return false;
		}

		return imageDao.deleteImageById(id);
	}
}
