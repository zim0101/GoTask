package modules.dummy;

import com.go_task.dao.ProjectDao;
import com.go_task.dao.TaskDao;
import com.go_task.dto.ProjectWithSumOfTaskDto;
import com.go_task.entity.Project;
import com.go_task.entity.Task;
import org.junit.Test;
import java.util.List;

public class DummyTest {

    @Test
    public void dummyIsDoingFine() {
        TaskDao taskDao = new TaskDao(Task.class);
        ProjectDao projectDao = new ProjectDao(Project.class);

//        List<TotalTaskByStatusDto> analyticalTaskList = taskDao.totalTaskCountGroupedByStatusList();
//        analyticalTaskList.forEach(analytics -> System.out.println(analytics.toString()));

        List<ProjectWithSumOfTaskDto> projectsWithTaskCount = projectDao.projectNameWithTotalTaskCountList();
        projectsWithTaskCount.forEach(row -> System.out.println(row.toString()));
    }
}
