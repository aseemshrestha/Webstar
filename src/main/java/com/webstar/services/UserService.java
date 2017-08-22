package com.webstar.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webstar.models.UserDetails;
import com.webstar.repository.UserRepository;
import com.webstar.util.Constants;

@Service
public class UserService implements IUserService
{
    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public void save(UserDetails userDetails)
    {
        userDetails.setPasswordConfirm(userDetails.getPassword());
        userRepo.save(userDetails);
    }

    public UserDetails isUserAuthenticated(String email, String password)
    {
        return userRepo.findByEmailAndPassword(email, password);

    }

    public UserDetails findUserbyEmail(String email)
    {
        return userRepo.findByEmail(email);

    }

    /* if user is loggedIn user - email shoud be returned */
    public String readEmailFromCookie(HttpServletRequest request)
    {
        String email = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constants.WEBSTAR_COOKIE_AUTH.equals(cookie.getName())) {
                    email = cookie.getValue();
                    break;
                }

            }
        }
        return email;
    }

}
