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
}
