package com.webstar.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webstar.models.UserSubmissions;

@Repository
public interface SubmissionRepository extends JpaRepository<UserSubmissions, Long>
{
    @Query(
        value = "select * from User_Submissions u where u.is_active_post=1 order by u.updated_date desc limit ?1 offset ?2",
        nativeQuery = true )
    Optional<List<UserSubmissions>> fetchRecentPostsDesc(int limit, int offset);

    @Query(
        value = "select * from User_Submissions u where u.is_active_post=1 and u.category = ?1 order by u.updated_date desc limit ?2 offset ?3",
        nativeQuery = true )
    Optional<List<UserSubmissions>> findByCategoryOrderDesc(String category, int limit, int offset);

    @Modifying
    @Transactional
    @Query( value = "update User_Submissions u set u.total_comments = ?1, u.updated_date = ?2  where u.id = ?3", nativeQuery = true )
    int updateTotalCommentsCount(int totalComments, Date updatedDate, Long postid);
    
    @Modifying
    @Transactional
    @Query( value = "update User_Submissions u set u.avg_ratings = ?1, u.updated_date = ?2 where u.id = ?3", nativeQuery = true )
    int updateAverageRating(double avgRating, Date updatedDate,Long postid);

    @Modifying
    @Transactional
    @Query( value = "update User_Submissions u set u.total_likes = ?1  where u.id = ?2", nativeQuery = true )
    int updateTotalLikesCount(int totalLikes,  Long postid);
    
    @Query( value = "select * from User_Submissions u where u.is_active_post=1 and u.id = ?1", nativeQuery = true )
    Optional<UserSubmissions>findByPostId(Long id); 
    
    @Query( value = "select * from User_Submissions u where u.is_active_post=1 and u.user_id = ?1 order by u.updated_date desc limit ?2 offset ?3", nativeQuery = true )
    Optional<List<UserSubmissions>>findPostsByUserId(Long id, int limit, int offset); 
    
    @Query( value = "select * from User_Submissions u where u.is_active_post=1 and u.username = ?1 order by u.updated_date desc limit ?2 offset ?3", nativeQuery = true )
    Optional<List<UserSubmissions>>findPostsByUsername(String username, int limit, int offset); 
    
    @Modifying
    @Transactional
    @Query( value = "update User_Submissions u set u.total_reposts = ?1  where u.id = ?2", nativeQuery = true )
    int updateTotalRepostsCount(int totalReposts,  Long postid);


}
