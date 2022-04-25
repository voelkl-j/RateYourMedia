package com.rateyourmedia.rym_web;

import com.rateyourmedia.rym_service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value ="/search", method = RequestMethod.GET)
    public String showSearchPage(Model model){
        return "searchStart";
    }


    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String showSearchResults(@RequestParam("searchText") String searchText, @RequestParam("searchCategory") String searchCategory, Model model){
        String[] split= searchCategory.split("_");
        String mediaType = split[0];
        String searchParam = split[1];
        switch (mediaType){
            case "media":
                model.addAttribute("Medias", searchService.searchMedia(searchText));
                System.out.println(searchText + " searchparam: " +searchParam);
                return "searchMediaResults";
            case "book":
                model.addAttribute("Books", searchService.searchBook(searchParam, searchText));
                System.out.println(searchText + " searchparam: " +searchParam);
                return "searchBookResults";
            case "movie":
                model.addAttribute("Movies", searchService.searchMovie(searchParam, searchText));
                System.out.println(searchText + " searchparam: " +searchParam);
                return "searchMovieResults";
            default: throw new RuntimeException();
        }
    }

}
