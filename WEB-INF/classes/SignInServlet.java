import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.util.DBConnection;

public class SignInServlet extends HttpServlet {

	private static final long serialVersionUID = 2134824700563833437L;
	Connection connection = null;
	PreparedStatement ps = null;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);
		out.println("<HTML>");
		out.println("  <BODY bgcolor=lightgrey text=cyan><center><h2>");
		ServletContext context = getServletContext();
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String user = context.getInitParameter("dbuser");
		String password = context.getInitParameter("dbpass");

		try {
			connection = DBConnection
					.getConnection(driver, url, user, password);
		} catch (Exception e) {
			if (e instanceof ClassNotFoundException) {
				out.println("<br> Class Driver Not Found First Load the Driver...");
				request.getRequestDispatcher("signup.html").include(request,
						response);
				e.printStackTrace();
			} else if (e instanceof SQLException) {
				out.println("<br> " + e.getMessage());
				request.getRequestDispatcher("signup.html").include(request,
						response);
				e.printStackTrace();
			}

		}

		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		HttpSession session = request.getSession(false);
		session.getAttribute("username");
		session.getAttribute("password");
		
		

		if (uname.equals("") || upass.equals("")) {
			out.println("<br><br> Please Enter data.");
		} else {
			if (uname.equals("admin") && upass.equals("admin")) {
				
				out.println("<br><br>UserName  is admin");
				out.println("<br><br>Address  is admin");
				out.println("<br><br> This is default account if you want to login with your details please SIGHUP");

				out.println("  </BODY> </HTML>");
			} else {
			if (uname.equals("username")&& upass.equals("password")) {
				
				out.println("<br><br> Successfully Login To WebPage....");
				out.print("<br >hai " + uname
						+ " welcome to our website");
			}
			else {
				out.println("<br >Invalid user");
			}	
					

				
			}

			out.flush();
			out.close();
		}
	}
}
