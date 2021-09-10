package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Activity;
import ch.zli.m223.punchclock.domain.Project;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProjectService {
    @Inject
    EntityManager entityManager;

    @Inject
    ActivityService activityService;

    public ProjectService() {
    }

    @Transactional
    public Project createProject(Project project) {
        List<Activity> activities = activityService.findAllActivitiesWithProjectId(project.getId());
        project.setActivities(activities);
        entityManager.merge(project);
        return project;
    }

    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        var query = entityManager.createQuery("FROM Project");
        return query.getResultList();
    }

    @Transactional
    public void deleteEntry(Long id){
        Project removeProject = getProjectById(id);
        entityManager.remove(removeProject);
    }

    @Transactional
    public Project getProjectById(Long id){
        Project project = entityManager.find(Project.class, id);
        List<Activity> activities = activityService.findAllActivitiesWithProjectId(id);
        project.setActivities(activities);
        return project;
    };

    @Transactional
    public void update(Project entry){
        entityManager.merge(entry);
    }

}
