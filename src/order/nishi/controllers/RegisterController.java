package order.nishi.controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import order.nishi.models.Product;
import order.nishi.models.ProductEntity;
import order.nishi.models.CartEntity;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get product list from database
        ProductEntity products = (new Product()).getProductEntity();
        request.setAttribute("products", products);

        // get order item list from session
        HttpSession session = request.getSession();
        CartEntity entity = (CartEntity) session.getAttribute("cartIn");
        request.setAttribute("cart", entity);

        response.setContentType("text/html; charset=Windows-31J");
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
            int productCode = Integer.parseInt(request.getParameter("code"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
    
            ProductEntity entity = (new Product()).makeProductEntityByProductCode(productCode);

            HttpSession session = request.getSession();
            if (session.getAttribute("cartIn") != null) {
                CartEntity cart = (CartEntity) session.getAttribute("cartIn");
                cart.addCode(entity.getCode());
                cart.addName(entity.getName());
                cart.addPrice(entity.getPrice());
                cart.addCount(quantity);
                cart.addSubTotal(entity.getPrice(), quantity);
                session.setAttribute("cartIn", cart);
            } else {
                CartEntity cart = new CartEntity();
                cart.addCode(entity.getCode());
                cart.addName(entity.getName());
                cart.addPrice(entity.getPrice());
                cart.addCount(quantity);
                cart.addSubTotal(entity.getPrice(), quantity);
                session.setAttribute("cartIn", cart);
            }
            response.sendRedirect("./register");

            // response.setContentType("text/html; charset=Windows-31J");
            // PrintWriter out = response.getWriter();
            // out.println(orders.getName());
    }
}
