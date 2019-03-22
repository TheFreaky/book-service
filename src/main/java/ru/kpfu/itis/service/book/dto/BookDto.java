package ru.kpfu.itis.service.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.service.book.models.Book;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Long id;

    @NotEmpty
    private String title;
    private String description;

    @NotEmpty
    private String author;

    @NotEmpty
    private String isbn;

    @Min(1)
    private int printYear;

    @Null
    private Boolean readAlready;

    @Null
    private String imageUrl;

    public static Book to(BookDto dto) {
        if (dto == null) return null;

        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .author(dto.getAuthor())
                .isbn(dto.getIsbn())
                .printYear(dto.getPrintYear())
                .readAlready(dto.getReadAlready())
                .imageUrl(dto.getImageUrl())
                .build();
    }

    public static BookDto from(Book model) {
        if (model == null) return null;

        return BookDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .author(model.getAuthor())
                .isbn(model.getIsbn())
                .printYear(model.getPrintYear())
                .readAlready(model.getReadAlready())
                .imageUrl(model.getImageUrl())
                .build();
    }

    public static List<BookDto> from(List<Book> models) {
        return models.stream()
                .map(BookDto::from)
                .collect(Collectors.toList());
    }
}
