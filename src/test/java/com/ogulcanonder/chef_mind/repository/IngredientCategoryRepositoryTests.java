package com.ogulcanonder.chef_mind.repository;


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
public class IngredientCategoryRepositoryTests {

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    private IngredientCategory testIngredientCategory;

    @BeforeEach
    public void setup() {
        testIngredientCategory = new IngredientCategory();
        testIngredientCategory.setIngredientCategoryName("Test Ingredient Category");
        testEntityManager.persistAndFlush(testIngredientCategory);
    }

    @Test
    public void givenIngredientCategory_whenUpdated_thenFoundByIdWithUpdateData() {
        IngredientCategory updatedIngredientCategory = new IngredientCategory();
        updatedIngredientCategory.setIngredientCategoryName("Test Updated Ingredient Category");
        ingredientCategoryRepository.updateById(testIngredientCategory.getId(), updatedIngredientCategory.getIngredientCategoryName());
        testEntityManager.clear();
        IngredientCategory updateIngredientCategory = ingredientCategoryRepository.findById(testIngredientCategory.getId()).get();
        assertThat(updateIngredientCategory.getIngredientCategoryName()).isEqualTo(updatedIngredientCategory.getIngredientCategoryName());
    }

    @Test
    public void givenNullIngredientCategory_whenUpdated_thenThrowsDataIntegrityViolationException() {
        assertThatThrownBy(() -> ingredientCategoryRepository.updateById(testIngredientCategory.getId(), null))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void givenExistingIngredientCategory_whenUpdated_thenThrowsDataIntegrityViolationException() {
        IngredientCategory updatedIngredientCategory = new IngredientCategory();
        updatedIngredientCategory.setIngredientCategoryName("Test Update Ingredient Category");
        testEntityManager.persistAndFlush(updatedIngredientCategory);
        testEntityManager.clear();
        assertThatThrownBy(() -> ingredientCategoryRepository.updateById(updatedIngredientCategory.getId(), testIngredientCategory.getIngredientCategoryName()))
                .isInstanceOf(DataIntegrityViolationException.class);

    }

    @Test
    public void givenIngredientCategory_whenDeleted_thenFoundByIdWithDeleteData() {
        int deletedRows = ingredientCategoryRepository.deleteByIngredientCategoryId(testIngredientCategory.getId());
        testEntityManager.clear();
        assertThat(deletedRows).isEqualTo(1);
        Optional<IngredientCategory> deletedIngredientCategory = ingredientCategoryRepository.findById(testIngredientCategory.getId());
        assertThat(deletedIngredientCategory).isEmpty();
    }

    @Test
    public void givenIngredientCategory_whenDeleted_thenNoRowsAffected() {
        int deletedRows = ingredientCategoryRepository.deleteByIngredientCategoryId(2L);
        testEntityManager.clear();
        assertThat(deletedRows).isEqualTo(0);
    }
}
