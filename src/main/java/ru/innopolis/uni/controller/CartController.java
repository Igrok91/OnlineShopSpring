package ru.innopolis.uni.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.Product;
import ru.innopolis.uni.model.service.ProductService;
import ru.innopolis.uni.model.service.cart.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by innopolis on 29.12.2016.
 */
@Controller
public class CartController {

    @Autowired
    ProductService service;
    private static Logger log = LoggerFactory.getLogger(CartController.class);
    @Autowired
    HttpSession session;

    @RequestMapping(value = "/addProducts", method = RequestMethod.POST)
    public String addProduct(Model model,  @SessionAttribute("productID") int id, HttpServletRequest request) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            request.getSession().setAttribute("cart", cart);
        }
        System.out.println(id);

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
        System.out.println(cart);
        if (cart != null) {
            cart.updateQuantity(producr_id, quantity, product);
        }
        return "redirect:/cart";
    }
}
