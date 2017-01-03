package ru.innopolis.uni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Igor on 03.01.2017.
 */
@Controller
public class CustomerController {
    HttpSession httpSession;
@RequestMapping(value = "/cart")
    public String getCart(Model model) {
        return "cart";
    }
@RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        request.getSession().removeAttribute("email");
        return "home";
    }
}
