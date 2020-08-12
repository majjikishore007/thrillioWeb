package com.mkk.thirilo;

//import java.util.ArrayList;
//import java.util.List;
//
//import com.thirilo.utill.IOUtil;
//import com.thirlilo.constants.BookGenre;
//import com.thirlilo.constants.Gender;
//import com.thirlilo.constants.MovieGenre;
//import com.thirlilo.constants.UserType;
//import com.thirlo.entites.BookMark;
//import com.thirlo.entites.User;
//import com.thirlo.entites.UserBookmark;
//import com.thirlo.managers.BookMarkMannager;
//import com.thirlo.managers.UserManager;
//
///*public class DataStore {
//
//	private static List<User> users = new ArrayList<>();
//
//	public static List<User> getUsers() {
//		return users;
//	}
//
//	private static List<List<BookMark>> bookmarks = new ArrayList<>();
//
//	public static List<List<BookMark>> getBookmarks() {
//		return bookmarks;
//	}
//
//	private static List<UserBookmark> userBookmarks = new ArrayList<>();
//
//	public static void loadData() {
//		loadUsers();
//		loadWebLinks();
//		loadMovies();
//		loadBooks();
//	}
//
//	private static void loadUsers() {
//
//		List<String> data = new ArrayList<>();
//		IOUtil.read(data, "User");
//		for (String row : data) {
//			String[] values = row.split("\t");
//
//			Gender gender = Gender.MALE;
//			if (values[5].equals("f")) {
//				gender = Gender.FEMALE;
//			} else if (values[5].equals("t")) {
//				gender = Gender.TRANSGENDER;
//			}
//
//			User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3],
//					values[4], gender, UserType.valueOf(values[6]));
//			users.add(user);
//		}
//	}
//
//	/*private static void loadWebLinks() {
//		List<String> data = new ArrayList<>();
//		IOUtil.read(data, "WebLink");
//		List<BookMark> bookmarkList = new ArrayList<>();
//		for (String row : data) {
//			String[] values = row.split("\t");
//
//			BookMark bookmark = BookMarkMannager.getInstance().createWeblink(Long.parseLong(values[0]), values[1],
//					values[2], values[3], values[4]);
//			bookmarkList.add(bookmark);
//		}
//		bookmarks.add(bookmarkList);
//	}*/
//
//	private static void loadMovies() {
//		List<String> data = new ArrayList<>();
//		IOUtil.read(data, "Movie");
//		List<BookMark> bookmarkList = new ArrayList<>();
//		for (String row : data) {
//			String[] values = row.split("\t");
//			String[] cast = values[3].split(",");
//			String[] directors = values[4].split(",");
//
//			BookMark bookmark = BookMarkMannager.getInstance().createMovie(Long.parseLong(values[0]), values[1],
//					Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5]),
//					Double.parseDouble(values[6])/* , values[7] */);
//			bookmarkList.add(bookmark);
//		}
//
//		bookmarks.add(bookmarkList);
//	}
//
//	private static void loadBooks() {
//		List<String> data = new ArrayList<>();
//		IOUtil.read(data, "Book");
//		List<BookMark> bookmarkList = new ArrayList<>();
//		for (String row : data) {
//			String[] values = row.split("\t");
//			String[] authors = values[4].split(",");
//			BookMark bookmark = BookMarkMannager.getInstance().createBook(Long.parseLong(values[0]), values[1],
//					Integer.parseInt(values[2]), values[3], authors, BookGenre.valueOf(values[5]),
//					Double.parseDouble(values[6])/* , values[7] */);
//			bookmarkList.add(bookmark);
//		}
//
//		bookmarks.add(bookmarkList);
//	}
//
//	public static void add(UserBookmark userBookmark) {
//		userBookmarks.add(userBookmark);
//
//	}
//
//}
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.thirlilo.constants.BookGenre;
import com.thirlilo.constants.Gender;
import com.thirlilo.constants.MovieGenre;
import com.thirlilo.constants.UserType;
import com.thirlo.entites.BookMark;
import com.thirlo.entites.User;
import com.thirlo.entites.UserBookmark;
import com.thirlo.managers.BookMarkMannager;
import com.thirlo.managers.UserManager;

public class DataStore {

	private static List<User> users = new ArrayList<>();

