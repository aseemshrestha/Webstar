package com.webstar.services;

import java.util.List;
import java.util.Optional;

import com.webstar.models.UserReposts;

public interface IRepostsService
{
    void saveReposts(UserReposts reposts);
    int getTotalNumberOfReposts(Long postid);
    Optional<List<UserReposts>> fetchRePostsByUser(Long userId, int limit, int offset);
    Optional<List<UserReposts>> fetchRePostsByUsername(String username, int limit, int offset);
  
}
