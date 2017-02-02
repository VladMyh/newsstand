package com.newsstand.service.magazine;

import com.newsstand.dao.magazine.MagazineDao;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
import com.newsstand.model.magazine.Magazine;
import org.apache.log4j.Logger;

import java.util.List;

public class MagazineServiceImpl implements MagazineService {

    private static final Logger LOGGER = Logger.getLogger(MagazineServiceImpl.class);

    private static MagazineServiceImpl INSTANCE;
    private static MagazineDao magazineDao;

    private MagazineServiceImpl() {
        LOGGER.info("Initializing MagazineServiceImpl");

        magazineDao = MysqlMagazineDaoImpl.getInstance();
    }

    public static MagazineServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MagazineServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public Magazine findMagazineById(Long id) {
        LOGGER.info("Finding magazine by id " + id);

        return magazineDao.findMagazineById(id);
    }

    @Override
    public List<Magazine> findLatestAdded(Integer limit) {
        LOGGER.info("Finding " + limit + " latest magazines");

        return magazineDao.findLastNMagazines(limit);
    }

    @Override
    public List<Magazine> getPageByCategoryId(Integer page, Integer size, Long categoryId) {
        LOGGER.info("Getting page number " + page + ", of size " + size + ", for category id " + categoryId);

        return magazineDao.findPageByCategory(categoryId, (page - 1) * size, size);
    }

    @Override
    public List<Magazine> getPageByPublisherId(Integer page, Integer size, Long publisherId) {
        LOGGER.info("Getting page number " + page + ", of size " + size + ", for publisher id " + publisherId);

        return magazineDao.findPageByPublisher(publisherId, (page - 1) * size, size);
    }

    @Override
    public List<Magazine> getPage(Integer page, Integer size) {
        LOGGER.info("Getting page number " + page + ", of size " + size );

        return magazineDao.findPage((page - 1) * size, size);
    }

    @Override
    public Magazine createMagazine(Magazine magazine) {
        LOGGER.info("Creating new magazine");

        return magazineDao.createMagazine(magazine);
    }

    @Override
    public Magazine updateMagazine(Magazine magazine) {
        LOGGER.info("Updating magazine");

        return magazineDao.updateMagazine(magazine);
    }

    @Override
    public boolean deleteMagazineById(Long id) {
        LOGGER.info("Deleting magazine by id " + id);

        return magazineDao.deleteMagazineById(id);
    }

    @Override
    public byte[] findImageByMagazineId(Long id) {
        LOGGER.info("Finding magazine image by id " + id);

        return magazineDao.findImageById(id);
    }
}
