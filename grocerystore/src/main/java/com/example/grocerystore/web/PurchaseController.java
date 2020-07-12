package com.example.grocerystore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PurchaseController {
    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView, HttpSession session) {
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/home")
    @RequestMapping("/home")
    public ModelAndView home(ModelAndView modelAndView, HttpSession session) {
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
