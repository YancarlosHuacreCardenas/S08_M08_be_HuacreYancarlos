package pe.edu.vallegrande.eduSmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.eduSmart.model.Movie;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByAvailableTrue();

    @Query(value = "SELECT * FROM movies WHERE id = ?", nativeQuery = true)
    Optional<Movie> findByIdIncludingDeleted(Long id);

    @Query(value = "SELECT * FROM movies", nativeQuery = true)
    List<Movie> findAllIncludingDeleted();

    @Modifying
    @Transactional
    @Query("UPDATE Movie m SET m.available = :status WHERE m.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") Boolean status);
}