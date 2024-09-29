package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParamsServlet
 */
@WebServlet(urlPatterns = "/InitParamsServlet",
			initParams =  {
					@WebInitParam(name="dbUrl", value="jdbc:mysql://localhost:3306/mydb"),
					@WebInitParam(name="adminEmail", value="admin!@example")
			}
		)
public class InitParamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitParamsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String dbUrl = this.getServletConfig().getInitParameter("dbUrl");
		String adminEmail = this.getServletConfig().getInitParameter("adminEmail");
		
		out.println("<h1> GET request received!</h1>");
		out.println("<p> DB Url: "+ dbUrl+"</p>");
		out.println("<p> Admin Email: "+ adminEmail +"</p>");
		
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
