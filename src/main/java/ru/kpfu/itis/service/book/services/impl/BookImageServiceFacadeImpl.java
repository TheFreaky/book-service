package ru.kpfu.itis.service.book.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.service.book.dto.BookImageDto;
import ru.kpfu.itis.service.book.services.BookImageServiceFacade;
import ru.kpfu.itis.service.book.services.BookService;
import ru.kpfu.itis.service.book.services.ImageStorageService;

@Service
public class BookImageServiceFacadeImpl implements BookImageServiceFacade {

    private final BookService bookService;
    private final ImageStorageService imageStorageService;

    @Autowired
    public BookImageServiceFacadeImpl(BookService bookService, ImageStorageService imageStorageService) {
        this.bookService = bookService;
        this.imageStorageService = imageStorageService;
    }

    @Override
    public BookImageDto save(Long bookId, MultipartFile image) {
        //ToDo check if book exist
        String imageUrl = imageStorageService.upload(image);
        bookService.updateImageUrl(bookId, imageUrl);
        return BookImageDto.builder()
                .imageUrl(imageUrl)
                .build();
    }
}
