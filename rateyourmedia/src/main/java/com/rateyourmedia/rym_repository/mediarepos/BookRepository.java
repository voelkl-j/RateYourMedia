package com.rateyourmedia.rym_repository.mediarepos;

import com.rateyourmedia.rym_entity.Book;
import com.rateyourmedia.rym_entity.Media;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findBookByBaseInformation(Media baseInformation);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByIsbnLike(long isbn);

    @Query("SELECT b FROM Book b WHERE b.baseInformation.title LIKE %:title%")
    List<Book> findByBaseInformation_TitleLike(@Param("title") String title);

    @Query("SELECT b FROM Book b ORDER BY b.publication_date DESC")
    List<Book> findnewestBooks();


}
