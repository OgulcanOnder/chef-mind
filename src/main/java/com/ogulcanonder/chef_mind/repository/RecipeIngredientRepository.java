package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository  extends JpaRepository<RecipeIngredient,Long> {

    boolean existsByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
