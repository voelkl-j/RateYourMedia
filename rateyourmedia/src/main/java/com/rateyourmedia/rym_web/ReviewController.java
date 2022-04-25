package com.rateyourmedia.rym_web;

import com.rateyourmedia.rym_entity.*;
import com.rateyourmedia.rym_service.ReviewService;
import com.rateyourmedia.rym_service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private SearchService searchService;
    @Autowired DetailController detailController;

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public String postReview(@ModelAttribute("mediaID") long mediaID, @ModelAttribute("mediaType") String mediaType, @ModelAttribute("rating") int rating, @ModelAttribute("comment") String text, Model model) {
        Media media= searchService.getMedia(mediaID);
        User creator= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reviewService.writeReview(media, creator, text, rating);
        return detailController.showMediaDetails(mediaID, RYMediaType.valueOf(mediaType), 0, model);
    }

}