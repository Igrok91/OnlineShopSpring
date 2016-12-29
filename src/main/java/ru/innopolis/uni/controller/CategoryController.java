package ru.innopolis.uni.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.Product;
import ru.innopolis.uni.model.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by innopolis on 29.12.2016.
 */
@Controller
public class CategoryController {
    @Autowired
    HttpSession session;
    private static Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    ProductService service;
@RequestMapping(value = "/category")
    public String getCategory(Model model, @RequestParam("subcat")String subCategory,
                              @RequestParam("categ")String categoryName){
        if (categoryName != null) {
            List<Product> productsCategoryList = null;
            try {
                productsCategoryList = service.getProductByCategory(categoryName);
            } catch (DataBaseException e) {
                log.warn(e.message());
                return "error";
            }
            model.addAttribute("productByCategory", productsCategoryList);
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
                return "error";
            }
            model.addAttribute("categoryProducts",
                    categoryProducts);
            model.addAttribute("cat", cat);

        }
        model.addAttribute("subCat", subCategory);
    return "category";
    }

    @RequestMapping(value = "/product")
    public String getProduct(Model model,
                              @RequestParam("productId")int productId, HttpServletRequest req){
        Product product = null;
        try {
            product = (Product) service.getProductDetails(productId);
        } catch (DataBaseException e) {
            log.warn(e.message());
            return "error";
        }
        session = req.getSession();
        session.setAttribute("product", product);
        session.setAttribute("productID", productId);

        model.addAttribute("productCategory",
                product.getCategoryName().getCategoryid());
        model.addAttribute("productSubCategory",
                product.getSubCategory().getCategoryid());
        return "product";
    }


}
