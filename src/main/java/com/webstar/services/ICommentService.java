package com.webstar.services;

import java.util.List;
import java.util.Optional;

import com.webstar.models.UserComments;

public interface ICommentService
{

    void save(UserComments comments);
    int getTotalNumberComments(Long postid);
    Optional<List<UserComments>> fetchCommentsByPostId(Long postid, int limit, int offset);
  

}
