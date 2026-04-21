package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientCategoryRequest;
import com.ogulcanonder.chef_mind.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE IngredientCategory i SET i.ingredientCategoryName=?2 WHERE i.id=?1")
    void updateById(Long id, String ingredientCategoryName);

    @Modifying
    @Transactional
    @Query("DELETE FROM IngredientCategory i WHERE i.id=:id")
    int deleteByIngredientCategoryId(Long id);
}
