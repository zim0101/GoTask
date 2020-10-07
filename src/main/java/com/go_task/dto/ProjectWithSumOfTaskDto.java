package com.go_task.dto;

public class ProjectWithSumOfTaskDto {

    private int projectId;
    private String name;
    private long totalTasks;

    public ProjectWithSumOfTaskDto(int id, String name, long totalTasks) {
        this.projectId = id;
        this.name = name;
        this.totalTasks = totalTasks;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(long totalTasks) {
        this.totalTasks = totalTasks;
    }

    @Override
    public String toString() {
        return "ProjectWithSumOfTaskDto{" +
                "projectId=" + projectId +
                ", name='" + name + '\'' +
                ", totalTasks=" + totalTasks +
                '}';
    }
}
