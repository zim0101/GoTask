package com.go_task.entity;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    public Task() {}

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Task(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
