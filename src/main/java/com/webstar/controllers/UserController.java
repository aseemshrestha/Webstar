package com.webstar.controllers;

import java.io.UnsupportedEncodingException;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webstar.models.UserDetails;
import com.webstar.services.IEmailService;
import com.webstar.services.IUserService;
import com.webstar.util.Constants;
import com.webstar.util.Roles;
import com.webstar.util.Utils;
import com.webstar.util.Views;

@Controller
public class UserController
{
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    public static final String CHARSET = "ISO-8859-1";

    @Autowired
    private IUserService userService;
    @Autowired
    private IEmailService emailService;

    @RequestMapping( "/" )
    public String home()
    {
        return Views.HOME_PAGE;
    }

    @RequestMapping( "/about" )
    public String about()
    {
        return Views.ABOUT_PAGE;

    }

    @RequestMapping( value = "/myhome", method = RequestMethod.POST )
    public String myhome(String email, String password, Model model, HttpServletResponse response)
    {
        UserDetails user = userService.isUserAuthenticated(email, password);
        if (user == null) {
            model.addAttribute("loginError", Constants.LOGIN_FAIL_MSG);
            return Views.HOME_PAGE;
        } else {
            String cookieValue = email + "_" + user.getFirstName();
            String encodedCookie = "";
            try {
                encodedCookie = new String(Base64.encodeBase64(cookieValue.getBytes(CHARSET)));
            } catch (UnsupportedEncodingException e) {}
            response.addCookie(new Cookie(Constants.WEBSTAR_COOKIE_AUTH, encodedCookie));
            // if cookie is disabled, hidden form to maintain session
            model.addAttribute("nameEmail", email + "_" + user.getFirstName());
            return Views.MY_HOME_PAGE;
        }
    }

    @RequestMapping( value = "/register", method = RequestMethod.GET )
    public ModelAndView register(ModelAndView modelAndView, @ModelAttribute( "userDetails" ) UserDetails userDetails,
        HttpServletRequest request)
    {
        modelAndView.setViewName(Views.REGISTRATION_PAGE);
        modelAndView.getModel().put("ipAddress", Utils.getClientIp(request));
        return modelAndView;

    }

    @RequestMapping( value = "/register", method = RequestMethod.POST )
    public ModelAndView register(ModelAndView modelAndView,
        @ModelAttribute( "userDetails" ) @Valid UserDetails userDetails, BindingResult result)
    {

        boolean isValidPassword = true;
        boolean isEmailValid = true;
        boolean isPhoneValid = true;
        modelAndView.setViewName(Views.REGISTRATION_PAGE);
        UserDetails user = userService.findUserbyEmail(userDetails.getEmail());
        if (user != null) {
            modelAndView.getModel().put("emailExists", Constants.EMAIL_EXISTS);
        } else {

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
            userDetails.setRole(Roles.ROLE_USER);
            if (!userDetails.getPassword().equals(userDetails.getPasswordConfirm())) {
                modelAndView.getModel().put("passwordMismatch", "Password mismatch");
                isValidPassword = false;
            }
            if (!Utils.emailValidator(userDetails.getEmail())) {
                modelAndView.getModel().put("invalidEmail", "Invalid email");
                isEmailValid = false;
            }
            if (userDetails.getPhone().length() >= 1) {
                if (!Utils.validatePhoneNumber(userDetails.getPhone())) {
                    modelAndView.getModel().put("invalidPhone", "Invalid Phone number");
                    isPhoneValid = false;
                }

            }
            if (!result.hasErrors() && isValidPassword && isEmailValid && isPhoneValid) {
                try {
                    userService.save(userDetails);
                    modelAndView.getModel().put("userDetails", new UserDetails());
                    modelAndView.getModel().put("registrationSuccess", Constants.REGISTRATION_SUCCESS_MSG);
                    emailService.sendMail(userDetails.getEmail(), "Registration Confirmation with Webstar",
                                          "Dear " + Utils.upperCaseFirst(userDetails.getFirstName()) + " ,\n"
                                              + Constants.REGISTRATION_MESSAGE,
                                          "support@webstar.com");
                } catch (Exception ex) {
                    LOG.debug("[UserController-Register] Error while saving to db", ex);
                }
            }

        }
        return modelAndView;

    }

}
