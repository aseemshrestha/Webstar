package com.webstar.controllers;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webstar.services.IUserService;
import com.webstar.util.Constants;
import com.webstar.util.Views;

@Controller
public class AccountController
{
    @Autowired
    private IUserService service;

    @RequestMapping( "/myAccount" )
    public String myAccount(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String email = service.readEmailFromCookie(request);
        if (email.isEmpty()) {
            response.sendRedirect("/");
        }
        
        return Views.MY_ACCOUNT;

    }

    @RequestMapping( "/logout" )
    public String logout(HttpServletRequest request,HttpServletResponse response)
    {
        Cookie cookie = new Cookie(Constants.WEBSTAR_COOKIE_AUTH, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        //for browser back button
        response.setHeader(request.getHeader("referrer"), "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        return Views.HOME_PAGE;
    }
}
