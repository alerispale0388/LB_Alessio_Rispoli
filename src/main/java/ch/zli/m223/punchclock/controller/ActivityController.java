package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Activity;
import ch.zli.m223.punchclock.service.ActivityService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/activities")
@Tag(name="Activity", description = "Manage Activities")
@RolesAllowed({"User"})
public class ActivityController {

    @Inject
    ActivityService activityService;

    /**
     * Get all activities
     * @return Returns all activities in DB
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> list() {
        return activityService.findAll();
    }

    /**
     * Create or update an activity
     * @return Returns created or updated activity
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Activity createOrUpdate(Activity activity) {
        return activityService.createActivity(activity);
    }

    /**
     * Delete specified Activity
     * @param id delete specified activity
     */
    @DELETE
    @Path("/{id}")
    public void deleteWithId(@PathParam Long id){
        activityService.deleteActivity(id);
    }

    /**
     * Get specified Activity
     * @param id get specified project
     * @return Returns the specified project
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Activity getSingleActivity(@PathParam Long id){
        return activityService.getActivityById(id);
    };

}
