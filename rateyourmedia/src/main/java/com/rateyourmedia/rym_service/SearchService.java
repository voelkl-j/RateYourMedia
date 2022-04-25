package com.rateyourmedia.rym_service;


import com.rateyourmedia.rym_entity.Book;
import com.rateyourmedia.rym_entity.Media;
import com.rateyourmedia.rym_entity.Movie;

import java.util.List;

public interface SearchService {
    //Searching for Books
    Book getBookByISBN(long isbn);
    List<Book> searchBookISBNLike(long isbn);
    Book getBookbyMediaID(long mediaID);
    List<Book> searchBook(String param, String searchText);
    //Searching for Movies
    Movie getMovieByMovieID(long movieID);
    Movie getMoviebyMediaID(long mediaID);
    List<Movie> searchMovieByTitleandDirectorLike(String title, String director);
    List<Movie> searchMovie(String param, String searchText);
    //Searching for Media
    Media getMedia(long mediaID);
    List<Media> searchMedia(String searchText);
    List<Book> getNewestBooks();
    List<Movie> getNewestMovies();



}
