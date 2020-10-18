package com.thirilo.dao;

import com.thirilo.utill.IOUtil;
import com.thirlilo.constants.BookGenre;
import com.thirlo.entites.*;
import com.thirlo.managers.BookMarkMannager;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class BookMarkDao {
//	public List<List<BookMark>> getBookmarks() {
//		return DataStore.getBookmarks();
//	}

	public void saveUserBookmark(UserBookmark userBookmark) {
	saveUserBook(userBookmark);
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio", "root",
//				"majji@007"); Statement stmt = conn.createStatement()) {
//			if (userBookmark.getBookmark() instanceof Weblink) {
//				saveUserWeblink(userBookmark, stmt);
//			} else if (userBookmark.getBookmark() instanceof Movie) {
//				saveUserMovie(userBookmark, stmt);
//			} else {
//				saveUserBook(userBookmark, stmt);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}

	private void saveUserBook(UserBookmark userBookmark){
//		array list addfun
		Data.saveUserBook(userBookmark);
//		String query = "insert into user_book (user_id, book_id) values (" + userBookmark.getUser().getId() + ","
//				+ userBookmark.getBookmark().getId() + ")";
//		stmt.executeUpdate(query);

	}

	private void saveUserMovie(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into user_movie (user_id, movie_id) values (" + userBookmark.getUser().getId() + ","
				+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);

	}

	private void saveUserWeblink(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into user_weblink (user_id, weblink_id) values (" + userBookmark.getUser().getId() + ","
				+ userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);
	}

	/*
	 * for downlading weblinks concurently using ,multithrading
	 */
//	public List<Weblink> getAllWeblinks() {
//		List<Weblink> result = new ArrayList<>();
//		List<List<BookMark>> bookmarks = DataStore.getBookmarks();
//		List<BookMark> allWeblinks = bookmarks.get(0);
//		for (BookMark bookMark : allWeblinks) {
//			result.add((Weblink) bookMark);
//		}
//		return result;
//	}

//	public List<Weblink> getWeblinks(Weblink.DownloadStaus downloadStaus) {
//		List<Weblink> result = new ArrayList<>();
//		List<Weblink> allWeblinks = getAllWeblinks();
//		for (Weblink weblink : allWeblinks) {
//			if (weblink.getDownloadStatus().equals(downloadStaus)) {
//				result.add(weblink);
//			}
//		}
//		return result;
//	}

	public void updateKidFriendlyStatus(BookMark bookmark) {
		System.out.println("inside update kid friendly sttus");
		int KidFriendlyStatus = bookmark.getKidfriendlyStatus().ordinal();
		long userid = bookmark.getKidFriendlyMarkedBy().getId();

		String tableToupdate = "Book";
		if (bookmark instanceof Movie) {
			tableToupdate = "Movie";
		} else if (bookmark instanceof Weblink) {
			tableToupdate = "Weblink";
		}
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio", "root",
				"majji@007"); Statement stmt = conn.createStatement();) {
			/*
			 * update book set kid_friendly_status=(),kid_friendly_marked_by=() where id
			 */
			String query = "update " + tableToupdate + " set kid_friendly_status = " + KidFriendlyStatus
					+ " ,kid_friendly_marked_by = " + userid + " where id = " + bookmark.getId() + ";";
			System.out.println(query);
			System.out.println("query(update kid friendly status):" + query);
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sharedByInfo(BookMark bookmark) {
		long userid = bookmark.getSharedBy().getId();

		String tableToupdate = "Book";
		if (bookmark instanceof Weblink) {
			tableToupdate = "Weblink";
		}
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio", "root",
				"majji@007"); Statement stmt = conn.createStatement();) {

			String query = "update " + tableToupdate + " set shared_by =" + userid + " where id= " + bookmark.getId()
					+ ";";
			System.out.println("query(update kid friendly status):" + query);
			stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * loading webpage
	 */
	public Collection<BookMark> getBooks(boolean isBookmarked, long userId) {
		System.out.println("get books");
		Collection<BookMark> result = new ArrayList<>();
		if(isBookmarked){
			result=  Data.getSaved();
			System.out.println(result);
		}
		else{
			System.out.println(result);
			result =Data.getUnSaved();
		}

//
//
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio", "root",
//				"majji@007"); Statement stmt = conn.createStatement()) {
////			System.out.println("kishore");
//			String query = "";
//			if (!isBookmarked) {
//				query = "Select b.id,title,image_url,publication_year,p.name as publisher, book_genre_id,amazon_rating, "
//						+ " GROUP_CONCAT(a.name SEPARATOR ',') AS authors from book_author ba join book b on b.id=ba.book_id "
//						+ " join author a on a.id=ba.author_id join publisher p on p.id=b.publisher_id "
//						+ " NOT IN (select ub.book_id from User u, User_Book ub where u.id = " + userId
//						+ " and u.id = ub.user_id) group by b.id";
//				System.out.println("not bookmarked");
//				System.out.println(query);
//			}
//
//			else {
//				query = "Select b.id, title, image_url, publication_year, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, "
//						+ "amazon_rating from Book b, Author a, Book_Author ba where b.id = ba.book_id and ba.author_id = a.id and "
//						+ "b.id IN (select ub.book_id from User u, User_Book ub where u.id = " + userId
//						+ " and u.id = ub.user_id) group by b.id";
//				System.out.println(" bookmarked");
//			}
//
////			System.out.println(query);
//			ResultSet rs = stmt.executeQuery(query);
//			JSONObject jsonObject = new JSONObject();
//			JSONArray array = new JSONArray();
////			System.out.println("kishore");
//			while (rs.next()) {
//				long id = rs.getLong("id");
//				String title = rs.getString("title");
//				String imageUrl = rs.getString("image_url");
//				int publicationYear = rs.getInt("publication_year");
//				// String publisher = rs.getString("name");
//				String[] authors = rs.getString("authors").split(",");
//				int genre_id = rs.getInt("book_genre_id");
//				BookGenre genre = BookGenre.values()[genre_id];
//				double amazonRating = rs.getDouble("amazon_rating");
//
//				System.out.println("id: " + id + ", title: " + title + ",imageUrl  " + imageUrl
//						+ " , publication year: " + publicationYear + ", authors: " + String.join(", ", authors)
//						+ ", genre: " + genre + ", amazonRating: " + amazonRating);
//
//				BookMark bookmark = BookMarkMannager.getInstance().createBook(id, title, imageUrl, publicationYear,
//						null, authors, genre, amazonRating/* , values[7] */);
//
//				result.add(bookmark);
//
//			}
////			File jsonfile=new File("E:\\javaProjects\\BooksAloha\\thrillioWeb\\data.json");
////			 ObjectMapper om=new ObjectMapper();
////	        try {
////				om.writeValue(jsonfile, result);
////			} catch (IOException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//
////	        System.out.println("Done!");
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return result;
	}

	public static BookMark getBook(long bookId) {
		return Data.getBook(bookId);
//		find book in list
//		Book book = null;
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio", "root",
//				"majji@007"); Statement stmt = conn.createStatement()) {
//			String query = "Select b.id, title, image_url, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
//					+ " from Book b, Publisher p, Author a, Book_Author ba " + "where b.id = " + bookId
//					+ " and b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id";
//			ResultSet rs = stmt.executeQuery(query);
//
//			while (rs.next()) {
//				long id = rs.getLong("id");
//				String title = rs.getString("title");
//				String imageUrl = rs.getString("image_url");
//				int publicationYear = rs.getInt("publication_year");
//				String publisher = rs.getString("name");
//				String[] authors = rs.getString("authors").split(",");
//				int genre_id = rs.getInt("book_genre_id");
//				BookGenre genre = BookGenre.values()[genre_id];
//				double amazonRating = rs.getDouble("amazon_rating");
//
////				System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genre + ", amazonRating: " + amazonRating);
//
//				book = BookMarkMannager.getInstance().createBook(id, title, imageUrl, publicationYear, publisher,
//						authors, genre, amazonRating/* , values[7] */);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return book;
	}

	public void removeBook(long bId, long userId) {
//		System.out.println("removing bookmarks");
////		Collection<BookMark> result = new ArrayList<>();
//
//		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio", "root",
//				"majji@007"); Statement stmt = conn.createStatement()) {
////			System.out.println("kishore");
//			String query = "DELETE FROM user_book WHERE user_id= " + userId + " and book_id=" + bId;
//
//			System.out.println(query);
//			stmt.executeUpdate(query);
//
////			result = getBooks(true,userId);
////			System.out.println("kishore");
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
			Data.remove(bId);
	}

}
