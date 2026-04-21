package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Recipe r SET r.name=?2 WHERE r.id=?1 ")
    void updateById(Long id, String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Recipe r WHERE r.id=:id")
    int deleteRecipeById(Long id);
}
