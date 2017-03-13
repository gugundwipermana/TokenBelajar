package com.gudperna.dao.impl;

import com.gudperna.dao.UserDAO;
import com.gudperna.model.User;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.internal.util.Base64;

public class UserDAOImpl implements UserDAO {
	
	private Connection connection;

	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<User> getAll() {
		ArrayList<User> result = new ArrayList<User>();
		Statement stmt = null;
		ResultSet rs = null;


 		try {
 			if(connection != null) {
	 			stmt = connection.createStatement();
	 			rs = stmt.executeQuery("SELECT * FROM users");
	 			while(rs.next()) {
	 				User user = new User();
	 				user.setId(rs.getInt("id"));
	 				user.setEmail(rs.getString("email"));
	 				user.setPassword(rs.getString("password"));
	 				user.setName(rs.getString("name"));
	 				result.add(user);
	 			}
 			}
 		} catch (SQLException ex) {
 			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 		} finally {
 			if(connection != null) try {
 				connection.close();
 			} catch (SQLException ex) {
 				Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 			}
 
 			if(stmt != null) try {
 				stmt.close();
 			} catch (SQLException ex) {
 				Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 			}
 
 			if(rs != null) try {
 				rs.close();
 			} catch (SQLException ex) {
 				Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
 			}
 		}

		return result;
	}

	public boolean cekUser(User user) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		boolean user_exists = false;

		try {
			ps = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}

		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	 	}

	 	return false;
	}

	public User getByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();
		try {
			ps = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
			}
		} catch(SQLException ex) {
			Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
	 	}

		return user;
	}
}