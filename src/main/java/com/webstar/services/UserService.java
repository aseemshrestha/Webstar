package com.webstar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstar.models.UserDetails;
import com.webstar.repository.UserRepository;

@Service
public class UserService implements IUserService
{
    @Autowired
    private UserRepository userRepo;

    @Override
    public void save(UserDetails userDetails)
    {
        userRepo.save(userDetails);
    }

}
