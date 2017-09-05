package com.webstar.services;

import java.util.List;
import java.util.Optional;

import com.webstar.models.UserSubmissions;

public interface ISubmissionService
{

    void save(UserSubmissions submissions);

    Optional<List<UserSubmissions>> getRecentPostsDesc(int limit, int offset);

    Optional<List<UserSubmissions>> fetchByCategoryDesc(String category, int limit, int offset);

}
