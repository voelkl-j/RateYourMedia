package com.rateyourmedia.rym_web;

import com.rateyourmedia.rym_service.ReviewService;
import com.rateyourmedia.rym_service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private SearchService searchService;

    @RequestMapping(value ="/")
    public String showstartingPage(Model model){
        model.addAttribute("Reviews",reviewService.loadlatest_Reviews());
        model.addAttribute("Books", searchService.getNewestBooks());
        model.addAttribute("Movies", searchService.getNewestMovies());
        return "start";
    }
}
