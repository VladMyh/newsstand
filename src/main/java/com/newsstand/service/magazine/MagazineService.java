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
     * @param categoryId If of the category to filter magazines.
     * @return           A list of magazines.
     */
    List<Magazine> getPageByCategoryId(Long page, Long size, Long categoryId);
}
