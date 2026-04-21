package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE RecipeIngredient ri SET ri.ingredient.id=?1,ri.recipe.id=?2,ri.required=?3 WHERE ri.id=?4 ")
    void updateById(Long ingredientId, Long recipeId,boolean required, Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM RecipeIngredient ri WHERE ri.id=:id")
    int deleteRecipeIngredientById(Long id);
}
