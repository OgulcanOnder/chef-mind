package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient i SET i.name = ?2, i.ingredientCategory.id=?3  WHERE i.id = ?1")
    void updateIngredient(Long id, String name, Long ingredientCategoryId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ingredient i WHERE i.id=:id")
    int deleteIngredient(Long id);
}
