package ru.kpfu.itis.service.book.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(updatable = false)
    private String author;
    private String isbn;
    private Integer printYear;
    private Boolean readAlready;
    private String imageUrl;
}
