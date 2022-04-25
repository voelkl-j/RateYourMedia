package com.rateyourmedia.rym_web;

import com.rateyourmedia.rym_entity.*;
import com.rateyourmedia.rym_service.ReviewService;
import com.rateyourmedia.rym_service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DetailController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value= "/media/detail/{page}", method = RequestMethod.GET)
    public String showMediaDetails(@RequestParam("mediaID") long mediaID, @RequestParam("mediaType") RYMediaType mediaType, @PathVariable("page") int page, Model model){
        Media media=searchService.getMedia(mediaID);
        model.addAttribute("rating", reviewService.getAvgRating(media));
        model.addAttribute("reviews",reviewService.loadlatest_Reviews_byMedia(media, page));
        page=page+1;
        model.addAttribute("page", page);
        System.out.println(page);
        switch (mediaType){
            case Book :
                Book book = searchService.getBookbyMediaID(mediaID);
                model.addAttribute("book", book);
                return "detailBook";
            case Movie:
                Movie movie= searchService.getMoviebyMediaID(mediaID);
                model.addAttribute("movie", movie);
                return "detailMovie";
            default: throw new RuntimeException();
        }
    }


}
