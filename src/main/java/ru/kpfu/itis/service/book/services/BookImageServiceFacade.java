package ru.kpfu.itis.service.book.services;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.service.book.dto.BookImageDto;

public interface BookImageServiceFacade {
    BookImageDto save(Long bookId, MultipartFile image);
}
