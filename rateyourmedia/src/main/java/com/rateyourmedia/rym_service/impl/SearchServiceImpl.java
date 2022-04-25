package com.rateyourmedia.rym_service.impl;

import com.rateyourmedia.rym_entity.Book;
import com.rateyourmedia.rym_entity.Media;
import com.rateyourmedia.rym_entity.Movie;
import com.rateyourmedia.rym_repository.MediaRepository;
import com.rateyourmedia.rym_repository.mediarepos.BookRepository;
import com.rateyourmedia.rym_repository.mediarepos.MovieRepository;
import com.rateyourmedia.rym_service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private MediaRepository mediarepo;
    @Autowired
    private BookRepository bookrepo;
    @Autowired
    private MovieRepository movierepo;

    //Movie
    @Override
    public Movie getMovieByMovieID(long movieID){
        Optional<Movie> movie =movierepo.findById(movieID);
        if(movie.isPresent()){
            return movie.get();
        }
        else{
            throw new RuntimeException();
        }
    }
    @Override
    public Movie getMoviebyMediaID(long mediaID) {
        Media media= getMedia(mediaID);
        List<Movie> results= movierepo.findMovieByBaseInformation(media);
        if(!results.isEmpty()){
            return results.get(0);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Override
    public List<Movie> searchMovieByTitleandDirectorLike(String title, String director) {
        return movierepo.searchMoviebyTitleandDirector(title,director);
    }


    @Override
    public List<Movie> searchMovie(String param, String searchText) {
        switch (param){
            case "title":
                return movierepo.findByBaseInformation_TitleLike(searchText);
            case "director":
                return movierepo.searchMoviebyDirector(searchText);
            default: return List.of();
        }
    }

    //Media
    @Override
    public Media getMedia(long mediaID) {
        Optional<Media> media =mediarepo.findById(mediaID);
        if(media.isPresent()){
            return media.get();
        }
        else{
            throw new RuntimeException();
        }
    }

    @Override
    public List<Media> searchMedia(String searchText) {
                return mediarepo.searchMediabyTitle(searchText);
    }

    //Books
    @Override
    public Book getBookByISBN(long isbn) {
        Optional<Book> book =bookrepo.findById(isbn);
        if(book.isPresent()){
            return book.get();
        }
        else{
            throw new RuntimeException();
        }
    }
    @Override
    public Book getBookbyMediaID(long mediaID) {
        Media media= getMedia(mediaID);
        List<Book> results= bookrepo.findBookByBaseInformation(media);
        if(!results.isEmpty()){
            return results.get(0);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Override
    public List<Book> searchBook(String param, String searchText) {
        switch (param){
            case "title":
                return bookrepo.findByBaseInformation_TitleLike(searchText);
            case "author":
                return bookrepo.findByAuthorContaining(searchText);
            case "isbn":
                try {
                    return bookrepo.findByIsbnLike(Long.parseLong(searchText));
                }
                catch (Exception e){
                    return List.of();
                }
            default: return List.of();
        }
    }

    @Override
    public List<Book> getNewestBooks(){
        List<Book> list= bookrepo.findnewestBooks();
        if(list.size()>3){
            return bookrepo.findnewestBooks().subList(0,3);
        }
        else return bookrepo.findnewestBooks();
    }

    @Override
    public List<Movie> getNewestMovies(){
        List<Movie> list= movierepo.findnewestMovies();
        if(list.size()>3){
            return movierepo.findnewestMovies().subList(0,3);
        }
        else return movierepo.findnewestMovies();
    }

    @Override
    public  List<Book> searchBookISBNLike(long isbn){
        return bookrepo.findByIsbnLike(isbn);
    }

}
