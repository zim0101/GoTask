package com.go_task.database;


import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public interface HibernateSessionContext {

    default <T> List<T> readContext(Function<Session, List<T>> function) {
        List<T> data = null;

        try (Session session = HibernateUtil.getSession()) {
            data = function.apply(session);
        } catch (Exception exception) {
            exception.getStackTrace();
        }

        return data;
    }

    default Object singleReadContext(Function<Session, Object> function) {
        Object data = null;

        try (Session session = HibernateUtil.getSession()) {
            data = function.apply(session);
        } catch (Exception exception) {
            exception.getStackTrace();
        }

        return data;
    }

    default void writeContext(Consumer<Session> consumer) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) transaction.rollback();
            exception.getStackTrace();
        }
    }
}
