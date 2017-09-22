package com.webstar.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstar.models.UserReposts;
import com.webstar.models.UserSubmissions;
import com.webstar.repository.SubmissionRepository;

@Service
public class SubmissionService implements ISubmissionService
{
    @Autowired
    private SubmissionRepository submissionsRepo;

    @Override
    @Transactional
    public void save(UserSubmissions submissions)
    {
        submissionsRepo.save(submissions);
    }

    public Optional<List<UserSubmissions>> getRecentPostsDesc(int limit, int offset)
    {
        return submissionsRepo.fetchRecentPostsDesc(limit, offset);
    }

    public Optional<List<UserSubmissions>> fetchByCategoryDesc(String category, int limit, int offset)
    {
        return submissionsRepo.findByCategoryOrderDesc(category, limit, offset);
    }

    @Override
    public int updateTotalCommentCount(int totalComments, Long postid)
    {
        return submissionsRepo.updateTotalCommentsCount(totalComments, postid);
    }

    @Override
    public int updateTotalLikesCount(int totalLikes, Long postid)
    {
        return submissionsRepo.updateTotalLikesCount(totalLikes, postid);
    }

    @Override
    public int updateTotalRepostsCount(int totalReposts, Long postid)
    {
        return submissionsRepo.updateTotalRepostsCount(totalReposts, postid);
    }

    @Override
    public Optional<UserSubmissions> fetchByPostId(Long postId)
    {
        return submissionsRepo.findByPostId(postId);

    }

    @Override
    public Optional<List<UserSubmissions>> fetchPostsByUserId(Long userId, int limit, int offset)
    {
        return submissionsRepo.findPostsByUserId(userId, limit, offset);

    }

    @Override
    public Optional<List<UserSubmissions>> fetchPostsByUsername(String username, int limit, int offset)
    {
       return submissionsRepo.findPostsByUsername(username, limit, offset);
    }

}
