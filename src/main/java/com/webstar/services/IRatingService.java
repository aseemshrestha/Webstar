package com.webstar.services;

import com.webstar.models.UserRatings;

public interface IRatingService
{

    void saveRatings(UserRatings ratings);
    double getAverageRating(Long postId);

}
