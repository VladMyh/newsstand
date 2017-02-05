package com.newsstand.service.magazine;

import com.newsstand.dao.image.ImageDao;
import com.newsstand.dao.image.MysqlImageDaoImpl;
import com.newsstand.dao.magazine.MagazineDao;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.util.Page;
import org.apache.log4j.Logger;

import java.util.List;

public class MagazineServiceImpl implements MagazineService {

    private static final Logger LOGGER = Logger.getLogger(MagazineServiceImpl.class);

    private MagazineDao magazineDao;
    private ImageDao imageDao;

    public MagazineServiceImpl(MagazineDao magazineDao, ImageDao imageDao) {
        LOGGER.info("Initializing MagazineServiceImpl");

        this.magazineDao = magazineDao;
        this.imageDao = imageDao;
    }

    @Override
    public Magazine findMagazineById(Long id) {
        LOGGER.info("Finding magazine by id " + id);

        if(id == null) {
            return null;
        }

        return magazineDao.findMagazineById(id);
    }

    @Override
    public List<Magazine> findLatestAdded(Integer limit) {
        LOGGER.info("Finding " + limit + " latest magazines");

        if(limit == null) {
            return null;
        }

        return magazineDao.findLastNMagazines(limit);
    }

    @Override
    public Page<Magazine> getPageByCategoryId(Integer page, Integer size, Long categoryId) {
        LOGGER.info("Getting page number " + page + ", of size " + size + ", for category id " + categoryId);

        if(page == null || size == null || categoryId == null || page < 1 || size < 1) {
            return null;
        }

        List<Magazine> items = magazineDao.findPageByCategory(categoryId, (page - 1) * size, size);
        return new Page<>(items, page, size);
    }

    @Override
    public Page<Magazine> getPageByPublisherId(Integer page, Integer size, Long publisherId) {
        LOGGER.info("Getting page number " + page + ", of size " + size + ", for publisher id " + publisherId);

        if(page == null || size == null || publisherId == null || page < 1 || size < 1) {
            return null;
        }

        List<Magazine> items = magazineDao.findPageByPublisher(publisherId, (page - 1) * size, size);
        return new Page<>(items, page, size);
    }

    @Override
    public Page<Magazine> getPage(Integer page, Integer size) {
        LOGGER.info("Getting page number " + page + ", of size " + size );

        if(page == null || size == null || page < 1 || size < 1) {
            return null;
        }

        List<Magazine> items = magazineDao.findPage((page - 1) * size, size);
        return new Page<>(items, page, size);
    }

    @Override
    public Magazine createMagazine(Magazine magazine) {
        LOGGER.info("Creating new magazine");

        if(magazine == null) {
            return null;
        }

        return magazineDao.createMagazine(magazine);
    }

    @Override
    public Magazine updateMagazine(Magazine magazine) {
        LOGGER.info("Updating magazine");

        if(magazine == null) {
            return null;
        }

        return magazineDao.updateMagazine(magazine);
    }

    @Override
    public boolean deleteMagazineById(Long id) {
        LOGGER.info("Deleting magazine by id " + id);

        if(id == null) {
            return false;
        }

        Magazine magazine = magazineDao.findMagazineById(id);

        if(magazine != null && magazine.getImageId() != null) {
            imageDao.deleteImageById(magazine.getImageId());
        }

        return magazineDao.deleteMagazineById(id);
    }

    @Override
    public Page<Magazine> getPageByName(String query, Integer page, Integer size) {
        LOGGER.info("Getting page by query " + query + " number " + page + ", of size " + size );

        if(page == null || size == null || query == null || page < 1 || size < 1) {
            return null;
        }

        List<Magazine> items = magazineDao.findPageByNameQuery(query, (page - 1) * size, size);
        return new Page<>(items, page, size);
    }
}
