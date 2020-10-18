package com.thirlo.managers;

import com.thirilo.dao.BookMarkDao;
import com.thirlilo.constants.BookGenre;
import com.thirlilo.constants.KidFriendlyStatus;
import com.thirlilo.constants.MovieGenre;
import com.thirlo.entites.*;

import java.util.Collection;

public class BookMarkMannager {
	private static BookMarkMannager instance = new BookMarkMannager();
	private static BookMarkDao dao = new BookMarkDao();

	private BookMarkMannager() {
	}

	public static BookMarkMannager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, int releaseYear, String[] cast, String[] directors,
			MovieGenre genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);

		return movie;

	}

	public Book createBook(long id, String title, String imageUrl, int publicationyear, String publisher,
			String[] authors, BookGenre genere, double amazonrating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setImageUrl(imageUrl);
		book.setAmazonRating(amazonrating);
		book.setAuthors(authors);
		book.setGenre(genere);
		book.setPublicationYear(publicationyear);
		book.setPublisher(publisher);

		return book;

	}

	public Weblink createWeblink(long id, String title, String url, String host, String profileUrl) {
		Weblink weblink = new Weblink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setProfileUrl(profileUrl);
		weblink.setUrl(url);
		weblink.setHost(host);

		return weblink;
	}

//	public List<List<BookMark>> getBookmarks() {
//		return dao.getBookmarks();
//	}

	public void saveUserBookmark(User user, BookMark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);

		dao.saveUserBookmark(userBookmark);

	}

	public void setKidfriendlyStatus(KidFriendlyStatus kidFriendlyStatus, BookMark bookmark, User user) {

		bookmark.setKidFriendlyMarkedBy(user);
		bookmark.setKidfriendlyStatus(kidFriendlyStatus);

		dao.updateKidFriendlyStatus(bookmark);

		System.out.println("kidfrendly status markedby.." + user.getFirstName() + "email.." + user.getEmail()
				+ "kidfriendly status " + kidFriendlyStatus + " , " + bookmark);
	}

	public void share(BookMark bookmark, User user) {
		bookmark.setSharedBy(user);
		System.out.println("data to be shared" + "sharedby" + user);
		if (bookmark instanceof Book) {
			System.out.println(((Book) bookmark).getItemData());
		} else if (bookmark instanceof Weblink) {
			System.out.println(((Weblink) bookmark).getItemData());
		}
		dao.sharedByInfo(bookmark);

	}

	public Collection<BookMark> getBooks(boolean isBookmarked, long userId) {
		return (Collection<BookMark>) dao.getBooks(isBookmarked, userId);
	}
	public void  removeBook(long bId, long userId) {
		dao.removeBook(bId, userId);
	}
	public BookMark getBook(long bid) {
		return BookMarkDao.getBook(bid);
		
	}
}