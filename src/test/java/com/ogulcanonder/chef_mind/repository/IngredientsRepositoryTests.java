package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.Ingredient;
import com.ogulcanonder.chef_mind.model.IngredientCategory;
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
public class IngredientsRepositoryTests {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    TestEntityManager testEntityManager;
    private Ingredient testIngredient;
    private IngredientCategory testIngredientCategory;

    @BeforeEach
    public void setup() {
        testIngredientCategory = new IngredientCategory();
        testIngredientCategory.setIngredientCategoryName("Test Ingredient Category");
        IngredientCategory savedIngredientCategory = testEntityManager.persist(testIngredientCategory);
        testIngredient = new Ingredient();
        testIngredient.setName("Test Ingredient");
        testIngredient.setIngredientCategory(savedIngredientCategory);
        testEntityManager.persistAndFlush(testIngredient);
    }

    @Test
    public void givenIngredient_whenUpdated_thenFoundByIdWithUpdateData() {
        Ingredient updatedIngredient = new Ingredient();
        updatedIngredient.setName("Updated Ingredient");
        updatedIngredient.setIngredientCategory(testIngredient.getIngredientCategory());
        ingredientRepository.updateNameAndCategoryId(testIngredient.getId(), updatedIngredient.getName(), updatedIngredient.getIngredientCategory().getId());
        testEntityManager.clear();
        Ingredient findByIngredient = ingredientRepository.findById(testIngredient.getId()).get();
        assertThat(findByIngredient.getName()).isEqualTo(updatedIngredient.getName());
        assertThat(findByIngredient.getIngredientCategory().getId()).isEqualTo(updatedIngredient.getIngredientCategory().getId());
    }

    @Test
    public void givenNullIngredient_whenUpdated_thenThrowDataIntegrityViolationException() {
        assertThatThrownBy(() -> ingredientRepository.updateNameAndCategoryId(testIngredient.getId(), null, null))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void givenExistingIngredient_whenUpdated_thenThrowsDataIntegrityViolationException() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Updated Ingredient");
        ingredient.setIngredientCategory(testIngredientCategory);
        testEntityManager.persistAndFlush(ingredient);
        testEntityManager.clear();
        assertThatThrownBy(() -> ingredientRepository.updateNameAndCategoryId(testIngredient.getId(), ingredient.getName(), ingredient.getIngredientCategory().getId()))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void givenIngredient_whenDeleted_thenFoundByIdWithDeleteData() {
        int deletedRows = ingredientRepository.deleteIngredientById(testIngredient.getId());
        testEntityManager.clear();
        assertThat(deletedRows).isEqualTo(1);
        Optional<Ingredient> findByIngredient = ingredientRepository.findById(testIngredient.getId());
        assertThat(findByIngredient).isEmpty();
    }

    @Test
    public void givenIngredient_whenDeleted_thenNoRowsAffected() {
        int deletedRows = ingredientRepository.deleteIngredientById(2L);
        testEntityManager.clear();
        assertThat(deletedRows).isEqualTo(0);
    }
}
