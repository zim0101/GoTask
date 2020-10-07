package com.go_task.dto;

import com.go_task.utils.enums.TaskStatus;

public class TotalTaskByStatusDto {
    private TaskStatus status;
    private long totalTasks;

    public TotalTaskByStatusDto(TaskStatus status, long totalTasks) {
        this.status = status;
        this.totalTasks = totalTasks;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(long totalTasks) {
        this.totalTasks = totalTasks;
    }

    @Override
    public String toString() {
        return "TotalTaskByStatusDto{" +
                "status=" + status +
                ", totalTasks=" + totalTasks +
                '}';
    }
}
