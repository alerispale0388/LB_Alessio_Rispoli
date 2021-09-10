package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Activity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ActivityService {
    @Inject
    EntityManager entityManager;

    public ActivityService() {
    }

    @Transactional
    public Activity createActivity(Activity activity) {
        entityManager.merge(activity);
        return activity;
    }

    @SuppressWarnings("unchecked")
    public List<Activity> findAll() {
        var query = entityManager.createQuery("FROM Activity");
        return query.getResultList();
    }

    @Transactional
    public void deleteActivity(Long id){
        Activity removeActivity = getActivityById(id);

        entityManager.remove(removeActivity);
    }

    @Transactional
    public Activity getActivityById(Long id){
        return entityManager.find(Activity.class, id);
    };

    @Transactional
    public void update(Activity activity){
        entityManager.merge(activity);
    }

    @Transactional
    public List<Activity> findAllActivitiesWithProjectId(Long project_id){
        var query = entityManager.createQuery("FROM Activity WHERE project_id = :project_id");
        query.setParameter("project_id", project_id);
        return query.getResultList();
    }


}
