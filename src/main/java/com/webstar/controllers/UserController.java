package com.webstar.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webstar.models.UserDetails;
import com.webstar.services.IUserService;
import com.webstar.util.Constants;
import com.webstar.util.Utils;

@Controller
public class UserController
{
    @Autowired
    private IUserService userService;

    @RequestMapping( "/" )
    public String home()
    {

        return "webstar.home";

    }

    @RequestMapping( "/about" )
    public String about()
    {
        return "webstar.about";

    }

    @RequestMapping( value = "/register", method = RequestMethod.GET )
    public ModelAndView register(ModelAndView modelAndView, @ModelAttribute( "userDetails" ) UserDetails userDetails,
        HttpServletRequest request)
    {
        modelAndView.setViewName("webstar.register");
        modelAndView.getModel().put("ipAddress", Utils.getClientIp(request));
        return modelAndView;

    }

    @RequestMapping( value = "/register", method = RequestMethod.POST )
    public ModelAndView register(ModelAndView modelAndView,
        @ModelAttribute( "userDetails" ) @Valid UserDetails userDetails,
        BindingResult result)
    {
        boolean isValidPassword = true;
        userDetails.setFirstName(userDetails.getFirstName());
        userDetails.setLastName(userDetails.getLastName());
        userDetails.setEmail(userDetails.getEmail());
        userDetails.setPassword(userDetails.getPassword());
        userDetails.setPasswordConfirm(userDetails.getPasswordConfirm());
        userDetails.setPhone(userDetails.getPhone());
        userDetails.setIpAddress(userDetails.getIpAddress());
        userDetails.setUserStatus(Constants.ACTIVE);
        userDetails.setRegistrationDate(new Date());
        userDetails.setLastLoggedIn(new Date());
        if (!userDetails.getPassword().equals(userDetails.getPasswordConfirm())) {
            modelAndView.getModel().put("passwordMismatch", "Password mismatch");
            isValidPassword = false;
        }
        if (!result.hasErrors() && isValidPassword) { // custom validator required but for now
            userService.save(userDetails);
            modelAndView.getModel().put("userDetails", new UserDetails());//empty the form
            modelAndView.getModel().put("registrationSuccess", Constants.REGISTRATION_SUCCESS_MSG);
        }
        modelAndView.setViewName("webstar.register");

        return modelAndView;

    }

}
