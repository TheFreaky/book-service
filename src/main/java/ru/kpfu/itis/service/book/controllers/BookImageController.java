package ru.kpfu.itis.service.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.service.book.dto.BookImageDto;
import ru.kpfu.itis.service.book.services.BookImageServiceFacade;

@RestController
@RequestMapping("/books/{id}/image")
public class BookImageController {

    private final BookImageServiceFacade bookImageServiceFacade;

    @Autowired
    public BookImageController(BookImageServiceFacade bookImageServiceFacade) {
        this.bookImageServiceFacade = bookImageServiceFacade;
    }

    @PostMapping("/upload")
    public ResponseEntity<BookImageDto> upload(@PathVariable Long id,
                                               @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(bookImageServiceFacade.save(id, multipartFile));
    }
}
