package com.rateyourmedia.rym_service.impl;

import com.rateyourmedia.rym_entity.Book;
import com.rateyourmedia.rym_entity.Media;
import com.rateyourmedia.rym_entity.Movie;
import com.rateyourmedia.rym_entity.RYMediaType;
import com.rateyourmedia.rym_repository.MediaRepository;
import com.rateyourmedia.rym_repository.mediarepos.BookRepository;
import com.rateyourmedia.rym_repository.mediarepos.MovieRepository;
import com.rateyourmedia.rym_service.MediaService;
import com.rateyourmedia.rym_service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SearchService searchService;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Book addBooktoDatabase(Media media, Book book, Date date) {
        media.setMediaType(RYMediaType.Book);
        media = mediaRepository.save(media);
        book.setBaseInformation(media);
        book.setPublication_date(date);
        return bookRepository.save(book);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Movie addMovietoDatabase(Media media, Movie movie) {
        media.setMediaType(RYMediaType.Movie);
        media =mediaRepository.save(media);
        movie.setBaseInformation(media);
        return movieRepository.save(movie);
    }


    //Im folgenden nutze ich CascadeType.Merge aus. Es reicht also aus nur bei Movie das Attribut zu setzen
    @Override
    @Transactional
    public Movie addMovieBookRelation(long isbn, long movieID){
        Movie movie= searchService.getMovieByMovieID(movieID);
        Book book= searchService.getBookByISBN(isbn);
        if(movie==null || book ==null){
            return null;
        }
        else {
            movie.setBasedOn(book);
            return movie;
        }
    }
}
