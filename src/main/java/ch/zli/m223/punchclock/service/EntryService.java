package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Activity;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;

@ApplicationScoped
public class EntryService {
    @Inject
    EntityManager entityManager;

    @Inject
    ProjectService projectService;

    @Inject
    UserService userService;

    public EntryService() {
    }

    @Transactional
    public Entry createEntry(Entry entry) {
        Project project = projectService.getProjectById(entry.getProject().getId());
        entry.setProject(project);
        User user = userService.getUserById(entry.getUser().getId());
        entry.setUser(user);
        entityManager.merge(entry);
        return entry;
    }

    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }

    @Transactional
    public void deleteEntry(Long id){

        Entry removeEntry = getEntryById(id);

        entityManager.remove(removeEntry);
    }

    @Transactional
    public Entry getEntryById(Long id){
        Entry entry = entityManager.find(Entry.class, id);
        Project project = projectService.getProjectById(entry.getProject().getId());
        entry.setProject(project);
        User user = userService.getUserById(entry.getUser().getId());
        entry.setUser(user);
        return entry;
    };

    @Transactional
    public void update(Entry entry){
        entityManager.merge(entry);
    }

}
