package com.thirilo.dao;




import com.thirlilo.constants.BookGenre;
import com.thirlilo.constants.Gender;
import com.thirlilo.constants.UserType;
import com.thirlo.entites.Book;
import com.thirlo.entites.BookMark;
import com.thirlo.entites.User;
import com.thirlo.entites.UserBookmark;
import com.thirlo.managers.BookMarkMannager;
import com.thirlo.managers.UserManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Data {
	public static List<User> users = new ArrayList<>();

	public static List<User> getUsers() {
		return users;
	}

	private static List<BookMark> bookmarks = new ArrayList<>();

	public static List<BookMark>getBookmarks() {
		return bookmarks;
	}

//	private static List<UserBookmark> userBookmarks = new ArrayList<>();

	private static List<BookMark> user_bookmarked=new ArrayList<>();
/*
Walden	https://images.gr-assets.com/books/1465675526l/16902.jpg	1854	1	6	4.3	2
Self-Reliance and Other Essays	https://images.gr-assets.com/books/1520778510l/123845.jpg	1993	2	6	4.5	2
Light From Many Lamps	https://images.gr-assets.com/books/1347739312l/1270698.jpg	1988	3	6	5	2
Head First Design Patterns	https://images.gr-assets.com/books/1408309444l/58128.jpg	2004	4	10	4.5	0	3	5
Effective Java Programming Language Guide	https://images.gr-assets.com/books/1433511045l/105099.jpg	2007	5	10	4.9	1	3	5



shant re ha

 */

	public static void loadBooks(){
	Book bookmarks1=BookMarkMannager.getInstance().createBook(4000,"Walden","https://images.gr-assets.com/books/1465675526l/16902.jpg" ,1854,"Wilder Publications",new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY,4.3);
		Book bookmarks2=BookMarkMannager.getInstance().createBook(  4001,"Self-Reliance and Other Essays","https://images.gr-assets.com/books/1520778510l/123845.jpg", 1993,"Dover Publications",new String[]{"Dover Publications Ralph Waldo Emerson"},BookGenre.PHILOSOPHY,4.5);
		Book bookmarks3=BookMarkMannager.getInstance().createBook(4002,"Light From Many Lamps","https://images.gr-assets.com/books/1347739312l/1270698.jpg",1988,"Touchstone",new
				String[]{"Lillian Eichler Watson"},BookGenre.PHILOSOPHY,5.0);
		Book bookmarks4=BookMarkMannager.getInstance().createBook(4003,
				"Head First Design Patterns","https://images.gr-assets.com/books/1408309444l/58128.jpg",2004,"O'Reilly Media",new String[]
						{"Eric Freeman","Bert Bates","Kathy Sierra","Elisabeth Robson"},BookGenre.
						TECHNICAL,4.5);

		Book bookmarks5=BookMarkMannager.getInstance().createBook(4004,"Effective Java Programming Language Guide","https://images.gr-assets.com/books/1433511045l/105099.jpg",2007,"Prentice Hall",new
				String[] {"Joshua Bloch"},BookGenre.TECHNICAL,4.9);

		bookmarks.add(bookmarks1);
		bookmarks.add(bookmarks2);
		bookmarks.add(bookmarks3);
		bookmarks.add(bookmarks4);
		bookmarks.add(bookmarks5);




	}

	public static  void loadUser() {
		System.out.println("load users");
		User user1=UserManager.getInstance().createUser(4, "user4@semanticsquare.com", "test", "Dheeru","M",Gender.MALE,UserType.CHIEF_EDITOR);
				users.add(user1);
				System.out.println(users.size());
				System.out.println(users);
				
//				users[0]=UserManager.getInstance().createUser( 1000
//			  ,"user0@semanticsquare.com","test", "Jon", "M",Gender.MALE,UserType.USER);
//			  users=UserManager.getInstance().createUser(1001,
//			  "user1@semanticsquare.com", "test" ,"Sam", "M",Gender.MALE,UserType.USER);
//			  users[2]=UserManager.getInstance().createUser(1002,
//			  "user2@semanticsquare.com", "test", "Anita","M",Gender.MALE,UserType.EDITOR);
//			  users[3]=UserManager.getInstance().createUser(1003,
//			  "user3@semanticsquare.com", "test", "Sara"
//			  ,"M",Gender.FEMALE,UserType.EDITOR);
//			  users[4]=UserManager.getInstance().createUser(1004,
//			  "user4@semanticsquare.com", "test",
//			  "Dheeru","M",Gender.MALE,UserType.CHIEF_EDITOR);
	}
			
	public static long authenticate(String email, String password) {
		loadUser();
		loadBooks();
		User user=users.get(0);
		return user.getId();
	
	}

	public static User getUser(long userId) {
		User user=users.get(0);
		if(userId==user.getId()) {
			return user;
		}
		return null;
	}

	public static BookMark getBook(long bookId) {

		for(BookMark book: bookmarks){
			if(book.getId()==bookId){
			return book;
			}
		}
		return  null;
	}

	public static void saveUserBook(UserBookmark userBookmark) {
		Book book= (Book) userBookmark.getBookmark();
		user_bookmarked.add(book);
		bookmarks.remove(book);
	}

	public static Collection<BookMark> getSaved() {
		return user_bookmarked;
	}

	public static Collection<BookMark> getUnSaved() {
		return  bookmarks;
	}

	public static void remove(long bId) {
		System.out.println(bId+" remove");
		for(BookMark us:user_bookmarked){
			System.out.println(us.getId()+"id ");
			if(us.getId()==bId){

				user_bookmarked.remove(us);
				bookmarks.add(us);
				break;
			}
		}
	}
}
