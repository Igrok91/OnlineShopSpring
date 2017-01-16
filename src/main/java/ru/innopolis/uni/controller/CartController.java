package ru.innopolis.uni.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.Product;
import ru.innopolis.uni.model.service.ProductService;
import ru.innopolis.uni.model.service.cart.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by  Igor Ryabtsev on 29.12.2016.
 * Класс  регулирует взаимодействие клиента с корзиной для покупок
 * Позволяет добавить, изменить или удалить корзину
 */
@Controller
public class CartController {

    private static Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    ProductService service;

    @Autowired
    HttpSession session;

    /**
     * Пользователь добавляет продукт в корзину
     * @param model
     * @param id   id продукта
     * @param request
     * @return
     */
    @RequestMapping(value = "/addProducts", method = RequestMethod.POST)
    public String addProduct(Model model,  @SessionAttribute("productID") int id, HttpServletRequest request) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            request.getSession().setAttribute("cart", cart);
        }

        Integer productID = new Integer(id);

        if (productID != null) {
            Product p = null;
            try {
                p = service.getProductDetails(productID);
            } catch (DataBaseException e) {
                log.warn(e.message());
                return "error";
            }
            cart.add(productID, p);

        }
        return "redirect:/home";
    }

    /**
     * Пользователь изменяет количство продуктов в корзине
     * @param model
     * @param producr_id id продукта
     * @param quantity Количество продукта
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(Model model, @RequestParam("productid")int producr_id, @RequestParam("quantity") int quantity) {
        Product product = null;
        try {
            product = (Product) service.getProductDetails(producr_id);
        } catch (DataBaseException e) {
            log.warn(e.message());
           return "error";
        }

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
             if (cart != null) {
            cart.updateQuantity(producr_id, quantity, product);
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeProduct(@RequestParam("pid")int producr_id) {

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            cart.remove(producr_id);
            return "redirect:/cart";
        }
    return "home";
    }
    @RequestMapping(value = "/login2", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login2");

        return model;

    }
    @RequestMapping(value = "/error2", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        /*//check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }
*/
        model.setViewName("error");
        return model;

    }
}
