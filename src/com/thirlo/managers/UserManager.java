package com.thirlo.managers;

import com.thirilo.dao.UserDao;
import com.thirlilo.constants.Gender;
import com.thirlilo.constants.UserType;
import com.thirlo.entites.User;

public class UserManager {
	private static UserManager instance = new UserManager();
	private static UserDao dao = new UserDao();

	private UserManager() {
	}

	public static UserManager getInstance() {
		return instance;
	}
	
	public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender,
			UserType userType) {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setUserType(userType);

		return user;

	}

//	public List<User> getUsers() {
//		return dao.getUsers();
//	}

	public User getUser(Long userId) {
		
		return dao.getUser(userId);
	}

	public Long authenticate(String email, String password) {
		
		return dao.authenticate(email, password);
	}

}
