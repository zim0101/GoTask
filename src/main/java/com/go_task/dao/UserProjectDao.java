package com.go_task.dao;


import com.go_task.entity.Project;
import com.go_task.entity.User;
import com.go_task.entity.UserProject;
import com.go_task.entity.UserProject_;
import org.hibernate.criterion.ProjectionList;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class UserProjectDao extends BaseDaoImpl <UserProject> {

    public UserProjectDao(Class<UserProject> entity) {
        super(entity);
    }

    public boolean userIsAssignedToProject(User user, Project project) {
        UserProject userProject = (UserProject)
            criteriaContext((session, root, criteria,builder) -> {
                criteria.select(root)
                        .where(builder.equal(root.get(UserProject_.user), user))
                        .where(builder.equal(root.get(UserProject_.project),
                                project));
                return session.createQuery(criteria).getSingleResult();
            });
        return userProject != null;
    }

    public void assignUserToProject(int userId, int projectId) {
        noReturnContext(session -> {
            User user = session.get(User.class, userId);
            Project project = session.get(Project.class, projectId);
            UserProject userProject = new UserProject(user, project);
            session.save(userProject);
        });
    }

    public void dischargeUserFromProject(int userId, int projectId) {
        noReturnContext(session -> {
            User user = session.get(User.class, userId);
            Project project = session.get(Project.class, projectId);
            user.removeProject(project);
            session.update(user);
        });
    }

    public List<Project> getProjectsOfUser(int userId) {
        List<Project> projects = new ArrayList<>();
        noReturnContext(session -> {
            User user = session.get(User.class, userId);
            user.getProjects().forEach(userProject ->
                    projects.add(userProject.getProject()));
        });

        return projects;
    }

    public List<User> getUsersOfProject(int projectId) {
        List<User> users = new ArrayList<>();
        noReturnContext(session -> {
            Project project = session.get(Project.class, projectId);
            project.getUsers().forEach(userProject ->
                    users.add(userProject.getUser()));
        });

        return users;
    }
}
