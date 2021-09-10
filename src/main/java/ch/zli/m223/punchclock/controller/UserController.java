package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Tag(name="User", description = "Manage User")
public class UserController {

    @Inject
    UserService userService;

    /**
     * Get users
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        return userService.findAll();
    }

    /**
     * Create or Update a user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User createOrUpdate(User user) {
        return userService.createUser(user);
    }

    /**
     * Delete specified user
     */
    @DELETE
    @Path("/{id}")
    public void deletesSpecified(@PathParam Long id){
        userService.deleteUser(id);
    }

    /**
     * Get specified user
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getSingleUser(@PathParam Long id){
        return userService.getUserById(id);
    };
}
