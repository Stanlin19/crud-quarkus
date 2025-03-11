package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.models.Person;
import org.acme.services.PersonService;

@Path("/person")
public class PersonResource {

    @Inject
    PersonService personService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.ok(personService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
        return Response.ok(personService.getPersonById(id)).build();
    }

    @POST
    public Response create(Person person){
        Person createdPerson = personService.save(person);
        return Response.status(Response.Status.CREATED).entity(createdPerson).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id")Long id, Person person){
        Person updatedPerson = personService.update(person, id);
        if (updatedPerson != null) {
            return Response.ok(updatedPerson).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) {
        boolean deleted = personService.deletePerson(id);
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
