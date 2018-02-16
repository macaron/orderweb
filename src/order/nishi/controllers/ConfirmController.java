package order.nishi.controllers;

import order.nishi.models.*;
import order.nishi.utils.MyValidatorUtil;
import order.nishi.utils.SessionClearUtil;

import java.io.*;
import java.util.Calendar;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ConfirmController", urlPatterns = {"/confirm"})
public class ConfirmController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get date
        Calendar cal = Calendar.getInstance();
        request.setAttribute("year", cal.get(Calendar.YEAR));
        request.setAttribute("month", cal.get(Calendar.MONTH));
        request.setAttribute("day", cal.get(Calendar.DATE));
        request.setAttribute("maximumDay", cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        // get customer list
        CustomerEntity customer = new Customer().getCustomerEntity();
        request.setAttribute("customers", customer);
        // get customer
        request.setAttribute("sales", new Sales().getSalesEntity());

        // get order item list from session
        HttpSession session = request.getSession();
        CartEntity cart = (CartEntity) session.getAttribute("cartIn");
        request.setAttribute("cart", cart);

        // get error message from session
        String error_msg = (String) session.getAttribute("error_msg");
        SessionClearUtil.clearSession(request, "error_msg");
        request.setAttribute("error_msg", error_msg);

        response.setContentType("text/html; charset=Windows-31J");
        request.getRequestDispatcher("/confirm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        int day = Integer.parseInt(request.getParameter("day"));
        int customer_c = Integer.parseInt(request.getParameter("customer_c"));
        String sales_c = request.getParameter("sales_c");

        HttpSession session = request.getSession();
        CartEntity product = (CartEntity) session.getAttribute("cartIn");

        // session hasn't items
        if (product == null) {
            session.setAttribute("error_msg", "登録する商品がありません");
            response.sendRedirect("./confirm");
        } else {
            // write to database after validation
            OrderWrite writer = new OrderWrite(year, month, day, customer_c, sales_c);
            // check date value
            if (MyValidatorUtil.validateDate(year, month, day)) {
                // write a order title to database
                writer.insertOrderTitle();
                for (int i = 0; i < product.getArrayTotal(); i++) {
                    writer.insertOrderDetail(product.getCode(i), product.getCount(i));
                }
                // update columns total_amount, sales_tax, bill
                writer.updateOrderTitle();

                // session clear
                SessionClearUtil.clearSession(request, "cartIn");

                RequestDispatcher dispatcher = request.getRequestDispatcher("complete.jsp");
                dispatcher.forward(request, response);
            } else {
                session.setAttribute("error_msg", "日付が不正です");
                response.sendRedirect("./confirm");
            }
        }
    }
}
