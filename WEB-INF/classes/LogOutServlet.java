import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter k = response.getWriter();

		request.getRequestDispatcher("link.html").include(request, response);

		HttpSession session = request.getSession();

		k.print("<br>");
		if (session != null) {
			session.invalidate();
			k.print("<br>your session expired, please login");
			k.print("<br>fine");
		}
	
	}

}

