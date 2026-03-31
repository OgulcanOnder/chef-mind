package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    boolean existsByNameIgnoreCase(String name);
}
