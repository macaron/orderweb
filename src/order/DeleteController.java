package order;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "DeleteController", urlPatterns = {"/DeleteController"})
public class DeleteController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			response.setContentType("text/html; charset=Windows-31J");
			String selectNumber = request.getParameter("selectNumber");
		
			//PrintWriter out = response.getWriter();
			//out.print(selectNumber);
			
			request.setAttribute("selectNumber", selectNumber);

			DeleteModel Model = new DeleteModel(selectNumber);
			Model.execute();
			request.setAttribute("Model", Model);
			
			getServletContext().getRequestDispatcher("/DeleteView2.jsp").forward(request, response);
	}
}
