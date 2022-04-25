package com.rateyourmedia.rym_service;

import com.rateyourmedia.rym_entity.Media;
import com.rateyourmedia.rym_entity.Review;
import com.rateyourmedia.rym_entity.User;

import java.util.List;

public interface ReviewService {

    Review writeReview(Media reviewed, User creator, String text, int rating);

    double getAvgRating(Media media);

    List<Review> loadlatest_Reviews_byMedia(Media media, int page);

    List<Review> loadlatest_Reviews();


}
