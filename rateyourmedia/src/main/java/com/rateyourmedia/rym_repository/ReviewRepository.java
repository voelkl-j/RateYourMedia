package com.rateyourmedia.rym_repository;

import com.rateyourmedia.rym_entity.Media;
import com.rateyourmedia.rym_entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

    Page<Review> findAllByReviewedOrderByCreationDateAsc(Media media, Pageable pageable);




    @Query("SELECT avg(r.rating) FROM Review r WHERE r.reviewed= :reviewed")
    double avgRating(@Param("reviewed") Media media);


}
