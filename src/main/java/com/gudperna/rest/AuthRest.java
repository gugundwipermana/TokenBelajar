package com.gudperna.rest;

import java.util.List;

import javax.ws.rs.DELETE; 
import javax.ws.rs.GET; 
import javax.ws.rs.POST; 
import javax.ws.rs.PUT; 
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.gudperna.util.ConnectionUtil;
import com.gudperna.dao.UserDAO;
import com.gudperna.dao.impl.UserDAOImpl;

import com.gudperna.model.User;

import org.glassfish.jersey.internal.util.Base64;

@Path("auth")
public class AuthRest {

	UserDAO userService = new UserDAOImpl(ConnectionUtil.getConnection());

	@POST
	@Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {

        Response resp;
    	if(userService.cekUser(user)) {

    		String email = user.getEmail();
			String password = user.getPassword();

    		resp = Response.ok("Basic "+Base64.encodeAsString(email+":"+password)).build();
    	} else {
    		resp = Response.ok("Login error ").build();
    	}

    	return resp;
    }
	
}
