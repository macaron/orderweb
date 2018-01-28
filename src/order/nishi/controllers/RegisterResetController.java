package order.nishi.controllers;

import order.nishi.utils.SessionClearUtil;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "RegisterResetController", urlPatterns = {"/reset"})
public class RegisterResetController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionClearUtil.clearSession(request, "cartIn");
        response.sendRedirect("./register");
    }

}
