package com.webstar.services;

import javax.servlet.http.HttpServletRequest;

import com.webstar.models.UserDetails;

public interface IUserService
{

    void save(UserDetails userDetails);
    UserDetails findUserbyEmail(String email);
    String readEmailFromCookie(HttpServletRequest request);
    UserDetails isUserAuthenticated(String email, String password);

}
