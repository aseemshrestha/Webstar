package com.webstar.controllers;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.webstar.models.UserComments;
import com.webstar.models.UserDetails;
import com.webstar.models.UserSubmissions;
import com.webstar.services.IEmailService;
import com.webstar.services.ISubmissionService;
import com.webstar.services.IUserService;
import com.webstar.util.Categories;
import com.webstar.util.Constants;
import com.webstar.util.Roles;
import com.webstar.util.Utils;
import com.webstar.util.Views;

@Controller
public class UserController
{
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    public static final String CHARSET = "ISO-8859-1";
    private Map<String, List<UserSubmissions>> postsMap = new HashMap<>();
    private static int OFFSET = 0;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISubmissionService subService;

    @Autowired
    private IEmailService emailService;

    @RequestMapping( "/" )
    public String home(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException
    {
        if (postsMap.isEmpty()) {
            postsMap.put("recent-posts", subService.getRecentPostsDesc(16, OFFSET).get());
        }
        model.addAttribute("recentPostsHome", postsMap);
        return Views.HOME_PAGE;
    }

    @RequestMapping( "/about" )
    public String about()
    {

        return Views.ABOUT_PAGE;
    }

    @RequestMapping( value = "/reset", method = RequestMethod.GET )
    public ModelAndView resetPasswordPage(ModelAndView modelAndView, @RequestParam( "token" ) String token)
    {

        Optional<UserDetails> user = userService.findUserbyToken(token);
        if (user.isPresent()) {
            modelAndView.addObject("resetToken", token);
        } else {
            modelAndView.addObject("resetTokenError", "Oops ! Password reset link is either expired or invalid !");
        }
        modelAndView.setViewName(Views.RESET_PASSWORD);
        return modelAndView;
    }

    @RequestMapping( value = "/register", method = RequestMethod.GET )
    public ModelAndView register(ModelAndView modelAndView, @ModelAttribute( "userDetails" ) UserDetails userDetails,
        HttpServletRequest request)
    {

        modelAndView.setViewName(Views.REGISTRATION_PAGE);
        modelAndView.getModel().put("ipAddress", Utils.getClientIp(request));
        return modelAndView;
    }

    @RequestMapping( value = "/forgotpassword", method = RequestMethod.GET )
    public String forgotPassword()
    {

        return Views.FORGOT_PASSWORD;
    }

    @RequestMapping( value = "/forgotpassword", method = RequestMethod.POST )
    public String forgotPassword(String email, Model model, HttpServletRequest request)
    {
        Optional<UserDetails> user = userService.findUserbyEmail(email);
        if (!user.isPresent()) {
            model.addAttribute("noaccount", "Cannot find the user with email:" + email);
        } else {
            UserDetails userObj = user.get();
            userObj.setResetToken(UUID.randomUUID().toString());
            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            String msg = "To reset your password, click the link below:\n" + appUrl
                + "/reset?token=";
            try {
                userService.save(userObj);
                emailService.sendMail(userObj.getEmail(), "Password Reset Request",
                                      msg + userObj.getResetToken() + "\n\n Webstar Support Team ",
                                      "webstar@support.com");
                model.addAttribute("passwordResetLink",
                                   "Password reset link has been set to your email address:" + userObj.getEmail());
            } catch (Exception ex) {
                LOG.debug("[UserController][forgotPassword]Cannot save user to db:", ex);
            }
        }
        return Views.FORGOT_PASSWORD;
    }

    @RequestMapping( value = "/myhome", method = { RequestMethod.GET, RequestMethod.POST } )
    public String myhome(String email, String password, Model model, HttpServletResponse response,
        HttpServletRequest request)
    {
        Optional<UserDetails> user = userService.isUserAuthenticated(email, password);
        if (!user.isPresent()) {
            return "redirect:/?loginError=true";
        } else {
            String cookieValue = email + Constants.COOKIE_SEPARATOR + user.get().getFirstName() + " "
                + user.get().getLastName() + Constants.COOKIE_SEPARATOR + user.get().getId();
            String encodedCookie = "";
            try {
                userService.updateLastLoggedTime(new Date(), email);
                encodedCookie = new String(Base64.encodeBase64(cookieValue.getBytes(CHARSET)));
            } catch (UnsupportedEncodingException e) {}
            response.addCookie(new Cookie(Constants.WEBSTAR_COOKIE_AUTH, encodedCookie));
            return "redirect:/myhomepage";
        }

    }

    @RequestMapping( value = "/myhomepage", method = { RequestMethod.GET, RequestMethod.POST } )
    public String myhomepage(Model model, HttpServletRequest request)
    {
        String nameEmail = userService.readNameEmailFromCookie(request);
        if (nameEmail.isEmpty()) {
            return "redirect:/?loginError=true";

        }
        model.addAttribute("categories", Categories.getCategories());
        model.addAttribute("recentPosts", subService.getRecentPostsDesc(Constants.BLOCKSIZE, OFFSET).get());
        model.addAttribute("usersubmissions", new UserSubmissions());
        model.addAttribute("usercomments", new UserComments());
        model.addAttribute("nameEmail", nameEmail);
        return Views.MY_HOME_PAGE;
    }

    @RequestMapping( value = "/reset", method = RequestMethod.POST )
    public ModelAndView resetPasswordPage(ModelAndView modelAndView, String password, String confirmpassword,
        String token)
    {

        Optional<UserDetails> user = userService.findUserbyToken(token);
        if (!password.equals(confirmpassword)) {
            modelAndView.addObject("resetTokenError", "Password and Compare Password do not match.");
        } else {
            if (user.isPresent()) {
                UserDetails resetUser = user.get();
                resetUser.setPassword(password);
                resetUser.setPasswordConfirm(password);
                resetUser.setResetToken(null);
                try {
                    userService.save(resetUser);
                } catch (Exception ex) {
                    LOG.debug("Error logging password reset:", ex);
                }
                modelAndView.addObject("passwordResetSuccessful",
                                       "You have successfully reset your password. You may now login");
            } else {
                modelAndView.addObject("resetTokenError", "Oops! Password reset link is not valid.");
            }
        }
        modelAndView.setViewName(Views.RESET_PASSWORD);
        return modelAndView;
    }

    @RequestMapping( value = "/register", method = RequestMethod.POST )
    public ModelAndView register(ModelAndView modelAndView,
        @ModelAttribute( "userDetails" ) @Valid UserDetails userDetails, BindingResult result,
        HttpServletRequest request)
    {

        boolean isValidPassword = true;
        boolean isEmailValid = true;
        boolean isPhoneValid = true;
        modelAndView.setViewName(Views.REGISTRATION_PAGE);
        Optional<UserDetails> user = userService.findUserbyEmail(userDetails.getEmail());
        if (user.isPresent()) {
            modelAndView.getModel().put("emailExists", Constants.EMAIL_EXISTS);
        } else {
            userDetails.setIpAddress(Utils.getClientIp(request));
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

    @RequestMapping( value = "/post", method = { RequestMethod.POST, RequestMethod.GET } )
    public String submitpost(@RequestParam( "file" ) MultipartFile file,
        @ModelAttribute( "usersubmissions" ) UserSubmissions usersubmissions, HttpServletRequest request)
    {

        String nameEmail = userService.readNameEmailFromCookie(request);
        if (nameEmail.isEmpty()) {
            return "redirect:/";
        }
        try {
            if (!file.isEmpty()) {
                File fileSaveDir = new File(Constants.IMG_PATH);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdir();
                }
                String absPath = request.getServletContext().getRealPath("/");
                byte[] bytes = file.getBytes();
                Path path = Paths.get(absPath + Constants.IMG_PATH + file.getOriginalFilename());
                usersubmissions.setImageUrl(path.toString());
                Files.write(path, bytes);

            }
        } catch (IOException e) {
            LOG.debug("[UserController:SubmitPost]Exception while trying to upload the file", e);
        }
        UserDetails userDetail = new UserDetails();
        usersubmissions.setSubmittiedDate(new Date());
        usersubmissions.setIp(Utils.getClientIp(request));
        usersubmissions.setIsActivePost(Constants.ACTIVE);
        usersubmissions.setUpdatedDate(new Date());
        userDetail
            .setId(Long.parseLong(nameEmail.split(Constants.COOKIE_SEPARATOR)[2]));
        usersubmissions.setUserDetails(userDetail);
        try {
            subService.save(usersubmissions);
            postsMap.clear();
        } catch (Exception ex) {
            LOG.debug("[UserController:SubmitPost]Exception while to save submissions to db", ex);
        }
        return "redirect:/myhomepage";
    }

    
    @RequestMapping( value = "/loadMoreRecent", method = RequestMethod.GET, produces = { "application/json" } )
    public @ResponseBody List<UserSubmissions> loadmore(@RequestParam( "offset" ) int offset,
        HttpServletResponse response)
    {
        if(offset !=0)
            offset = Constants.BLOCKSIZE * offset ;
        
        return subService.getRecentPostsDesc(Constants.BLOCKSIZE, offset).get();
    }

    
    @RequestMapping( value = "/categories", method = RequestMethod.GET, produces = { "application/json" } )
    public @ResponseBody Map<String, String> getCategories()
    {
        return Categories.getCategories();
    }

    
    @RequestMapping( value = "/subcategories", method = RequestMethod.GET )
    public @ResponseBody String getSubCategoryByKey(@RequestParam( "category" ) String category)
    {
        return Categories.getSubCategoryByKey(category);
    }

    @RequestMapping( value = "/bycategory", method = RequestMethod.GET )
    public @ResponseBody List<UserSubmissions> filterByCateogry(@RequestParam( "category" ) String category,
        @RequestParam( "offset" ) int offset,
        Model model, HttpServletRequest request)
    {
        if(offset !=0)
            offset = Constants.BLOCKSIZE *  offset ;
        return subService.fetchByCategoryDesc(category, Constants.BLOCKSIZE, offset).get();
    }

    

}
