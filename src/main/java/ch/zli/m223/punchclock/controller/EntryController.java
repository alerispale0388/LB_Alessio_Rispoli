package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import javax.naming.Name;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/entries")
@Tag(name="Entry", description = "Manage Entries")
@RolesAllowed({"User"})
public class EntryController {

    @Inject
    EntryService entryService;

    /**
     * Get entries
     * @return Returns all entries in DB
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entry> list() {
        return entryService.findAll();
    }

    /**
     * Create or Update an entry
     * @return Returns created or updated entry
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry createOrUpdate(Entry entry) {
        return entryService.createEntry(entry);
    }

    /**
     * Delete specified entry
     * @param id delete specified entry
     */
    @DELETE
    @Path("/{id}")
    public void deleteSpecified(@PathParam Long id){
        entryService.deleteEntry(id);
    }

    /**
     * Get specified entry
     * @param id get specified entry
     * @return Returns the specified entry
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entry getSingleEntry(@PathParam Long id){
        return entryService.getEntryById(id);
    };
}
