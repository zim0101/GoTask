package com.go_task.database.utils;


import com.go_task.config.ApplicationConfig;
import com.go_task.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            loadSessionFactory();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadSessionFactory(){
        Configuration configuration = new Configuration()
                .configure(ApplicationConfig.getHibernateXMLPath())
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Task.class)
                .addAnnotatedClass(UserDetails.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(UserProject.class);

        ServiceRegistry serviceRegistration =
                new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistration);
    }

    public static SessionFactory sessionFactory() throws HibernateException {

        return sessionFactory;
    }
}
