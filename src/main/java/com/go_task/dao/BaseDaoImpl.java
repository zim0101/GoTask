package com.go_task.dao;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public abstract class BaseDaoImpl<T> implements BaseDao <T> {

    public Class<T> entity;

    public BaseDaoImpl(Class<T> entity) {
        this.entity = entity;
    }

    public List<T> all() {
        return readContext((session) -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
            Root<T> rootEntry = criteriaQuery.from(entity);
            CriteriaQuery<T> objects = criteriaQuery.select(rootEntry);

            return session.createQuery(objects).getResultList();
        });
    }

    public Object find(int id) {
        return singleReadContext(session -> session.get(entity, id));
    }

    public void create(Object object) {
        writeContext((session -> session.save(object)));
    }

    public void update(Object object) {
        writeContext((session -> session.update(object)));
    }

    public void createOrUpdate(Object object) {
        writeContext((session -> session.saveOrUpdate(object)));
    }

    public void delete(Object object) {
        writeContext((session -> session.delete(object)));
    }

    public void remove(int id) {
        writeContext(session -> {
            T object = session.get(entity, id);
            session.remove(object);
        });
    }
}
