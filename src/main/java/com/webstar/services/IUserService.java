package com.webstar.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.webstar.models.UserDetails;
import com.webstar.viewmodels.SearchViewModel;

public interface IUserService
{

    void save(UserDetails userDetails);

    Optional<UserDetails> findUserbyEmail(String email);

    Optional<UserDetails> findUserbyUsername(String username);

    String readNameEmailFromCookie(HttpServletRequest request);

    Optional<UserDetails> isUserAuthenticated(String email, String password);

    Optional<UserDetails> findUserbyToken(String token);

    void updateLastLoggedTime(Date loggedin, String email);
    
    Optional<List<UserDetails>> fetchUsersByUsername(String username, int limit, int offset);

}
