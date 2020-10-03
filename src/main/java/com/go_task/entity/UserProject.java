package com.go_task.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "user_project")
public class UserProject implements Serializable {

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Project project;


    public UserProject() {}

    public UserProject(User user, Project project) {
        this.user = user;
        this.project = project;
    }

    @Id
    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        UserProject that = (UserProject) o;
        return Objects.equals( user, that.user ) &&
                Objects.equals( project, that.project );
    }

    @Override
    public int hashCode() {
        return Objects.hash( user, project );
    }
}
