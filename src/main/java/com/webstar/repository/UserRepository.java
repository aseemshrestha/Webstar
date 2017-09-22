package com.webstar.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webstar.models.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long>
{
    Optional<UserDetails> findByEmail(String email);

    Optional<UserDetails> findByUsername(String username);

    Optional<UserDetails> findByEmailAndPassword(String email, String password);

    Optional<UserDetails> findByResetToken(String token);
  
    @Modifying
    @Query( "update UserDetails u set u.lastLoggedIn = ?1  where u.email = ?2" )
    void updateLastLoggedIn(Date lastLoggedin, String email);

    @Query( value = "select * from  user_details u where u.username like ?1%  limit ?2 offset ?3 ", nativeQuery = true )
    Optional<List<UserDetails>> fetchUserNames(String username, int limit, int offset);
    
    
}
