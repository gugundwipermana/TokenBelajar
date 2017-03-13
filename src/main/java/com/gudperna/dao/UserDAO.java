package com.gudperna.dao;

import com.gudperna.model.User;

import java.util.ArrayList;

public interface UserDAO {
	public ArrayList<User> getAll();
	public boolean cekUser(User user);
	public User getByEmail(String email);
}