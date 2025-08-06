package com.example.errorhandling.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBookRequest {

    @NotBlank(message = "Title must not be blank")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Author must not be blank")
    private String author;

    private String category;

    @NotNull(message = "Publication year is required")
    @Min(value = 1450, message = "Publication year must be after 1450")
    private short publicationYear;
    
    @Email(message = "Publisher email format is invalid")
    private String publisherEmail;
}
