package com.webstar.services;

import java.util.List;

import com.webstar.models.UserSubmissions;

public interface ISubmissionService
{

    void save(UserSubmissions submissions);
    List<UserSubmissions>getRecentPosts(int limit, int offset);

}
