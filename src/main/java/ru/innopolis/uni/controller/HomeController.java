package ru.innopolis.uni.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.pojo.Products;
import ru.innopolis.uni.model.service.CategoryService;
import ru.innopolis.uni.model.service.ProductService;


import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Igor Ryabtsev on 29.12.2016.
 */
@Controller
public class HomeController {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductService service;

    @Autowired
    private  CategoryService categoryService;

    @Autowired
    private HttpSession httpSession;

    /**
     *
     * @param model
     * @return Возвращает домашнюю страницу
     */
    @RequestMapping(value = "/home")
   public String home(Model model) throws Exception {
    log.info("Запрос домашней страницы");
    List<Products> productsList = null;
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

    @RequestMapping(value = "/welcome")
    public String welcome(Model model) throws Exception {
        log.info("Домашняя страница");
        List<Products> productsList = null;
        try {
            productsList = service.getAllProducts();
        } catch (DataBaseException e) {
            log.warn(e.message());
            System.out.println("error");
            return "error";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            httpSession.setAttribute("email", userDetail.getUsername());
        }
        model.addAttribute("categories", categoryService.getCategoriesMap());
        model.addAttribute("productsList", productsList);
        return "home";
    }


}
