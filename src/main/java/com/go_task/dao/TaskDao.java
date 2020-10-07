package com.go_task.dao;


import com.go_task.dto.TotalTaskByStatusDto;
import com.go_task.entity.Project;
import com.go_task.entity.Task;
import com.go_task.entity.Task_;
import com.go_task.utils.enums.TaskStatus;
import javax.persistence.criteria.*;
import java.util.List;


public class TaskDao extends BaseDaoImpl <Task> {

    public TaskDao(Class<Task> entity) {
        super(entity);
    }

    public int createTaskWithProject(int projectId, Task task) {
        return (int) context(session -> {
            Project project = session.get(Project.class, projectId);
            task.setProject(project);
            return session.save(task);
        });
    }

    public List<Task> getTaskListByStoryPointRange(int from, int to) {
        return criteriaContext((session, root, criteria, builder) -> {
            Predicate storyPointBetween = builder.between(
                    root.get(Task_.STORY_POINTS),
                    from,
                    to
            );
            criteria.select(root).where(storyPointBetween);
            return session.createQuery(criteria).getResultList();
        });
    }

    public List<Task> getTaskListByStatus(TaskStatus status) {
        return criteriaContext((session, root, criteria, builder) -> {
            Predicate matchStatus = builder.equal(
                    root.get(Task_.status), status
            );
            criteria.select(root).where(matchStatus);
            return session.createQuery(criteria).getResultList();
        });
    }

    public List<TotalTaskByStatusDto> taskAnalyticsListGroupedByStatus() {
        return criteriaBuilderContext((session, builder) -> {
            CriteriaQuery<TotalTaskByStatusDto> criteria =
                    builder.createQuery(TotalTaskByStatusDto.class);
            Root<Task> root = criteria.from(Task.class);

            Expression<TaskStatus> groupByStatus = root.get(Task_.STATUS).as(TaskStatus.class);
            Expression<Long> countTaskCount = builder.count(groupByStatus);
            Order totalTaskCountInDescendingOrder = builder.desc(countTaskCount);

            CriteriaQuery<TotalTaskByStatusDto> select =
                    criteria.multiselect(groupByStatus, countTaskCount);

            criteria.groupBy(groupByStatus).orderBy(totalTaskCountInDescendingOrder);

            return session.createQuery(select).getResultList();
        });
    }
}
