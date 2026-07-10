package dev.kostie.cicddemo.repository;

import dev.kostie.cicddemo.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, Integer> {
    Optional<Cat> findByColor(String name);
}