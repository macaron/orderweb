package order;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "DeleteHelperController", urlPatterns = {"/DeleteHelperController"})
public class DeleteHelperController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			response.setContentType("text/html; charset=Windows-31J");
			String selectNumber = request.getParameter("selectNumber");
		
			PrintWriter out = response.getWriter();
			//out.print(selectNumber);
			
			request.setAttribute("selectNumber", selectNumber);

			DeleteHelperModel Model = new DeleteHelperModel(selectNumber);
			Model.executeTitle();
			Model.executeDetail();
			request.setAttribute("Model", Model);
			
			getServletContext().getRequestDispatcher("/DeleteView1.jsp").forward(request, response);
	}
}
