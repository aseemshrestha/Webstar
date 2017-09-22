package com.webstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webstar.models.UserLikes;

public interface LikesRepository extends JpaRepository<UserLikes, Long>
{
    @Query(
        value = "select total_likes from User_Submissions u where u.id = ?1",
        nativeQuery = true )
    public int getTotalLikesbyPostId(Long id);

    @Query(
        value = "select post_id from User_Likes u where u.liked_by = ?1 and u.post_id=?2",
        nativeQuery = true )
    public Long getLikedByPostId(Long uid,Long postId);
}
