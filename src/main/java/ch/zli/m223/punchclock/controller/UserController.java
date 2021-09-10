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
@RolesAllowed({"User"})
public class UserController {

    @Inject
    UserService userService;

    /**
     * Get users
     * @return Returns all user in DB
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        return userService.findAll();
    }

    /**
     * Get users with email longer than 10
     * @return Returns all user with email longer than 10 in DB
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/special")
    public List<User> specialList() {
        return userService.findSpecialUser();
    }

    /**
     * Create or Update a user
     * @return Returns created or updated user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User createOrUpdate(User user) {
        return userService.createUser(user);
    }

    /**
     * Delete specified user
     * @param id delete specified user
     */
    @DELETE
    @Path("/{id}")
    public void deletesSpecified(@PathParam Long id){
        userService.deleteUser(id);
    }

    /**
     * Get specified user
     * @param id get specified user
     * @return Returns the specified user
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getSingleUser(@PathParam Long id){
        return userService.getUserById(id);
    };
}
