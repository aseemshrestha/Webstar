package com.webstar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webstar.models.UserComments;

@Repository
public interface CommentRepository extends JpaRepository<UserComments, Long>
{
    @Query(
        value = "select total_comments from User_Submissions u where u.id = ?1",
        nativeQuery = true )
    public int getTotalCommentsbyPostId(Long id);

    @Query(
        value = "select * from  user_comments u where u.post_id = ?1 order by u.commented_on desc limit ?2 offset ?3",
        nativeQuery = true )
    public Optional<List<UserComments>> getCommentsbyPostId(Long id, int limit, int offset);

}
