package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.Ingredient;
import com.ogulcanonder.chef_mind.model.IngredientCategory;
import com.ogulcanonder.chef_mind.model.Recipe;
import com.ogulcanonder.chef_mind.model.RecipeIngredient;
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
public class RecipeIngredientRepositoryTests {

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    private RecipeIngredient testRecipeIngredient;
    private Recipe testRecipe, testRecipe2;
    private Ingredient testIngredient, testUpdateIngredients;


    @BeforeEach()
    public void setup() {
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setIngredientCategoryName("Test Ingredient Category");
        IngredientCategory savedCategory = testEntityManager.persist(ingredientCategory);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        testRecipe = testEntityManager.persistAndFlush(newRecipe);

        Ingredient newIngredient = new Ingredient();
        newIngredient.setName("Test Ingredient");
        newIngredient.setIngredientCategory(savedCategory);
        testIngredient = testEntityManager.persistAndFlush(newIngredient);

        Recipe updateRecipe = new Recipe();
        updateRecipe.setName("Updated Recipe");
        testRecipe2 = testEntityManager.persistAndFlush(updateRecipe);

        Ingredient updateIngredient = new Ingredient();
        updateIngredient.setName("Updated Ingredient");
        updateIngredient.setIngredientCategory(savedCategory);
        testUpdateIngredients = testEntityManager.persistAndFlush(updateIngredient);

        testRecipeIngredient = new RecipeIngredient();
        testRecipeIngredient.setRecipe(testRecipe);
        testRecipeIngredient.setIngredient(testIngredient);
        testRecipeIngredient.setRequired(true);
        testEntityManager.persistAndFlush(testRecipeIngredient);
    }

    @Test
    public void givenRecipeIngredient_whenUpdated_thenFoundByIdWithUpdateData() {
        recipeIngredientRepository.updateById(testUpdateIngredients.getId(), testRecipe2.getId(), false, testRecipeIngredient.getId());
        testEntityManager.clear();
        RecipeIngredient updateRecipeIngredient = recipeIngredientRepository.findById(testRecipeIngredient.getId()).get();
        assertThat(updateRecipeIngredient.getIngredient().getId()).isEqualTo(testUpdateIngredients.getId());
        assertThat(updateRecipeIngredient.getRecipe().getId()).isEqualTo(testRecipe2.getId());
        assertThat(updateRecipeIngredient.isRequired()).isFalse();
    }

    @Test
    public void givenNullRecipeIngredient_whenUpdated_thenThrowDataIntegrityViolationException() {
        assertThatThrownBy(() -> recipeIngredientRepository.updateById(null, null, false, testRecipeIngredient.getId()))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void givenExistingRecipeIngredient_whenUpdated_thenThrowsDataIntegrityViolationException() {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(testRecipe2);
        recipeIngredient.setIngredient(testUpdateIngredients);
        recipeIngredient.setRequired(true);
        testEntityManager.persistAndFlush(recipeIngredient);
        testEntityManager.clear();
        assertThatThrownBy(() -> recipeIngredientRepository.updateById(testIngredient.getId(), testRecipe.getId(), true, recipeIngredient.getId()))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void givenRecipeIngredient_whenDeleted_thenFoundByIdWithDeleteData() {
        int deletedRows = recipeIngredientRepository.deleteRecipeIngredientById(testRecipeIngredient.getId());
        testEntityManager.clear();
        assertThat(deletedRows).isEqualTo(1);
        Optional<RecipeIngredient> deleteRecipeIngredient = recipeIngredientRepository.findById(testRecipeIngredient.getId());
        assertThat(deleteRecipeIngredient).isEmpty();
    }

    @Test
    public void givenRecipeIngredient_whenDeleted_thenNoRowsAffected() {
        int deletedRows = recipeIngredientRepository.deleteRecipeIngredientById(2L);
        testEntityManager.clear();
        assertThat(deletedRows).isEqualTo(0);
    }


}
