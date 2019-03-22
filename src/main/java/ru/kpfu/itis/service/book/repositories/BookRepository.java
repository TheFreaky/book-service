package ru.kpfu.itis.service.book.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.service.book.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM public.book " +
            "WHERE setweight(to_tsvector(author), 'A') || setweight(to_tsvector(title), 'B') " +
            "|| setweight(to_tsvector(description), 'C') @@ plainto_tsquery(:query) " +
            "ORDER BY ts_rank(setweight(to_tsvector(author), 'A') || setweight(to_tsvector(title), 'B') " +
            "|| setweight(to_tsvector(description), 'C'), plainto_tsquery(:query)) DESC",
            nativeQuery = true)
    //ToDo func in Postgres like create_book_tsvector(author, title, description)
    //ToDo add field in book table for cache tsvector
    Page<Book> findByQuery(@Param("query") String query, Pageable pageable);
}
