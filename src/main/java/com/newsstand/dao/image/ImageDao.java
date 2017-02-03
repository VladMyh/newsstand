package com.newsstand.dao.image;

import java.io.InputStream;

public interface ImageDao {

	/**
	 * This method finds image by id.
	 *
	 * @param id Id of the image to find.
	 * @return   Image bytes.
	 */
	byte[] findImageById(Long id);

	/**
	 * This method creates new image.
	 *
	 * @param inputStream Image stream.
	 * @return            Id of inserted image.
	 */
	Long createImage(InputStream inputStream);

	/**
	 * This method deletes image by id.
	 *
	 * @param id Id of the image to delete.
	 * @return   True if deletion was successful, otherwise false.
	 */
	boolean deleteImageById(Long id);
}
