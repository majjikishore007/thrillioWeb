package com.thirilo.dao;

import com.thirlo.entites.User;

public class UserDao {
//	public List<User> getUsers() {
//		return DataStore.getUsers();
//	}

	public User getUser(long userId) {

		return Data.getUser(userId);

		/*
		 * User user = null;
		 * 
		 * try { Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException
		 * e) { e.printStackTrace(); }
		 * 
		 * try (Connection conn =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio",
		 * "root", "majji@007"); Statement stmt = conn.createStatement();) { String
		 * query = "Select * from User where id = " + userId; ResultSet rs =
		 * stmt.executeQuery(query);
		 * 
		 * while (rs.next()) { long id = rs.getLong("id"); String email =
		 * rs.getString("email"); String password = rs.getString("password"); String
		 * firstName = rs.getString("first_name"); String lastName =
		 * rs.getString("last_name"); int gender_id = rs.getInt("gender_id"); Gender
		 * gender = Gender.values()[gender_id]; int user_type_id =
		 * rs.getInt("user_type_id"); UserType userType =
		 * UserType.values()[user_type_id];
		 * 
		 * user = UserManager.getInstance().createUser(id, email, password, firstName,
		 * lastName, gender, userType); } } catch (SQLException e) {
		 * e.printStackTrace(); }
		 * 
		 * return user;
		 */

	}

	public long authenticate(String email, String password) {
//		without database 
		return Data.authenticate(email, password);

//		

		/*
		 * try { Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException
		 * e) { e.printStackTrace(); }
		 * 
		 * try (Connection conn =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio",
		 * "root", "majji@007"); Statement stmt = conn.createStatement();) { String
		 * query = "Select id from User where email = '" + email + "' and password = '"
		 * + password + "'"; System.out.println("query: " + query); ResultSet rs =
		 * stmt.executeQuery(query);
		 * 
		 * while (rs.next()) {
		 * 
		 * System.out.println(rs.getLong("id")); return rs.getLong("id"); }
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); }
		 * 
		 * return -1;
		 */
	}
}
