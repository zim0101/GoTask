package com.go_task.database;

import com.go_task.entity.Task;
import com.go_task.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        try {
            loadSessionFactory();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure("db/hibernate.cfg.xml");
        configuration.addAnnotatedClass(Task.class);
        configuration.addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistration =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                        .build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistration);
    }

    public static Session getSession() throws HibernateException {

        return sessionFactory.openSession();
    }
}
