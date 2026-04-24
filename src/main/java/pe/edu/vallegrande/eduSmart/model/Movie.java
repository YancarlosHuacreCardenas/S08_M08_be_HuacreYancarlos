package pe.edu.vallegrande.eduSmart.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column
    private String director;

    @Column
    private Integer year;

    @Column
    private String genre;

    @Column
    private Integer duration;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "release_date")
    private LocalDate releaseDate;
}