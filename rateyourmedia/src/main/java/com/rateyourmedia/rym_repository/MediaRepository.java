package com.rateyourmedia.rym_repository;

import com.rateyourmedia.rym_entity.Media;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {

    @Query("SELECT m FROM Media AS m WHERE m.title LIKE %:title%")
    List<Media> searchMediabyTitle(String title);
}
