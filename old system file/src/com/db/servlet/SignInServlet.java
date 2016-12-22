package com.db.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SignInServlet extends GenericServlet {

	private static final long serialVersionUID = 2134824700563833437L;

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("  <BODY bgcolor=yellow text=red><center><h2>");
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		if (uname.equals("") || upass.equals("")) {
			out.println("<br><br> Please Enter data.");
		} else {
			if (uname.equals("admin") && upass.equals("admin")) {
				out.println("<br><br>U R Successfully Login Ur WebPage....");
				out.println("<br><br> Ur Details Are.....");
				out.println("<br><br>UserName  is " + uname);
				out.println("<br><br>Address  is " + upass);
				out.println("<br><br> Ok Bye Have A Nice Day...");
			} else {
				out.println("<br><br> This in Invalid Password...");
			}
		}

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
