package ru.kpfu.itis.service.book.controllers;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.service.book.dto.BookDto;
import ru.kpfu.itis.service.book.services.BookService;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
//ToDo Create separate dto and do validation
//ToDo Create custom PageableResponse for minimize useless fields in response
public class BookController {

    private static final String DEFAULT_OFFSET_PARAM_VALUE = "0";
    private static final String DEFAULT_LIMIT_PARAM_VALUE = "10";

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<Page<BookDto>> findAll(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "offset", defaultValue = DEFAULT_OFFSET_PARAM_VALUE) Integer offset,
            @RequestParam(name = "limit", defaultValue = DEFAULT_LIMIT_PARAM_VALUE) Integer limit) {

        Page<BookDto> booksPage;
        if (Strings.isEmpty(query)) {
            booksPage = bookService.findAll(createPageable(offset, limit));
        } else {
            booksPage = bookService.findByQuery(query, createPageable(offset, limit));
        }

        return ResponseEntity.ok(booksPage);
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BookDto> changeBookEdition(@PathVariable Long id,
                                                     @Valid @RequestBody BookDto book) {
        return ResponseEntity.ok(bookService.update(id, book));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PostMapping(path = "/{id}/markAsRead")
    @ResponseStatus(HttpStatus.OK)
    public void changeBookStatus(@PathVariable Long id) {
        bookService.markAsRead(id);
    }

    private Pageable createPageable(Integer offset, Integer limit) {
        return PageRequest.of(offset / limit, limit);
    }
}
