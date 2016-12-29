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

/**
 * Created by innopolis on 29.12.2016.
 */
@Controller
@SessionAttributes("cart")
public class CartController {

    @Autowired
    ProductService service;
    private static Logger log = LoggerFactory.getLogger(CartController.class);

    @RequestMapping(value = "/addProducts", method = RequestMethod.POST)
    public String addProduct(Model model, @SessionAttribute("cart") ShoppingCart cart, @RequestParam("productID") int id) {
        if (cart == null) {
            cart = new ShoppingCart();
            model.addAttribute("cart", cart);
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
        return "redirect:/product";
    }
}
