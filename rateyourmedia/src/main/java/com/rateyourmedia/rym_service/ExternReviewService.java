package com.rateyourmedia.rym_service;

import com.rateyourmedia.rym_entity.Review;



public interface ExternReviewService {
    Review[] loadlatest_bookReviews(long isbn);
    Review[] loadlatest_movieReviews(String title, String director);
}
