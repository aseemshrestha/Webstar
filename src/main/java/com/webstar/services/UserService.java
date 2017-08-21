package com.webstar.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstar.models.UserDetails;
import com.webstar.repository.UserRepository;
import com.webstar.util.Constants;

@Service
public class UserService implements IUserService/*, UserDetailsService*/
{
    @Autowired
    private UserRepository userRepo;

    @Override
    public void save(UserDetails userDetails)
    {
        userDetails.setPasswordConfirm(userDetails.getPassword());
        userRepo.save(userDetails);
    }

    public boolean isUserAuthenticated(String email, String password)
    {
        UserDetails user = userRepo.findByEmailAndPassword(email, password);
        if (user == null)
            return false;
        return true;
    }

    public UserDetails findUserbyEmail(String email)
    {
        UserDetails user = userRepo.findByEmail(email);
        if (user == null)
            return null;
        return user;
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
