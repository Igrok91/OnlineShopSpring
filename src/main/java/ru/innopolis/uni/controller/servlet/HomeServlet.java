package ru.innopolis.uni.controller.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.Product;
import ru.innopolis.uni.model.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс сервлета, возвращающий домашнюю страницу
 */
public class HomeServlet extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(HomeServlet.class);
    private HttpSession hs;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService service = new ProductService();
        String userPath = req.getServletPath();
        String getURL = "/" + userPath + ".jsp";
        if (userPath.equals("/home")) {
            log.info("Запрос домашней страницы");
            List<Product> productsList = null;
            try {
                productsList = service.getAllProducts();
            } catch (DataBaseException e) {
                log.warn(e.message());
                resp.sendRedirect("error.jsp");
            }
            getServletContext().setAttribute("productsList", productsList);

        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(getURL);
        rd.forward(req, resp);
    }
}
