package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeServet
 */
@WebServlet("/EmployeeServet")
public class EmployeeServet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Connection is being created..");
		try {
			// STEP 1: Load driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// STEP 2: Gets Connection Object using DriverManager
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jfsd", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			// STEP 3: Gets statement object
			Statement stmt = connection.createStatement();

			// STEP 4: Execute query and gets resultset object
			ResultSet rs = stmt.executeQuery("select * from employees");

			// STEP 5: Iterate resultset object
			out.println("<ul>");
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString(2);
				Integer age = rs.getInt(3);
				out.println("<li> ID: " + id + " Name :" + name + " Age: " + age + "</li>");
			}
			out.println("</ul>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");

		if ("create".equals(action)) {
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String sql = "insert into employees (name, age) values (? , ?)";

			try {
				PreparedStatement preparedStmt = connection.prepareStatement(sql);

				preparedStmt.setString(1, name);
				preparedStmt.setInt(2, age);

				preparedStmt.executeUpdate();
				out.println(" Employee Record added successfully!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("update".equals(action)) {

			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			int id = Integer.parseInt(request.getParameter("id"));

			String sql = "update employees set name = ?, age = ? where id = ?";

			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, name);
				pstmt.setInt(2, age);
				pstmt.setInt(3, id);

				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					out.println("<p> Employee updated successfully </p>");
				} else {
					out.println("<p> Employee Not Found!! </p>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String sql = "DELETE FROM employees where id = ?";

			try {
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, id);

				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					out.println("<p> Employee deleted successfully </p>");
				} else {
					out.println("<p> Employee Not Found!! </p>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if ("getById".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String sql = "{CALL GetEmployeeById(?)}";
			try {
				CallableStatement cstmt = connection.prepareCall(sql);
				cstmt.setInt(1, id);

				ResultSet rs = cstmt.executeQuery();
				out.println("<ul>");
				while (rs.next()) {
					out.println("<li> ID: " + rs.getInt("id") + " Name :" + rs.getString(2) + " Age: " + rs.getInt(3)
							+ "</li>");
				}
				out.println("</ul>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void destroy() {
		System.out.println("Connection is closed!");
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
