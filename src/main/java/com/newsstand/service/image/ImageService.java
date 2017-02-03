package com.newsstand.service.image;

import java.io.InputStream;

public interface ImageService {
	/**
	 * This method finds image by id.
	 *
	 * @param id Id of the image to find.
	 * @return   Image bytes.
	 */
	byte[] findImageById(Long id);

	/**
	 * This method crates new image.
	 *
	 * @param image Image stream.
	 * @return      Created image id.
	 */
	Long createImage(InputStream image);
}
