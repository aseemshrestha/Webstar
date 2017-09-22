package com.webstar.services;

import java.util.List;
import java.util.Optional;
import com.webstar.models.UserSubmissions;

public interface ISubmissionService
{

    void save(UserSubmissions submissions);

    Optional<List<UserSubmissions>> getRecentPostsDesc(int limit, int offset);

    Optional<List<UserSubmissions>> fetchByCategoryDesc(String category, int limit, int offset);

    int updateTotalCommentCount(int totalComments, Long postid);

    int updateTotalLikesCount(int totalComments, Long postid);

    int updateTotalRepostsCount(int totalReposts, Long postid);

    Optional<UserSubmissions> fetchByPostId(Long postId);

    Optional<List<UserSubmissions>> fetchPostsByUserId(Long userId, int limit, int offset);
    
    Optional<List<UserSubmissions>> fetchPostsByUsername(String username, int limit, int offset);

   

}
