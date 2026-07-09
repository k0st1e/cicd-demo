package dev.kostie.cicddemo.repository;

import dev.kostie.cicddemo.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {
}