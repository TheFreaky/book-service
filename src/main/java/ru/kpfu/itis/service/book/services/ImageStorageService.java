package ru.kpfu.itis.service.book.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    String upload(MultipartFile multipartFile);
}
