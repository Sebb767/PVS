package de.fhws.fiw.pvs.restdemo;

import com.google.gson.Gson;
import de.fhws.fiw.pvs.grpc.Greeter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by braunpet on 24.04.17.
 */
@Path( "users" )
public class UserService
{
	protected static ArrayList<Person> persons = new ArrayList<>();
	protected final Gson gson = new Gson();

	@Context
	protected UriInfo uriInfo;

	@Context
	protected ContainerRequestContext requestContext;

	@Context
	protected Request request;

	@Context
	protected HttpServletRequest httpServletRequest;

	@GET
	public Response ping( )
	{
		return Response.ok( gson.toJson(persons.toArray()) ).build( );
	}

	@GET
	@Path( "{id}" )
	public Response getPersonById( @PathParam( "id" ) int id )
	{
		return persons
				.stream()
				.filter(p -> p.getId() == id)
				.findFirst()
				.map(person -> Response.ok(gson.toJson(person)).build())
				.orElseGet(this::fail);
	}

	@DELETE
	@Path( "{id}" )
	public Response deletePerson( @PathParam( "id" ) int id )
	{
		persons
				.stream()
				.filter(p -> p.getId() == id)
				.findFirst()
				.ifPresent(person -> persons.remove(person));

		return Response.ok( gson.toJson(new SimpleResponse(true)) ).build();
	}

	@POST
	public Response updatePerson(String text)
	{
		Person p = gson.fromJson(text, Person.class);
		if(p == null)
			return fail();

		p.generateId();
		persons.add(p);
		return Response.ok( gson.toJson(new SimpleResponse(true)) ).build();
	}

	private Response fail()
	{
		return Response.ok( gson.toJson(new SimpleResponse(false))).status(400).build();
	}

	private class SimpleResponse {
		public boolean success;
		public String message;

		public SimpleResponse(boolean success) {
			this(success, "");
		}

		public SimpleResponse(boolean success, String message) {
			this.success = success;
			this.message = message;
		}
	}
}
