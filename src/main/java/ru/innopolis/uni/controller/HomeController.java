package ru.innopolis.uni.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.Product;
import ru.innopolis.uni.model.service.CategoryService;
import ru.innopolis.uni.model.service.CustomerService;
import ru.innopolis.uni.model.service.ProductService;

import java.util.List;

/**
 * Created by Igor Ryabtsev on 29.12.2016.
 */
@Controller
public class HomeController {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    ProductService service;

    @Autowired
    CategoryService categoryService;

    /**
     *
     * @param model
     * @return Возвращает домашнюю страницу
     */
    @RequestMapping(value = "/home")
   public String home(Model model){
    log.info("Запрос домашней страницы");
    List<Product> productsList = null;
    try {
        productsList = service.getAllProducts();
    } catch (DataBaseException e) {
        log.warn(e.message());
        System.out.println("error");
        return "error";
    }
    model.addAttribute("categories", categoryService.getCategoriesMap());
    model.addAttribute("productsList", productsList);
    return "home";
   }


}
