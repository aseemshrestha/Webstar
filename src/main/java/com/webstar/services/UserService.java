package com.webstar.services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webstar.models.UserDetails;
import com.webstar.repository.UserRepository;
import com.webstar.util.Constants;
import com.webstar.util.Security;

@Service
public class UserService implements IUserService
{
    private final UserRepository userRepo;

    private static final String CHARSET = "ISO-8859-1";

    @Autowired public UserService(UserRepository userRepo)
    {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public void save(UserDetails userDetails)
    {
        String passwordHash = Security.generateHash(Security.SALT.concat(userDetails.getPassword()));
        userDetails.setPassword(passwordHash);
        userDetails.setPasswordConfirm(passwordHash);
        userRepo.save(userDetails);
    }

    @Override
    public Optional<UserDetails> isUserAuthenticated(String email, String password)
    {

        String passwordH = Security.generateHash(Security.SALT.concat(password));
        return userRepo.findByEmailAndPassword(email, passwordH);
    }

    @Override
    public Optional<UserDetails> findUserbyEmail(String email)
    {

        return userRepo.findByEmail(email);
    }

    /* if user is loggedIn user - email shoud be returned */
    public String readNameEmailFromCookie(HttpServletRequest request)
    {

        String nameEmail = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constants.WEBSTAR_COOKIE_AUTH.equals(cookie.getName())) {
                    try {
                        nameEmail = new String(Base64.decodeBase64(cookie.getValue().getBytes(CHARSET)));
                    } catch (UnsupportedEncodingException ignored) {}
                    break;
                }
            }
        }

        return nameEmail;
    }

    @Override
    public Optional<UserDetails> findUserbyToken(String token)
    {

        return userRepo.findByResetToken(token);
    }

    @Transactional
    public void updateLastLoggedTime(Date loggedin, String email)
    {

        userRepo.updateLastLoggedIn(loggedin, email);
    }

    @Override
    public Optional<UserDetails> findUserbyUsername(String username)
    {
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<List<UserDetails>> fetchUsersByUsername(String username, int limit, int offset)
    {

       return userRepo.fetchUserNames(username,limit, offset);
       
    }

}
