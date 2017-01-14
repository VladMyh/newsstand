package com.newsstand.dao.magazine;

import com.newsstand.model.magazine.Magazine;

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
     */
    void deleteMagazineById(Long id);

    /**
     * This method finds magazine by id.
     *
     * @param id Id of the magazine to find.
     * @return   Magazine object.
     */
    Magazine findMagazineById(Long id);
}