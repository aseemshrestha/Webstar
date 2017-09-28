package com.webstar.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webstar.models.UserReposts;
import com.webstar.repository.RepostRepository;

@Service
public class RepostsService implements IRepostsService
{
    private final RepostRepository repostRepo;

    @Autowired public RepostsService(RepostRepository repostRepo)
    {
        this.repostRepo = repostRepo;
    }

    @Override
    public void saveReposts(UserReposts reposts)
    {
        repostRepo.save(reposts);
    }

    @Override
    public int getTotalNumberOfReposts(Long postid)
    {
        return repostRepo.getTotalRepostsbyPostId(postid);
    }
    
    @Override
    public Optional<List<UserReposts>> fetchRePostsByUser(Long userId, int limit, int offset)
    {
        return repostRepo.findPostRepostsByUser(userId, limit, offset);
    }

    @Override
    public Optional<List<UserReposts>> fetchRePostsByUsername(String username, int limit, int offset)
    {
        return repostRepo.findPostRepostsByUsername(username, limit, offset);
    }

}
