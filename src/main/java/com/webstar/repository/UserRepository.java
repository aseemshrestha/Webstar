package com.webstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webstar.models.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long>
{
    UserDetails findByEmail(String email);

   

}
