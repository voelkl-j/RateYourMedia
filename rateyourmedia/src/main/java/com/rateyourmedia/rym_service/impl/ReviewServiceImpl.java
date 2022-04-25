package com.rateyourmedia.rym_service.impl;

import com.rateyourmedia.rym_entity.*;
import com.rateyourmedia.rym_repository.ReviewRepository;
import com.rateyourmedia.rym_service.ExternReviewService;
import com.rateyourmedia.rym_service.ReviewService;
import com.rateyourmedia.rym_service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService, ExternReviewService{
    @Autowired
    private ReviewRepository reviewrepo;
    @Autowired
    private SearchService searchService;

    @Override
    public Review[] loadlatest_bookReviews(long isbn) {
        List<Book> bookList= searchService.searchBookISBNLike(isbn);
        if(bookList.isEmpty()){
            return null;
        }
        else {
            Pageable pageable=PageRequest.of(0, 10);
            List<Review> list=reviewrepo.findAllByReviewedOrderByCreationDateAsc(bookList.get(0).getBaseInformation(),pageable).getContent();
            Review[] array = list.toArray(new Review[0]);
            return array;
        }
    }



    @Override
    public Review[] loadlatest_movieReviews(String title, String director) {
        List<Movie> movieList= searchService.searchMovieByTitleandDirectorLike(title, director);
        if(movieList.isEmpty()){
            return null;
        }
        else {
            Pageable pageable=PageRequest.of(0, 10);
            List<Review> list= reviewrepo.findAllByReviewedOrderByCreationDateAsc(movieList.get(0).getBaseInformation(),pageable).getContent();
            Review[] array = list.toArray(new Review[0]);
            return array;
        }
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Review writeReview(Media reviewed, User creator, String text, int rating) {
        Review review= new Review();
        review.setReviewed(reviewed);
        review.setCreator(creator);
        review.setText(text);
        review.setRating(rating);
        review.setCreationDate(new Date());
        return reviewrepo.save(review);

    }

    @Override
    public double getAvgRating(Media media){

        double rating;
        try {
            rating = reviewrepo.avgRating(media);
            int scale = (int) Math.pow(10, 1);
            rating= (double) Math.round(rating * scale) / scale;

        }
        catch (Exception e){
            rating= 0;
        }
        return rating;
    }

    @Override
    public List<Review> loadlatest_Reviews_byMedia(Media media, int page){
        Pageable pageable=PageRequest.of(page, 3);
        return reviewrepo.findAllByReviewedOrderByCreationDateAsc(media,pageable).getContent();
    }

    @Override
    public List<Review> loadlatest_Reviews(){
        Pageable pageable=PageRequest.of(0, 3, Sort.by("creationDate"));
        return reviewrepo.findAll(pageable).getContent();
    }


}
