package com.webstar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstar.models.UserLikes;
import com.webstar.repository.LikesRepository;

@Service
public class LikesService implements ILikesService
{
    private final LikesRepository likesRepo;

    @Autowired public LikesService(LikesRepository likesRepo)
    {
        this.likesRepo = likesRepo;
    }

    @Override
    public void saveLikes(UserLikes likes)
    {
        likesRepo.save(likes);
    }

    @Override
    public int getTotalNumberOfLikes(Long postid)
    {
        return likesRepo.getTotalLikesbyPostId(postid);
    }

    public Long getLikedById(Long uid, Long postId)
    {

        return likesRepo.getLikedByPostId(uid, postId);
    }
}
