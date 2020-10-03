package com.go_task.dao;


import com.go_task.entity.User;
import com.go_task.entity.User_;
import javax.persistence.criteria.*;


public class UserDao extends BaseDaoImpl <User> {

    public UserDao(Class<User> entity) {
        super(entity);
    }

    public User getUserByEmail(String email) {
        return criteriaContext((session, root, criteria, builder) -> {
            Path<String> columnValue = root.get(User_.email);
            Predicate condition = builder.equal(columnValue, email);
            criteria.select(root).where(condition);

            return session.createQuery(criteria).getSingleResult();
        });
    }
}
