package com.thirilo.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thirlo.entites.BookMark;
import com.thirlo.entites.User;
import com.thirlo.managers.BookMarkMannager;
import com.thirlo.managers.UserManager;

/**
 * Servlet implementation class BookmarkController
 */
@WebServlet(urlPatterns = { "/bookmark", "/bookmark/save", "/bookmark/mybooks" })
public class BookmarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookmarkController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		System.out.println("servletpath  " + request.getServletPath());
		System.out.println("session userId"+request.getSession().getAttribute("userId"));
		if(request.getSession().getAttribute("userId")!=null) {
			System.out.println("session userId1"+request.getSession().getAttribute("userId"));
							Long userId=(long)request.getSession().getAttribute("userId");
			if (request.getServletPath().contains("save")) {
//				save
				dispatcher = request.getRequestDispatcher("/mybooks.jsp");

				String bid = request.getParameter("bid");

				User user = UserManager.getInstance().getUser(userId);
				BookMark book = BookMarkMannager.getInstance().getBook(Long.parseLong(bid));
				BookMarkMannager.getInstance().saveUserBookmark(user, book);
				Collection<BookMark> list = BookMarkMannager.getInstance().getBooks(true, userId);
				System.out.println(list);
				request.setAttribute("books", list);
			} else if (request.getServletPath().contains("mybooks")) {
				dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				
				Collection<BookMark> list = BookMarkMannager.getInstance().getBooks(true, userId);
				System.out.println(list);
				request.setAttribute("books", list);

			} else {
				dispatcher = request.getRequestDispatcher("/browse.jsp");
				Collection<BookMark> list = BookMarkMannager.getInstance().getBooks(false, userId);
				request.setAttribute("books", list);
			}
			
		}else {
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
		doGet(request, response);
	}
}
