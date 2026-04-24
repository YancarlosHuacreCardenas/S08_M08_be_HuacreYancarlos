package pe.edu.vallegrande.eduSmart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.eduSmart.model.Movie;
import pe.edu.vallegrande.eduSmart.repository.MovieRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieRepository.findByAvailableTrue();
    }

    @Transactional(readOnly = true)
    public List<Movie> findAllIncludingDeleted() {
        return movieRepository.findAllIncludingDeleted();
    }

    @Transactional(readOnly = true)
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie save(Movie movie) {
        if (movie.getAvailable() == null) {
            movie.setAvailable(true);
        }
        return movieRepository.save(movie);
    }

    public Movie update(Long id, Movie movie) {
        return movieRepository.findById(id)
                .map(existingMovie -> {
                    existingMovie.setTitle(movie.getTitle());
                    existingMovie.setDescription(movie.getDescription());
                    existingMovie.setDirector(movie.getDirector());
                    existingMovie.setYear(movie.getYear());
                    existingMovie.setGenre(movie.getGenre());
                    existingMovie.setDuration(movie.getDuration());
                    existingMovie.setImageUrl(movie.getImageUrl());
                    existingMovie.setAvailable(movie.getAvailable());
                    existingMovie.setReleaseDate(movie.getReleaseDate());
                    return movieRepository.save(existingMovie);
                })
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    public void delete(Long id) {
        movieRepository.updateStatus(id, false);
    }

    public void restore(Long id) {
        movieRepository.updateStatus(id, true);
    }
}