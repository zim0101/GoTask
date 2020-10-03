package com.go_task.dao;

import com.go_task.entity.User;
import com.go_task.entity.UserDetails;

public class UserDetailsDao extends BaseDaoImpl <UserDetails>{

    public UserDetailsDao(Class<UserDetails> entity) {
        super(entity);
    }

    public int createDetailsOfUser(int userId, UserDetails userDetails) {
        return (int) context(session -> {
            User user = session.get(User.class, userId);
            userDetails.setUser(user);
            return session.save(userDetails);
        });
    }
}
