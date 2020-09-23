package com.go_task.dao;


import com.go_task.database.HibernateSessionContext;
import java.util.List;


public interface BaseDao<T> extends HibernateSessionContext {

    List<T> all();

    Object find(int id);

    void create(Object object);

    void update(Object object);

    void createOrUpdate(Object todo);

    void delete(Object todo);

    void remove(int id);
}
