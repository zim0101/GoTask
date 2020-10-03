package com.go_task.dao;

import java.util.List;

public interface BaseDao<T> {

    List<T> all();

    Object find(int id);

    int create(Object object);

    void update(Object object);

    void createOrUpdate(Object todo);

    void delete(Object todo);

    void remove(int id);
}
