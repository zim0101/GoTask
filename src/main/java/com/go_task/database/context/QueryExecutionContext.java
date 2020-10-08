package com.go_task.database.context;


import com.go_task.database.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.NoResultException;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;
import java.util.function.Function;


public abstract class QueryExecutionContext <T> {

    public Class<T> entity;
    public String tableName;

    public QueryExecutionContext(Class<T> entity) {
        this.entity = entity;
        this.tableName = entity.getAnnotation(Table.class).name();
    }

    public <U> U criteriaContext(CriteriaContextRunner<Session,
                                                       Root<T>,
                                                       CriteriaQuery<T>,
                                                       CriteriaBuilder, U> runner) {
        U data = null;
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.sessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
            Root<T> root = criteriaQuery.from(entity);
            data = runner.apply(session, root, criteriaQuery, criteriaBuilder);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) transaction.rollback();
            exception.getStackTrace();
        }

        return data;
    }

    public <U> U criteriaBuilderContext(CriteriaBuilderContextRunner<Session,
                                                                     CriteriaBuilder,
                                                                     U> runner) {
        U data = null;
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.sessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            data = runner.apply(session, criteriaBuilder);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) transaction.rollback();
            exception.getStackTrace();
        }

        return data;
    }

    public void noReturnContext(Consumer<Session> consumer) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.sessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) transaction.rollback();
            exception.getStackTrace();
        }
    }

    public <U> U context(Function<Session, U> function) {
        U object = null;
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.sessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            object = function.apply(session);
            transaction.commit();
        } catch (NoResultException exception) {
            if (transaction != null) transaction.rollback();
            return object;
        } catch (HibernateException exception) {
            if (transaction != null) transaction.rollback();
            exception.getStackTrace();
        }

        return object;
    }
}
