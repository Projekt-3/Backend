package org.example.backend.repository;

import org.example.backend.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IShowRepository extends JpaRepository<Show, Integer> {
    Optional<Show> findById(int id);
}
