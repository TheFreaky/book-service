package ru.kpfu.itis.service.book.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.service.book.dto.BookDto;

public interface BookService {

    Page<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    Page<BookDto> findByQuery(String query, Pageable pageable);

    BookDto save(BookDto bookDto);

    BookDto update(Long id, BookDto bookDto);

    void delete(Long id);

    void markAsRead(Long id);

    void updateImageUrl(Long id, String imageUrl);
}
