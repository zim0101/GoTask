package com.go_task.dao;

import com.go_task.entity.Task;

public class TaskDao extends BaseDaoImpl <Task> {

    public TaskDao(Class<Task> entity) {
        super(entity);
    }
}
