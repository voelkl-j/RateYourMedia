package com.rateyourmedia.rym_service;

import com.rateyourmedia.rym_entity.Book;
import com.rateyourmedia.rym_entity.Media;
import com.rateyourmedia.rym_entity.Movie;

import java.util.Date;

public interface MediaService {
    Book addBooktoDatabase(Media media, Book book, Date date);
    Movie addMovietoDatabase(Media media, Movie movie);
    Movie addMovieBookRelation(long isbn, long movieID);
}
