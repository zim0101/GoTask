package com.go_task.dao;

import com.go_task.database.HibernateUtil;
import com.go_task.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class UserDao extends BaseDaoImpl <User> {

    public UserDao(Class<User> entity) {
        super(entity);
    }

//    public User getUserByEmail(String email) {
//        User data = null;
//
//        try (Session session = HibernateUtil.getSession()) {
//            String sql = "select U.id, U.name from com.go_task.entity.User as U where U.email = :email";
//            Query query = session.createQuery(sql, User.class);
//            query.setParameter("email", email);
//            data = (User) query.uniqueResult();
//        } catch (Exception exception) {
//            exception.getStackTrace();
//        }
//
//        return data;
//    }

    public static void main(String[] args) {
//        UserDao userDao = new UserDao(User.class);
//
////        User user1 = new User(
////                "Farhat Shahir",
////                "user@email.com",
////                "password"
////        );
//
//        User user = (User) userDao.find(2);
//        System.out.println(user);
//
        User data = null;
//
        System.out.println("Here");
        try (Session session = HibernateUtil.getSession()) {
            String sql = "select id, name from users  where id = 1";
            System.out.println(sql);
            Query query = session.createQuery(sql, User.class);
            System.out.println(query);
//            query.setParameter("email", 1);
            data = (User) query.uniqueResult();
        } catch (Exception exception) {
            exception.getStackTrace();
        }
        System.out.println(data);
    }
}
