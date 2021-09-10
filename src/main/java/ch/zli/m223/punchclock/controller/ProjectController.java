package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.service.ProjectService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/projects")
@Tag(name="Project", description = "Manage Projects")
@RolesAllowed("User")
public class ProjectController {

    @Inject
    ProjectService projectService;

    /**
     * Get projects
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> list() {
        return projectService.findAll();
    }

    /**
     * Create or Update a project
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Project createOrUpdate(Project project) {
        return projectService.createProject(project);
    }

    /**
     * Delete specified project
     */
    @DELETE
    @Path("/{id}")
    public void deleteSpecified(@PathParam Long id){
        projectService.deleteEntry(id);
    }

    /**
     * Get specified project
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getSingleProject(@PathParam Long id){
        return projectService.getProjectById(id);
    };
}
