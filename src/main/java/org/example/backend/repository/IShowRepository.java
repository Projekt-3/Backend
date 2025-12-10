package org.example.backend.repository;

import org.example.backend.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShowRepository extends JpaRepository<Show, Integer> {
}
