package ru.innopolis.uni.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.pojo.Products;
import ru.innopolis.uni.model.service.CategoryService;
import ru.innopolis.uni.model.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Igor Ryabtsev on 29.12.2016.
 *  Класс  регулирует отображение названии категорий продуктов в пользовательском интерфейсе
 */
@Controller
public class CategoryController {

    private static Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    HttpSession session;

    @Autowired
    ProductService service;

    @Autowired
    CategoryService categoryService;

    /**
     * Пользователь запрашивает поиск продуктов по категории
     * @param model
     * @param subCategory Подкатегория
     * @param categoryName
     * @return
     */
    @RequestMapping(value = "/category")
    public String getCategory(Model model, @RequestParam("subcat")String subCategory,
                              @RequestParam("categ")String categoryName) throws Exception {


        // Если пользователь запрашивает поиск в подкатегории
        if (subCategory != null) {
            List<Products> categoryProducts = null;
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
        model.addAttribute("categories", categoryService.getCategoriesMap());
    return "category";
    }

    /**
     * Пользователь запрашивает информацию о продукте
     * @param model
     * @param productId
     * @param req
     * @return
     */
    @RequestMapping(value = "/product")
    public String getProduct(Model model,
                              @RequestParam("productId")int productId, HttpServletRequest req) throws Exception {
        Products product = null;
        try {
            product = (Products) service.getProductDetails(productId);
        } catch (DataBaseException e) {
            log.warn(e.message());
            return "error";
        }
        req.getSession().setAttribute("product", product);
        req.getSession().setAttribute("productID", productId);

        model.addAttribute("categories", categoryService.getCategoriesMap());

     model.addAttribute("productCategory",
                product.getCategoryByCategoryName().getIdcategory());
        model.addAttribute("productSubCategory",
                product.getSubcategoryBySubCategory().getIdsubCategory());
        return "product";
    }
}
