package com.webstar.services;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.webstar.models.UserDetails;

public interface IUserService
{
   
    void save(UserDetails userDetails);
    Optional<UserDetails> findUserbyEmail(String email);
    String readNameEmailFromCookie(HttpServletRequest request);
    public Optional<UserDetails> isUserAuthenticated(String email, String password);
    Optional<UserDetails>findUserbyToken(String token);
    void updateLastLoggedTime(Date loggedin, String email);
    
}
