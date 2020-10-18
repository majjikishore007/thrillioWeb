package com.thirilo.controllers;

import com.thirilo.utill.StringUtil;
import com.thirlo.managers.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class AuthController
 */
@WebServlet(urlPatterns = {"/auth","/auth/logout"})
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("servletpath  " + request.getServletPath());
		
		if(!request.getServletPath().contains("logout")) {
//			login
			String email=request.getParameter("email");
//			String password=StringUtil.encodePassword(request.getParameter("password"));
			String password=request.getParameter("password");
			Long userId=UserManager.getInstance().authenticate(email, password);
			System.out.println("userID "+userId  );
			if(userId != -1) {
				System.out.println("userID   inside session "+userId  );
				HttpSession session=request.getSession();
					session.setAttribute("userId", userId);
					
					System.out.println("  mkk session userId"+(long)request.getSession().getAttribute("userId"));
				request.getRequestDispatcher("bookmark/mybooks").forward(request, response);
			}else {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}else {
//			logout
			request.getSession().invalidate();
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
