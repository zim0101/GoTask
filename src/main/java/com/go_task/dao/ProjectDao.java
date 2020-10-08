package com.go_task.dao;


import com.go_task.dto.ProjectWithSumOfTaskDto;
import com.go_task.entity.Project;
import com.go_task.entity.Project_;
import com.go_task.entity.Task;
import com.go_task.entity.Task_;
import javax.persistence.criteria.*;
import java.util.List;


public class ProjectDao extends BaseDaoImpl<Project> {

    public ProjectDao(Class<Project> entity) {
        super(entity);
    }

    public List<ProjectWithSumOfTaskDto> projectNameWithTotalTaskCountList() {
        return criteriaBuilderContext((session, builder) -> {
            CriteriaQuery<ProjectWithSumOfTaskDto> criteria =
                    builder.createQuery(ProjectWithSumOfTaskDto.class);
            Root<Task> task = criteria.from(Task.class);
            Join<Task, Project> projectJoin = task.join(Task_.project, JoinType.LEFT);
            criteria.multiselect(
                    projectJoin.get(Project_.ID).alias("projectId"),
                    projectJoin.get(Project_.NAME).alias("name"),
                    builder.count(task).alias("totalTasks")
            );
            criteria.groupBy(task.get(Task_.project));

            return session.createQuery(criteria).getResultList();
        });
    }
}
