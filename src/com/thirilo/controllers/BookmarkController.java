package com.thirilo.controllers;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import com.thirlilo.constants.KidFriendlyStatus;
import com.thirlo.entites.BookMark;
import com.thirlo.entites.User;
import com.thirlo.managers.BookMarkMannager;
import com.thirlo.managers.UserManager;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Servlet implementation class BookmarkController
 */

@WebServlet(urlPatterns = { "/bookmark", "/bookmark/save", "/bookmark/mybooks", "/bookmark/remove" })
public class BookmarkController extends HttpServlet {

	/*
	 * private static BookmarkController instance=new BookmarkController(); private
	 * BookmarkController() {} public static BookmarkController getInstance() {
	 * return instance; }
	 */
	public BookmarkController() {
		// TODO Auto-generated constructor stub
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("her in do get of bc");

		RequestDispatcher dispatcher = null;

		System.out.println("servlet path" + request.getServletPath());
		if (request.getSession().getAttribute("userId") != null) {

			System.out.println(request.getSession().getAttribute("userId") + " user ID");
			Long userId = (long) request.getSession().getAttribute("userId");
			System.out.println(userId + " user ID2");

			if (request.getServletPath().contains("remove")) {

				dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				String bid = request.getParameter("bid");

//				User user = UserManager.getInstance().getUser(userId);
//				BookMark book = BookMarkMannager.getInstance().getBook(Long.parseLong(bid));
				BookMarkMannager.getInstance().removeBook(Long.parseLong(bid), userId);

				Collection<BookMark> list = BookMarkMannager.getInstance().getBooks(false, userId);
				System.out.println(list);
				request.setAttribute("books", list);
			}
			if (request.getServletPath().contains("save")) {
				// save
				dispatcher = request.getRequestDispatcher("/mybooks.jsp");

				String bid = request.getParameter("bid");
				User user = UserManager.getInstance().getUser(userId);
				BookMark bookmark = BookMarkMannager.getInstance().getBook(Long.parseLong(bid));

				BookMarkMannager.getInstance().saveUserBookmark(user, bookmark);

				Collection<BookMark> list = BookMarkMannager.getInstance().getBooks(true, userId);

				request.setAttribute("books", list);

			} else if (request.getServletPath().contains("mybooks")) {
				// mybooks
				dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				Collection<BookMark> list = BookMarkMannager.getInstance().getBooks(true, userId);
				request.setAttribute("books", list);

			} else {
				dispatcher = request.getRequestDispatcher("/browse.jsp");
				Collection<BookMark> list = BookMarkMannager.getInstance().getBooks(false, userId);
				request.setAttribute("books", list);
				System.out.println(list);
//			  browse jsp kaha hai
				// 2. Forwarding to View
			}

		} else {
			dispatcher = request.getRequestDispatcher("/login.jsp");
		}

		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("her in do post of bc");

		doGet(request, response);

	}

	public void saveUserBookmark(User user, BookMark bookmark) {
		BookMarkMannager.getInstance().saveUserBookmark(user, bookmark);
	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, BookMark bookmark) {
		BookMarkMannager.getInstance().setKidfriendlyStatus( kidFriendlyStatus, bookmark,user);

	}

	public void share(User user, BookMark bookmark) {
		BookMarkMannager.getInstance().share( bookmark,user);

	}

}
