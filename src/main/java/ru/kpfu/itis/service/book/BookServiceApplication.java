package ru.kpfu.itis.service.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookServiceApplication {
    //ToDo Add Docker Compose
    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }
}
