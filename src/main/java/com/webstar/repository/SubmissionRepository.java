package com.webstar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webstar.models.UserSubmissions;

@Repository
public interface SubmissionRepository extends JpaRepository<UserSubmissions, Long>
{
    @Query( value = "select * from User_Submissions u order by u.submittied_date desc limit ?1 offset ?2",
        nativeQuery = true )
    List<UserSubmissions> fetchRecentPosts(int limit, int offset);
}
