package pe.edu.vallegrande.eduSmart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.eduSmart.model.Movie;
import pe.edu.vallegrande.eduSmart.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Movies", description = "API para gestionar películas")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    @Operation(summary = "Obtener todas las películas", description = "Retorna una lista de todas las películas disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de películas obtenida exitosamente")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las películas incluyendo eliminadas", description = "Retorna una lista de todas las películas incluyendo las marcadas como eliminadas")
    @ApiResponse(responseCode = "200", description = "Lista de películas obtenida exitosamente")
    public ResponseEntity<List<Movie>> getAllMoviesIncludingDeleted() {
        return ResponseEntity.ok(movieService.findAllIncludingDeleted());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener película por ID", description = "Retorna una película específica según su identificador")
    @ApiResponse(responseCode = "200", description = "Película encontrada")
    @ApiResponse(responseCode = "404", description = "Película no encontrada")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nueva película", description = "Crea una nueva película en la base de datos")
    @ApiResponse(responseCode = "201", description = "Película creada exitosamente")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar película", description = "Actualiza los datos de una película existente")
    @ApiResponse(responseCode = "200", description = "Película actualizada exitosamente")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.update(id, movie);
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar película", description = "Marca una película como eliminada")
    @ApiResponse(responseCode = "204", description = "Película eliminada exitosamente")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/restore/{id}")
    @Operation(summary = "Restaurar película eliminada", description = "Restaura una película que fue marcada como eliminada")
    @ApiResponse(responseCode = "204", description = "Película restaurada exitosamente")
    public ResponseEntity<Void> restoreMovie(@PathVariable Long id) {
        movieService.restore(id);
        return ResponseEntity.noContent().build();
    }
}