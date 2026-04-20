package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory,Long> {
    boolean existsByIngredientCategoryNameIgnoreCase(String ingredientCategoryName);
}
