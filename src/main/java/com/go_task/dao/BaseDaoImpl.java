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
            CriteriaQuery<T> todos = criteriaQuery.select(rootEntry);

            return session.createQuery(todos).getResultList();
        });
    }

    public Object find(int id) {
        return singleReadContext(session -> session.get(entity, id));
    }

    public void create(Object todo) {
        writeContext((session -> session.save(todo)));
    }

    public void update(Object todo) {
        writeContext((session -> session.update(todo)));
    }

    public void createOrUpdate(Object todo) {
        writeContext((session -> session.saveOrUpdate(todo)));
    }

    public void delete(Object todo) {
        writeContext((session -> session.delete(todo)));
    }

    public void remove(int id) {
        writeContext(session -> {
            T todo = session.get(entity, id);
            session.remove(todo);
        });
    }
}
