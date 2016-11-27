package com.newsstand.dao;

import com.newsstand.model.user.User;

import java.io.Serializable;

public interface GenericDao<T, ID extends Serializable> {
    void saveOrUpdate(T obj);
    void deleteById(ID id);
    User getEntityById(ID id);
}
