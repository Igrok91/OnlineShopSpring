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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.service.CustomerService;
import ru.innopolis.uni.model.service.cart.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Igor Ryabtsev  on 03.01.2017.
 * Класс  регулирует основные взаимодействия пользователя с интерфесом,
 * такие как регистрация, вход, покупка
 */
@Controller
public class CustomerController {
    private static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    HttpSession httpSession;

    @Autowired
    CustomerService customerService;

    /**
     * @param model
     * @return Имя представления для пользовательской корзины
     */
    @RequestMapping(value = "/cart")
    public String getCart(Model model) {
        return "cart";
    }

    /**
     * Выход из профиля
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        request.getSession().removeAttribute("email");
        return "redirect:/home";
    }

    /**
     * Проверяет регистрацию пользователя
     * @return
     */
    @RequestMapping(value = "/checkout", method = {RequestMethod.POST, RequestMethod.GET})
    public String checkout(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            httpSession.setAttribute("email", userDetail.getUsername());
        }
        return "checkout_unreg";
    }


    @RequestMapping(value = "/final_checkout")
    public String checkoutFinal() {
        return "final_checkout";
    }

    /**
     * Оплата продукта
     * @return
     */
    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public String orderConfirm() {

        ShoppingCart cart = (ShoppingCart) httpSession.getAttribute("cart");
        cart.clear();
        return "orderconfirm";
    }

    /**
     * Пользователь регистрируется в системе
     * @return
     */
    @RequestMapping(value = "/login-register")
    public String login() {
        return "login-register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @RequestParam("inputEmail")String email, @RequestParam("password")String password,
                                    HttpServletRequest request) {
        boolean success = false;
        try {
            success = customerService.registerCustomer(email, password);
        } catch (DataBaseException e) {
            log.warn(e.message());
            return "error";
        }

        if (success) {
            httpSession.setAttribute("regstatus", 1);
            httpSession.setAttribute("regStatus", "Success");
            return "redirect:/login-register";
        } else {
            model.addAttribute("regStatus", "Fail");
            return "checkout_unreg";
        }

    }

}
