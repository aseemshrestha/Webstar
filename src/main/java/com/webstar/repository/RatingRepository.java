package com.webstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webstar.models.UserRatings;

@Repository
public interface RatingRepository extends JpaRepository<UserRatings, Long>
{
    
  @Query(value ="select avg(rating) from webstar.user_ratings where post_id=?1", nativeQuery = true ) 
  public double getAverageRating(Long postId);

}



