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
 * Created by IgorRyabtsev on 28.12.2016.
 * Класс сервлета регулирует отображение названии категорий продуктов в пользовательском интерфейсе
 */
public class CategoryServlet extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(CategoryServlet.class);
    private HttpSession hs;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userPath = req.getServletPath();
        String getURL = "/" + userPath + ".jsp";
        ProductService service = new ProductService();

        //  Если пользователь запрашивает поиск категории
        if (userPath.equals("/category")) {
            String subCategory = req.getParameter("subcat");
            String categoryName = req.getParameter("categ");
            if (categoryName != null) {
                List<Product> productsCategoryList = null;
                try {
                    productsCategoryList = service.getProductByCategory(categoryName);
                } catch (DataBaseException e) {
                    log.warn(e.message());
                    resp.sendRedirect("error.jsp");
                }
                req.setAttribute("productByCategory", productsCategoryList);
            }

            // Если пользователь запрашивает поиск в подкатегории
            if (subCategory != null) {
                List<Product> categoryProducts = null;
                String cat = null;
                try {
                    categoryProducts = service.getProductBySubCategory(subCategory);
                    cat = service.getCategoryBySubCategory(subCategory);
                } catch (DataBaseException e) {
                    log.warn(e.message());
                    resp.sendRedirect("error.jsp");
                }
                getServletContext().setAttribute("categoryProducts",
                        categoryProducts);
                getServletContext().setAttribute("cat", cat);
            }

            getServletContext().setAttribute("subCat", subCategory);
        }  // Если пользователь запрашивает продукт
        else if (userPath.equals("/product")) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            Product product = null;
            try {
                product = (Product) service.getProductDetails(productId);
            } catch (DataBaseException e) {
                log.warn(e.message());
                resp.sendRedirect("error.jsp");
            }
            hs = req.getSession();
            hs.setAttribute("product", product);
            hs.setAttribute("productID", productId);

            getServletContext().setAttribute("productCategory",
                    product.getCategoryName().getCategoryid());
            getServletContext().setAttribute("productSubCategory",
                    product.getSubCategory().getCategoryid());
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(getURL);
        rd.forward(req, resp);
    }
}
