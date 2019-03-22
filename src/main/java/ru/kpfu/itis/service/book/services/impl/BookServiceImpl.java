package ru.kpfu.itis.service.book.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.service.book.dto.BookDto;
import ru.kpfu.itis.service.book.models.Book;
import ru.kpfu.itis.service.book.repositories.BookRepository;
import ru.kpfu.itis.service.book.services.BookService;

import javax.persistence.EntityNotFoundException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(BookDto::from);
    }

    @Override
    public BookDto findById(Long id) {
        Book book = getByIdOrThrow(id);
        return BookDto.from(book);
    }

    @Override
    public Page<BookDto> findByQuery(String query, Pageable pageable) {
        Page<Book> books = bookRepository.findByQuery(query, pageable);
        return books.map(BookDto::from);
    }

    @Override
    public BookDto save(BookDto bookDto) {
        bookDto.setReadAlready(false);
        bookDto.setImageUrl(null);
        Book book = bookRepository.save(BookDto.to(bookDto));
        return BookDto.from(book);
    }

    @Override
    public BookDto update(Long id, BookDto bookDto) {
        Book book = getByIdOrThrow(id);

        book = bookRepository.save(updateBookEdition(book, BookDto.to(bookDto)));
        return BookDto.from(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void markAsRead(Long id) {
        Book book = getByIdOrThrow(id);
        if (book.getReadAlready()) {
            return;
        }

        book.setReadAlready(true);
        bookRepository.save(book);
    }

    @Override
    public void updateImageUrl(Long id, String imageUrl) {
        Book book = getByIdOrThrow(id);
        book.setImageUrl(imageUrl);
        bookRepository.save(book);
    }

    private Book getByIdOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id[" + id + "] not found"));
    }

    private Book updateBookEdition(Book old, Book updated) {
        updated.setId(old.getId());
        updated.setAuthor(old.getAuthor());
        updated.setImageUrl(old.getImageUrl());
        updated.setReadAlready(false);
        return updated;
    }
}
