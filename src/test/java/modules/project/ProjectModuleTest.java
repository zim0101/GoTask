package modules.project;


import com.go_task.dao.ProjectDao;
import com.go_task.dao.TaskDao;
import com.go_task.dao.UserDao;
import com.go_task.dao.UserProjectDao;
import com.go_task.entity.Project;
import com.go_task.entity.Task;
import com.go_task.entity.User;
import com.go_task.entity.UserProject;
import com.go_task.utils.enums.Priority;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;


public class ProjectModuleTest {

    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final UserProjectDao userProjectDao;
    private final TaskDao taskDao;

    public ProjectModuleTest() {
        this.userDao = new UserDao(User.class);
        this.projectDao = new ProjectDao(Project.class);
        this.userProjectDao = new UserProjectDao(UserProject.class);
        this.taskDao = new TaskDao(Task.class);
    }

    private Project buildProject() {
        return new Project("Project 1", "Dummy project");
    }

    private User buildUser() {
        return new User("New User 1", "user@email.com", "pass");
    }

    private Task buildTask() {
        return new Task(
                "New task",
                "dummy description",
                1,
                Priority.REGULAR
        );
    }

    @Test
    public void testProjectModule() {
        int userId = createUser();
        int projectId = createProject();
        int taskId = createTask(projectId);

        updateProject(projectId);
        listAllProjects();
        assignUserToProject(userId, projectId);
        getProjectsByUser(userId);
        getUsersByProject(projectId);
        deleteProject(projectId);
    }

    public int createUser() {
        return userDao.create(buildUser());
    }

    public int createProject() {
        return projectDao.create(buildProject());
    }

    public int createTask(int projectId) {
        return taskDao.createTaskWithProject(projectId, buildTask());
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

    private void deleteProject(int projectId) {
        Project project = (Project) projectDao.find(projectId);
        projectDao.delete(project);
        Project deletedProject = (Project) projectDao.find(projectId);
        assertNull(deletedProject);
    }
}
