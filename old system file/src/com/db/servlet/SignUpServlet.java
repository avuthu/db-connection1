package com.db.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.db.util.DBConnection;

public class SignUpServlet extends GenericServlet {

	private static final long serialVersionUID = -678214511292710636L;

	private Connection connection = null;
	private PreparedStatement ps = null;

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("  <BODY bgcolor=yellow text=red><center><h2>");

		ServletContext context = getServletContext();
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String user = context.getInitParameter("dbuser");
		String password = context.getInitParameter("dbpass");

		try {
			connection = DBConnection.getConnection(driver, url, user, password);
		} catch (Exception e) {
			if (e instanceof ClassNotFoundException) {
				out.println("<br> Class Driver Not Found First Load the Driver...");
				request.getRequestDispatcher("signup.html").include(request, response);
				e.printStackTrace();
			} else if (e instanceof SQLException) {
				out.println("<br> " + e.getMessage());
				request.getRequestDispatcher("signup.html").include(request, response);
				e.printStackTrace();
			}

		}

		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass1");
		String upass2 = request.getParameter("upass2");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String hobbies[] = request.getParameterValues("hobbies");
		String role = request.getParameter("role");
		String hobbie = "";

		if (hobbies != null && hobbies.length > 0) {
			int count = 0;
			for (String string : hobbies) {
				if (count == 0) {
					hobbie = hobbie + string;
				} else {
					hobbie = hobbie + ", " + string;
				}
				count++;
			}
		}

		if (uname.equals("")) {
			out.println("<br> please enter user name");
			request.getRequestDispatcher("signup.html").include(request, response);
		} else if (upass.equals("")) {
			out.println("<br> please enter password");
			request.getRequestDispatcher("signup.html").include(request, response);
		} else if (upass2.equals("")) {
			out.println("<br> please enter Confirm password");
			request.getRequestDispatcher("signup.html").include(request, response);
		} else if (!upass.equals(upass2)) {
			out.println("<br> Password MisMatch Enter Correct password in both fields");
			request.getRequestDispatcher("signup.html").include(request, response);
		} else if (gender.equals("")) {
			out.println("<br> please select gender");
			request.getRequestDispatcher("signup.html").include(request, response);
		} else if (address.equals("")) {
			out.println("<br> please enter address");
			request.getRequestDispatcher("signup.html").include(request, response);
		} else if (hobbies.equals("")) {
			out.println("<br> please select hobbies");
			request.getRequestDispatcher("signup.html").include(request, response);
		} else if (role.equals("")) {
			out.println("<br> please select expected role");
			request.getRequestDispatcher("signup.html").include(request, response);
		} else {
			String query = "insert into account values(?,?,?,?,?,?)";
			try {
				ps = connection.prepareStatement(query);
				ps.setString(1, uname);
				ps.setString(2, upass);
				ps.setString(3, gender);
				ps.setString(4, address);
				ps.setString(5, hobbie);
				ps.setString(6, role);

				if (ps.executeUpdate() > 0) {
					out.println("<br>User is Successfully Registered....<br>Please login with your credintials");
					out.println("<br><a href='./signup.html'>SignUp Form</a>");
					out.println("		<a href='./signin.html'>Signin Form</a>");
				}
			} catch (Exception e) {
				out.println("<br> Invalid Data Try Again");
				request.getRequestDispatcher("signup.html").include(request, response);
				e.printStackTrace();
			}

		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
