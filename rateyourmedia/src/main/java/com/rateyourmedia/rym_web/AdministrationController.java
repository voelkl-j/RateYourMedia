package com.rateyourmedia.rym_web;

import com.rateyourmedia.rym_entity.Book;
import com.rateyourmedia.rym_entity.Media;
import com.rateyourmedia.rym_entity.Movie;
import com.rateyourmedia.rym_service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdministrationController {
    @Autowired
    private MediaService mediaService;
    @Autowired
    private DetailController detailController;

    @RequestMapping(value ="/book", method = RequestMethod.POST)
    public String addNewBook(@ModelAttribute(value = "Media") Media media, @ModelAttribute(value = "Book") Book book, @RequestParam(value = "publication_date") String publication_dateString, Model model){
        //Date aus Html kann nicht direkt Book hinzugefügt werden
        SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy");
        Date publication_date;
        try {
            publication_date = formatter.parse(publication_dateString);}
        catch (ParseException e){throw new RuntimeException();}
        book = mediaService.addBooktoDatabase(media, book, publication_date);
        return detailController.showMediaDetails(book.getBaseInformation().getMediaID(),book.getBaseInformation().getMediaType(),0, model);
    }
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public String addNewMovie(@ModelAttribute(value = "Media") Media media, @ModelAttribute(value = "Movie") Movie movie, Model model){
        movie = mediaService.addMovietoDatabase(media, movie);
        return detailController.showMediaDetails(movie.getBaseInformation().getMediaID(),movie.getBaseInformation().getMediaType(),0, model);
    }

    @RequestMapping(value = "/administration/add/{mediaType}", method = RequestMethod.GET)
    public String showAddPage(@PathVariable("mediaType")String mediaType, Model model){
        Media media= new Media();
        model.addAttribute("Media", media);
        switch (mediaType){
            case "Book":
                Book book= new Book();
                model.addAttribute("Book", book);
                return "addBook";
            case "Movie":
                Movie movie= new Movie();
                model.addAttribute("Movie", movie);
                return "addMovie";
            default: throw new RuntimeException();
        }
    }

    @RequestMapping(value = "/administration/relations", method = RequestMethod.GET)
    public String showAddMBRelationPage(){
        return "addMovieBookRelation";
    }

    @RequestMapping(value = "/administration/relations", method = RequestMethod.POST)
    public String addNewMBRelation(@RequestParam("isbn") String isbn, @RequestParam("movieID") String movieID, Model model){
        Movie movie= mediaService.addMovieBookRelation(Long.parseLong(isbn),Long.parseLong(movieID));
        if(movie==null){
            return "addMovieBookRelation";
        }
        else {
            return detailController.showMediaDetails(movie.getBaseInformation().getMediaID(), movie.getBaseInformation().getMediaType(), 0, model);
        }
    }


}
