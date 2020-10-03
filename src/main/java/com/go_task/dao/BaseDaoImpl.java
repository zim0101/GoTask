package com.go_task.dao;


import com.go_task.database.context.QueryExecutionContext;
import java.util.List;


public abstract class BaseDaoImpl<T> extends QueryExecutionContext <T> implements BaseDao <T> {

    public BaseDaoImpl(Class<T> entity) {
        super(entity);
    }

    public List<T> all() {
        return criteriaContext((session, root, criteria, builder) -> {
            criteria.select(root);
            return session.createQuery(criteria).getResultList();
        });
    }

    public Object find(int id) {
        return context(session -> session.get(entity, id));
    }

    public int create(Object object) {
        return context((session -> (Integer) session.save(object)));
    }

    public void update(Object object) {
        noReturnContext((session -> session.update(object)));
    }

    public void createOrUpdate(Object object) {
        noReturnContext((session -> session.saveOrUpdate(object)));
    }

    public void delete(Object object) {
        noReturnContext((session -> session.delete(object)));
    }

    public void remove(int id) {
        noReturnContext(session -> {
            T object = session.get(entity, id);
            session.remove(object);
        });
    }
}
