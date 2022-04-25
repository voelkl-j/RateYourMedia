package com.rateyourmedia.rym_web;

import com.rateyourmedia.rym_entity.Review;
import com.rateyourmedia.rym_service.ExternReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReviewServiceRestController {
    @Autowired
    private ExternReviewService reviewService;

    //Da beim Senden von List<MyClass>, das Problem auftritt, dass beim Client eine HashMap ankommt mit key=index und value=Instanz von myClass
    //Habe ich mein Interface auf Rückgabe eines Arrays geändert und hier entsprechend.
    @RequestMapping(value = "/rymapi/reviews/book/{isbn}", method = RequestMethod.GET)
    public Review[] loadlatest_bookReviews(@PathVariable("isbn") long isbn){
        Review[] InternResults = reviewService.loadlatest_bookReviews(isbn);
        if(InternResults == null){
            return null;
        }
        int size;
        if(InternResults.length>10){
            size=10;
        }
        else {
            size= InternResults.length;
        }
        Review ExternResults[]= new Review[size];
        for(int i=0; i<size; i++){
            Review noUserReview= new Review();
            noUserReview.setRating(InternResults[i].getRating());
            noUserReview.setText(InternResults[i].getText());
            ExternResults[i]=noUserReview;
        }
        return ExternResults;
    }

    //Siehe Kommentar oben, Array statt List
    @RequestMapping(value = "/rymapi/reviews/movie/{title}/{director}", method = RequestMethod.GET)
    public Review[] loadlatest_movieReviews(@PathVariable("title") String title, @PathVariable("director") String director){
        Review[] InternResults = reviewService.loadlatest_movieReviews(title, director);
        if(InternResults == null){
            return null;
        }
        int size;
        if(InternResults.length>10){
            size=10;
        }
        else {
            size= InternResults.length;
        }
        Review ExternResults[]= new Review[size];
        for(int i=0; i<size; i++){
            Review noUserReview= new Review();
            noUserReview.setRating(InternResults[i].getRating());
            noUserReview.setText(InternResults[i].getText());
            ExternResults[i]=noUserReview;
        }
        return ExternResults;
    }
}