	public static List<User> getUsers() {
		return users;
	}

	private static List<List<BookMark>> bookmarks = new ArrayList<>();

	public static List<List<BookMark>> getBookmarks() {
		return bookmarks;
	}

	private static List<UserBookmark> userBookmarks = new ArrayList<>();

	public static void loadData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// try-with-resources ==> conn & stmt will be closed
		// Connection string: <protocol>:<sub-protocol>:<data-source details>
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio", "root",
				"majji@007"); Statement stmt = conn.createStatement();) {
			loadUsers(stmt); // Student is expected to implement this method
			loadWebLinks(stmt); // Student is expected to implement this method
			loadMovies(stmt);
			loadBooks(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void loadWebLinks(Statement stmt) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select *from weblink";
		/*
		 * long id, String title, String profileUrl, String url, String host
		 */
		List<BookMark> bookmarkList = new ArrayList<>();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			String url = rs.getString("url");
			String host = rs.getString("host");

			BookMark bookmark = BookMarkMannager.getInstance().createWeblink(id, title, url, host, "");

			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadUsers(Statement stmt) throws SQLException {
		String query = "select *from user";
		/*
		 * (long id, String email, String password, String firstName, String lastname,
		 * Gender gender, UserType userType)
		 */
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			long id = rs.getLong("id");
			String email = rs.getString("email");
			String password = rs.getString("password");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			int gender_id = rs.getInt("gender_id");
			Gender gender = Gender.values()[gender_id];
			int user_type_id = rs.getInt("user_type_id");
			UserType userType = UserType.values()[user_type_id];

			User user = UserManager.getInstance().createUser(id, email, password, firstName, lastName, gender,
					userType);
			users.add(user);
		}

	}

	private static void loadMovies(Statement stmt) throws SQLException {
		String query = "Select m.id, title, release_year, GROUP_CONCAT(DISTINCT a.name SEPARATOR ',') AS cast, GROUP_CONCAT(DISTINCT d.name SEPARATOR ',') AS directors, movie_genre_id, imdb_rating"
				+ " from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md "
				+ "where m.id = ma.movie_id and ma.actor_id = a.id and "
				+ "m.id = md.movie_id and md.director_id = d.id group by m.id";
		ResultSet rs = stmt.executeQuery(query);

		List<BookMark> bookmarkList = new ArrayList<>();
		while (rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			int releaseYear = rs.getInt("release_year");
			String[] cast = rs.getString("cast").split(",");
			String[] directors = rs.getString("directors").split(",");
			int genre_id = rs.getInt("movie_genre_id");
			MovieGenre genre = MovieGenre.values()[genre_id];
			double imdbRating = rs.getDouble("imdb_rating");

			BookMark bookmark = BookMarkMannager.getInstance().createMovie(id, title, releaseYear, cast, directors,
					genre, imdbRating/* , values[7] */);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadBooks(Statement stmt) throws SQLException {
		String query = "Select b.id, title, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
				+ " from Book b, Publisher p, Author a, Book_Author ba "
				+ "where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";
		ResultSet rs = stmt.executeQuery(query);

		List<BookMark> bookmarkList = new ArrayList<>();
		while (rs.next()) {
			long id = rs.getLong("id");
			String title = rs.getString("title");
			String imgUrl=rs.getString("image_url");
			int publicationYear = rs.getInt("publication_year");
			String publisher = rs.getString("name");
			String[] authors = rs.getString("authors").split(",");
			int genre_id = rs.getInt("book_genre_id");
			BookGenre genre = BookGenre.values()[genre_id];
			double amazonRating = rs.getDouble("amazon_rating");

			Date createdDate = rs.getDate("created_date");
			System.out.println("createdDate: " + createdDate);
			Timestamp timeStamp = rs.getTimestamp(8);
			System.out.println("timeStamp: " + timeStamp);
			System.out.println("localDateTime: " + timeStamp.toLocalDateTime());

			System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear
					+ ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genre
					+ ", amazonRating: " + amazonRating);

			BookMark bookmark = BookMarkMannager.getInstance().createBook(id, title, imgUrl, publicationYear, publisher,
					authors, genre, amazonRating/* , values[7] */);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

//	public static void add(UserBookmark userBookmark) {
//		userBookmarks.add(userBookmark);
//	}
}
