package com.newsstand.dao.magazine;

import com.newsstand.model.magazine.Magazine;

import java.util.List;

public interface MagazineDao {
    /**
     * This method creates new magazine.
     *
     * @param magazine Object to be created.
     * @return         An updated object.
     */
    Magazine createMagazine(Magazine magazine);

    /**
     * This method updates magazine.
     *
     * @param magazine Object to be updated in db.
     * @return         An updated object.
     */
    Magazine updateMagazine(Magazine magazine);

    /**
     * This method deleted magazine by id.
     *
     * @param id Id of the magazine to be deleted.
     * @return   True if deletion successful, otherwise false.
     */
    boolean deleteMagazineById(Long id);

    /**
     * This method finds magazine by id.
     *
     * @param id Id of the magazine to find.
     * @return   Magazine object.
     */
    Magazine findMagazineById(Long id);

    /**
     * This method finds latest added magazines.
     *
     * @param limit How much to find.
     * @return      A list of magazines.
     */
    List<Magazine> findLastNMagazines(Integer limit);

    /**
     * This method finds a page of magazines by category.
     *
     * @param categoryId Id of the category of magazines to find
     * @param offset     Element to start from.
     * @param size       How much elements to take.
     * @return           List of magazines.
     */
    List<Magazine> findPageByCategory(Long categoryId, Integer offset, Integer size);

    /**
     * This method finds a page of magazines by publisher.
     *
     * @param publisherId Id of the publisher of magazines to find
     * @param offset      Element to start from.
     * @param size        How much elements to take.
     * @return            List of magazines.
     */
    List<Magazine> findPageByPublisher(Long publisherId, Integer offset, Integer size);

    /**
     * This methods finds page from all magazines
     *
     * @param offset Element to start from.
     * @param size   How much elements to take.
     * @return       List of magazines.
     */
    List<Magazine> findPage(Integer offset, Integer size);
}
