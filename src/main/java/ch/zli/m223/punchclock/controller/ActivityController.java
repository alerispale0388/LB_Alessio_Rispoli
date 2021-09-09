package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Activity;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.ActivityService;
import ch.zli.m223.punchclock.service.EntryService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/activities")
public class ActivityController {


    @Inject
    ActivityService activityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> list() {
        return activityService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Activity add(Activity activity) {
        return activityService.createActivity(activity);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam Long id){
        activityService.deleteActivity(id);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Activity getSingleEntry(@PathParam Long id){
        return activityService.getActivityById(id);
    };

    @PUT
    public void update(Activity activity){
        activityService.update(activity);
    }

}
