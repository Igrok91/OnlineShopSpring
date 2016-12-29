package ru.innopolis.uni.controller.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.service.CustomerService;
import ru.innopolis.uni.model.service.cart.ShoppingCart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс сервлета регулирует основные взаимодействия пользователя с интерфесом,
 * такие как регистрация, вход, покупка
 */
public class CustomerServlet extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(CustomerServlet.class);
    private HttpSession hs;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userPath = req.getServletPath();
        String getURL = "/" + userPath + ".jsp";
        if (userPath.equals("/checkout")) {
            hs = req.getSession();
            ShoppingCart cart = (ShoppingCart) hs.getAttribute("cart");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/checkout_unreg.jsp");
            rd.forward(req, resp);
            return;
        }// Если пользователь запрашивает Logout
        else if (userPath.equals("/logout")){
            req.getSession().invalidate();
            req.getSession().removeAttribute("email");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/home.jsp");
            rd.forward(req, resp);
            return;
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(getURL);
        rd.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String userPath = request.getServletPath();
        String postURL = "/" + userPath + ".jsp";
        // Если пользователь оплачивает продукт
        if (userPath.equals("/purchase")) {
            HttpSession s = request.getSession();
            ShoppingCart cart = (ShoppingCart) s.getAttribute("cart");
            cart.clear();
            response.sendRedirect("orderconfirm.jsp");
        }
        // Если пользователь регистрируется
        else if (userPath.equals("/register")) {
            CustomerService customerService = new CustomerService();
            String email = request.getParameter("inputEmail");
            String password = request.getParameter("password");
            boolean success = false;
            try {
                success = customerService.registerCustomer(email, password);
            } catch (DataBaseException e) {
                log.warn(e.message());
                response.sendRedirect("error.jsp");
            }

            if (success) {
                HttpSession hs = request.getSession();
                hs.setAttribute("regstatus", 1);
                response.sendRedirect("login.jsp?regStatus=Success");
            } else {
                response.sendRedirect("checkout_unreg.jsp?regStatus=Fail");
            }
        }
        // Если пользователь входит в систему
        else if (userPath.equals("/login")) {
            CustomerService customerService = new CustomerService();
            String email = request.getParameter("inputEmail");
            String password = request.getParameter("password");

            boolean flag = false;
            try {
                flag = customerService.verifyUser(email, password);
            } catch (DataBaseException e) {
                log.warn(e.message());
                response.sendRedirect("error.jsp");
            }
            if (flag) {
                HttpSession hs = request.getSession();
                hs.setAttribute("email", email);
                response.sendRedirect("final_checkout.jsp");
            } else {
                response.sendRedirect("login.jsp?regStatus=Fail");
            }

        }
    }
}
