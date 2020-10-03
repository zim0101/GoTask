package modules.project;

import com.go_task.dao.ProjectDao;
import com.go_task.dao.UserDao;
import com.go_task.dao.UserProjectDao;
import com.go_task.entity.Project;
import com.go_task.entity.User;
import com.go_task.entity.UserProject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectModuleTest {

    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final UserProjectDao userProjectDao;

    public ProjectModuleTest() {
        this.userDao = new UserDao(User.class);
        this.projectDao = new ProjectDao(Project.class);
        this.userProjectDao = new UserProjectDao(UserProject.class);
    }

    private Project buildProject() {
        return new Project("Project 1", "Dummy project");
    }

    private User buildUser() {
        return new User("New User 1", "user@email.com", "pass");
    }

    @Test
    public void testProjectModule() {
        int userId = createUser();
        int projectId = createProject();
        updateProject(projectId);
        listAllProjects();
        assignUserToProject(userId, projectId);
        getProjectsByUser(userId);
        getUsersByProject(projectId);
    }

    public int createUser() {
        return userDao.create(buildUser());
    }

    public int createProject() {
        return projectDao.create(buildProject());
    }

    private void updateProject(int projectId) {
        String newName = "Updated name";
        Project project = (Project) projectDao.find(projectId);
        project.setName(newName);
        projectDao.update(project);
        Project updatedProject = (Project) projectDao.find(projectId);

        assertEquals(updatedProject.getName(), newName);
    }

    private void listAllProjects() {
        List<Project> projects = projectDao.all();
        assertTrue(projects.size() > 0);
    }

    private void assignUserToProject(int userId, int projectId) {
        userProjectDao.assignUserToProject(userId, projectId);
    }

    private void getProjectsByUser(int userId) {
        List<Project> projects = userProjectDao.getProjectsOfUser(userId);
        assertTrue(projects.size() > 0);
    }

    private void getUsersByProject(int projectId) {
        List<User> users = userProjectDao.getUsersOfProject(projectId);
        assertTrue(users.size() > 0);
    }
}
