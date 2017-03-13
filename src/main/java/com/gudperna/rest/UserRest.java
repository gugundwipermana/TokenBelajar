package com.gudperna.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import java.util.List;

import com.gudperna.util.ConnectionUtil;
import com.gudperna.dao.UserDAO;
import com.gudperna.dao.impl.UserDAOImpl;

import com.gudperna.model.User;

@Path("users")
public class UserRest {

	UserDAO userService = new UserDAOImpl(ConnectionUtil.getConnection());

	@GET
	// @Path("message")
	@Produces(MediaType.APPLICATION_JSON)
	public Response all(@Context SecurityContext ctx) {
		Response resp = Response.ok("Welcome: "+ctx.getUserPrincipal().getName()).build();

		return resp;


	}

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		List<User> listUser = userService.getAll(); 
		return listUser;
	}
	
}
