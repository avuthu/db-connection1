import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.util.DBConnection;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement ps = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter k = response.getWriter();

		request.getRequestDispatcher("link.html").include(request, response);
		
		ServletContext context = getServletContext();
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String user = context.getInitParameter("dbuser");
		String password = context.getInitParameter("dbpass");
		try {
			connection = DBConnection.getConnection(driver, url, user, password);
		} catch (Exception e) {
			if (e instanceof ClassNotFoundException) {
				k.println("<br> Class Driver Not Found First Load the Driver...");
				request.getRequestDispatcher("signup.html").include(request, response);
				e.printStackTrace();
			} else if (e instanceof SQLException) {
				k.println("<br> " + e.getMessage());
				request.getRequestDispatcher("signup.html").include(request, response);
				e.printStackTrace();
			}

		}


		HttpSession session=request.getSession();
		
		k.print("<br>");
		if (session == null) {
			session.invalidate();
			k.print("<br>your session expired, please login");
		
		}
		else {
//			k.print("<br> session contain value is "
//					+ session.getAttribute("ename"));
//			k.print("<br> session contain value is "
//					+ session.getAttribute("pass"));
//			k.print("<br> session contain value is "
//							+ session.getAttribute("uname"));
//			k.print("<br> session contain value is "
//					+ session.getAttribute("uname"));
//			k.print("<br> session contain value is "
//					+ session.getAttribute("uname"));
//			String query = "select * from account where uname = ?";
//			try {
//				ps = connection.prepareStatement(query);
//				ps.setString(1, "raja");
//				ResultSet rs = ps.executeQuery();
//
//				rs.next();
//				String username = rs.getString(1);
//				String pass = rs.getString(2);
//				String gender = rs.getString(3);
//				String addr = rs.getString(4);
//				String hobbies = rs.getString(5);
//			
//			if (ps.executeUpdate() > 0) {
				k.println("<br>User is Successfully Registered....");
				//k.println("<br>Heloo"  +username+ "your details are");
				k.println("<br>username---->"+ session.getAttribute("ename"));
				k.println("<br>password---->sorry we maintain this confidential");
				//k.println("<br>gender---->"+gender);
				//k.println("<br>addr---->"+addr);
				//k.println("<br>hobbies---->"+hobbies);
			//}
		//} 
				//catch (Exception e) {
			//k.println("<br> Invalid Data Try Again");
			//request.getRequestDispatcher("signup.html").include(request, response);
			//e.printStackTrace();
		}

		
		
	
	}

}

//}
