package com.webstar.services;

import com.webstar.models.UserLikes;

public interface ILikesService
{

    void saveLikes(UserLikes likes);
    int getTotalNumberOfLikes(Long postid);
    Long getLikedById( Long uid, Long postId);

}
