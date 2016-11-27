package com.newsstand.servlets;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(LoginServlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("LoginServlet");
		response.sendRedirect("main.jsp");
	}
}
