package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Project;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class ProjectService {
    @Inject
    EntityManager entityManager;

    public ProjectService() {
    }

    @Transactional
    public Project createProject(Project project) {
        entityManager.persist(project);
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
        return entityManager.find(Project.class, id);
    };

    @Transactional
    public void update(Project entry){
        entityManager.merge(entry);
    }

}
