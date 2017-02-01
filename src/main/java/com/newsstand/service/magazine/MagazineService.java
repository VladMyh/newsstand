package com.newsstand.service.magazine;

import com.newsstand.model.magazine.Magazine;

import java.util.List;

public interface MagazineService {
    /**
     * This method finds magazine by id.
     *
     * @param id Id of the magazine to find.
     * @return   Magazine object, null if nothing is found.
     */
    Magazine findMagazineById(Long id);

    /**
     * This method returns n latest added magazines.
     *
     * @param limit How much magazines to find.
     * @return      A list of magazines.
     */
    List<Magazine> findLatestAdded(Integer limit);

    /**
     * This method returns a page of magazines with category id.
     *
     * @param page       Number of the page, starts from 1.
     * @param size       Size of the page.
     * @param categoryId Id of the category to filter magazines.
     * @return           A list of magazines.
     */
    List<Magazine> getPageByCategoryId(Long page, Long size, Long categoryId);

    /**
     * This method returns a page of magazines with publisher id.
     *
     * @param page        Number of the page, starts from 1.
     * @param size        Size of the page.
     * @param publisherId Id of the publisher to filter magazines.
     * @return            A list of magazines.
     */
    List<Magazine> getPageByPublisherId(Long page, Long size, Long publisherId);

    /**
     * This method returns a page of all magazines.
     *
     * @param page Number of the page, starts from 1.
     * @param size Size of the page.
     * @return     A list of magazines.
     */
    List<Magazine> getPage(Long page, Long size);

    /**
     * This method creates new magazine.
     *
     * @param magazine Magazine object to create.
     * @return         Updated object
     */
    Magazine createMagazine(Magazine magazine);

    /**
     * This method updates magazine.
     *
     * @param magazine Magazine object to update.
     * @return         Updated object.
     */
    Magazine updateMagazine(Magazine magazine);

    /**
     * This method deletes magazine.
     *
     * @param id Id of the magazine to delete.
     * @return   True if magazine deleted successfully, otherwise false.
     */
    boolean deleteMagazineById(Long id);
}
