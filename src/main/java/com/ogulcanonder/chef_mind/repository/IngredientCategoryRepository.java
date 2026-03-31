package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory,Long> {
    boolean existsByIngredientCategoryNameIgnoreCase(String ingredientCategoryName);
    Optional<IngredientCategory>findByIngredientCategoryName(String ingredientCategoryName);
}
