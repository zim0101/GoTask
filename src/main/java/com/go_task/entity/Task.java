package com.go_task.entity;


import com.go_task.utils.enums.Priority;
import com.go_task.utils.enums.TaskStatus;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "task")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "story_points")
    private int storyPoints;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "started_at", columnDefinition="DATETIME")
    private Date startedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_at", columnDefinition="DATETIME")
    private Date endAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName="id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName="id")
    private Project project;

    public Task() {}

    public Task(String title, String description, int storyPoints,
                TaskStatus status, Priority priority, Date startedAt,
                Date endAt) {
        this.title = title;
        this.description = description;
        this.storyPoints = storyPoints;
        this.status = status;
        this.priority = priority;
        this.startedAt = startedAt;
        this.endAt = endAt;
    }

    public Task(String title, String description, int storyPoints,
                Priority priority) {
        this.title = title;
        this.description = description;
        this.storyPoints = storyPoints;
        this.priority = priority;
        this.status = TaskStatus.STEADY;
    }

    public Task(String title, String description, int storyPoints,
                TaskStatus status, Priority priority, User user) {
        this.title = title;
        this.description = description;
        this.storyPoints = storyPoints;
        this.status = status;
        this.priority = priority;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "story_points")
    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

    @Enumerated(EnumType.STRING)
    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Column(name = "started_at")
    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    @Column(name = "end_at")
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName="id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Enumerated(EnumType.STRING)
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName="id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", storyPoints=" + storyPoints +
                ", status=" + status +
                ", priority=" + priority +
                ", startedAt=" + startedAt +
                ", endAt=" + endAt +
                '}';
    }
}
