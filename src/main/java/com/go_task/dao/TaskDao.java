package com.go_task.dao;

import com.go_task.database.HibernateUtil;
import com.go_task.entity.Task;
import com.go_task.utils.enums.Priority;
import com.go_task.utils.enums.TaskStatus;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TaskDao extends BaseDaoImpl <Task> {

    public TaskDao(Class<Task> entity) {
        super(entity);
    }

    public static void main(String[] args) {

        TaskDao taskDao = new TaskDao(Task.class);
                Task task = new Task(
                "String title",
                "String description",
                1,
                Priority.REGULAR
        );

        taskDao.create(task);

        List<Task> data = null;

        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
            Root<Task> rootEntry = criteriaQuery.from(Task.class);
            CriteriaQuery<Task> objects = criteriaQuery.select(rootEntry);

            data = session.createQuery(objects).getResultList();
        } catch (Exception exception) {
            exception.getStackTrace();
        }

        System.out.println(data);

    }
}
