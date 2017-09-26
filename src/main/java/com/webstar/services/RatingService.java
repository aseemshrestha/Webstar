package com.webstar.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstar.models.UserRatings;
import com.webstar.repository.RatingRepository;
@Service
public class RatingService implements IRatingService
{
    @Autowired
    private RatingRepository ratingRepo;
    
    @Override
    @Transactional
    public void saveRatings(UserRatings ratings)
    {
        ratingRepo.save(ratings);
    }

    @Override
    public double getAverageRating(Long postId)
    {
       return ratingRepo.getAverageRating(postId);
    }
}
