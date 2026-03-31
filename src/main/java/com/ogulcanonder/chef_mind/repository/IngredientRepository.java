package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository  extends JpaRepository<Ingredient,Long> {

    boolean existsByNameIgnoreCase(String name);
    Optional<Ingredient> findByName(String name);
}
