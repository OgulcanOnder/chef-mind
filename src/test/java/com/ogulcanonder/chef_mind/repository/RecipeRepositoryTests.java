package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
public class RecipeRepositoryTests {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    TestEntityManager testEntityManager;
    private Recipe testRecipe;

    @BeforeEach
    public void setup() {
        testRecipe = new Recipe();
        testRecipe.setName("Test Recipe");
        testEntityManager.persistAndFlush(testRecipe);
    }

    @Test
    public void givenRecipe_whenUpdated_thenFoundByIdWithUpdateData() {
        Recipe updateRecipe = new Recipe();
        updateRecipe.setName("Test Updated Recipe");
        recipeRepository.updateById(testRecipe.getId(), updateRecipe.getName());
        testEntityManager.clear();
        Recipe updatedRecipe = recipeRepository.findById(testRecipe.getId()).get();
        assertThat(updatedRecipe.getName()).isEqualTo(updateRecipe.getName());
    }

    @Test
    public void givenNullRecipeName_whenUpdated_thenThrowsDataIntegrityViolationException() {
        assertThatThrownBy(() -> recipeRepository.updateById(testRecipe.getId(), null))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void givenExistingRecipe_whenUpdated_thenThrowsDataIntegrityViolationException() {
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setName("Test Updated Recipe");
        testEntityManager.persistAndFlush(updatedRecipe);
        testEntityManager.clear();
        assertThatThrownBy(() -> recipeRepository.updateById(updatedRecipe.getId(), testRecipe.getName()))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void givenRecipe_whenDeleted_thenFoundByIdWithDeleteData() {
        int deletedRows = recipeRepository.deleteRecipeById(testRecipe.getId());
        testEntityManager.clear();
        assertThat(deletedRows).isEqualTo(1);
        Optional<Recipe> deletedRecipe = recipeRepository.findById(testRecipe.getId());
        assertThat(deletedRecipe).isEmpty();
    }

    @Test
    public void givenRecipe_whenDeleted_thenNoRowsAffected() {
        int deletedRows = recipeRepository.deleteRecipeById(5L);
        testEntityManager.clear();
        assertThat(deletedRows).isEqualTo(0);
    }

}
