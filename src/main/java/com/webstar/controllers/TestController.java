package com.webstar.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webstar.services.EmailService;

@Controller
public class TestController
{
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private EmailService emailService;
  
    @ResponseBody
    @RequestMapping( value = "/testemail", method = RequestMethod.GET, produces = "application/json" )
    public String testEmail()
    {

        try {
           // logger.info("hello");
           emailService.sendMail("shrestha.asm@gmail.com", "hello", "hello", "shrestha.asmm@gmail.com");
            return "success";
        } catch (Exception ex) {
            LOG.info(ex.getMessage());
            return "failed";
        }

    }

}
