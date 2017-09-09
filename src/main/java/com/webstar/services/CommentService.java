package com.webstar.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstar.models.UserComments;
import com.webstar.repository.CommentRepository;
import com.webstar.repository.SubmissionRepository;

@Service
public class CommentService implements ICommentService
{
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private SubmissionRepository submissionRepo;

    @Override
    @Transactional
    public void save(UserComments comments)
    {
        commentRepo.save(comments);
    }

    @Override
    public int getTotalNumberComments(Long postid)
    {
        return commentRepo.getTotalCommentsbyPostId(postid);
    }

    public Optional<List<UserComments>> fetchCommentsByPostId(Long postid, int limit, int offset)
    {
        return commentRepo.getCommentsbyPostId(postid, limit, offset);
    }

}
