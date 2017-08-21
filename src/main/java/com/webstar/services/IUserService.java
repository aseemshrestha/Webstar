package com.webstar.services;

import javax.servlet.http.HttpServletRequest;

import com.webstar.models.UserDetails;

public interface IUserService
{

    void save(UserDetails userDetails);
    boolean isUserAuthenticated(String email, String password);
    UserDetails findUserbyEmail(String email);
    String readEmailFromCookie(HttpServletRequest request);

}
