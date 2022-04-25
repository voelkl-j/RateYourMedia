package com.rateyourmedia.rym_repository.mediarepos;


import com.rateyourmedia.rym_entity.Media;
import com.rateyourmedia.rym_entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findMovieByBaseInformation(Media baseInformation);

    @Query("SELECT mo FROM Movie mo WHERE mo.baseInformation.title LIKE %:title%")
    List<Movie> findByBaseInformation_TitleLike(String title);

    @Query("SELECT mo FROM Movie AS mo WHERE mo.director LIKE %:director%")
    List<Movie> searchMoviebyDirector(String director);

    @Query("SELECT mo FROM Movie AS mo WHERE mo.director LIKE %:director% AND mo.baseInformation.title LIKE %:title%")
    List<Movie> searchMoviebyTitleandDirector(String title, String director);

    @Query("SELECT mo FROM Movie mo ORDER BY mo.movieID DESC")
    List<Movie> findnewestMovies();
}
