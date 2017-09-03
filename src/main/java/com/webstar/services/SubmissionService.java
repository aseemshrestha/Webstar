package com.webstar.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<UserSubmissions> getRecentPosts(int limit, int offset)
    {
        return submissionsRepo.fetchRecentPosts(limit, offset);
    }
}
