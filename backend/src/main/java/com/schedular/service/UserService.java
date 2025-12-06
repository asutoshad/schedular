package com.schedular.service;

import com.schedular.model.User;

public interface UserService {
	
	void SaveUser(User user);
	void getSingerUser(Long User);

	User saveGoogleUser(String email, String name);
	
	  User loginUser(String username, String password); 
	    User socialLogin(String email, String name);     
}
