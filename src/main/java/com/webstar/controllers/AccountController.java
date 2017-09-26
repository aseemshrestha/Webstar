package com.webstar.controllers;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webstar.services.IUserService;
import com.webstar.util.Constants;
import com.webstar.util.Views;

@Controller
public class AccountController
{
    @Autowired
    private IUserService userService;
    // @Autowired
    //private RequestMappingHandlerAdapter reqMappingHandler;

    @RequestMapping( value = "/myAccount", method = RequestMethod.GET )
    public String myAccount(HttpServletRequest request, HttpServletResponse response, Model model)
        throws IOException
    {
        String nameEmail = userService.readNameEmailFromCookie(request);
        if (nameEmail.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("nameEmail", nameEmail);
        return Views.MY_ACCOUNT;

    }

    @RequestMapping( value = "/logout", method = RequestMethod.GET )
    public String logout(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        Cookie cookie = new Cookie(Constants.WEBSTAR_COOKIE_AUTH, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        //for browser back button
        // reqMappingHandler.setCacheSeconds(0);
        return "redirect:/";
    }

}
