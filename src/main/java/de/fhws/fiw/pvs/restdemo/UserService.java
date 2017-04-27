package de.fhws.fiw.pvs.restdemo;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by braunpet on 24.04.17.
 */
@Path( "users" )
public class UserService
{
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
		return Response.ok( "Hello All Persons!" ).build( );
	}

	@GET
	@Path( "{id}" )
	public Response getPersonById( @PathParam( "id" ) long id )
	{
		return Response.ok( "Hello Person " + id ).build( );
	}

}
