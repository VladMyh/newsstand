package com.newsstand.servlets;

import com.newsstand.command.MainPageCommand;
import com.newsstand.command.ServletCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(Servlet.class);
	private HashMap<String, ServletCommand> commands;

	@Override
	public void init() throws ServletException {
		commands = new HashMap<String, ServletCommand>();
		commands.put("", new MainPageCommand());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		logger.info("");
		response.sendRedirect("main.jsp");
		/*try {
			response.sendRedirect(commands.get(request.getContextPath()).execute(request, response));
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.sendRedirect("error.jsp");
		}*/
	}
}
