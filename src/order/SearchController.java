package order;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import org.apache.commons.lang3.*;

@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			response.setContentType("text/html; charset=Windows-31J");
			
			String searchWord = request.getParameter("searchWord");
			searchWord = new String(searchWord.getBytes("8859_1"), "Windows-31J");
			
			//PrintWriter out = response.getWriter();
			//out.print(searchWord);
			
			request.setAttribute("searchWord",searchWord);
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchView2.jsp");
            //dispatcher.forward(request, response);
			
			SearchModel model = new SearchModel(searchWord);
			model.execute();
			request.setAttribute("model", model);
			getServletContext().getRequestDispatcher("/SearchView2.jsp").forward(request, response);

			
			
	}
}
