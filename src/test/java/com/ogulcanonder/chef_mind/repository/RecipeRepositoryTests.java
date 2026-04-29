package com.ogulcanonder.chef_mind.repository;

import com.ogulcanonder.chef_mind.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;
    private Recipe testRecipe;

    @BeforeEach
    public void setup() {
        testRecipe = new Recipe();
        testRecipe.setName("testRecipe");
        recipeRepository.save(testRecipe);
    }

    @Test
    void givenRecipe_WhenUpdated_ThenShouldPersistNewName() {
        testRecipe.setName("updatedRecipe");
        recipeRepository.save(testRecipe);
        Recipe updatedRecipe = recipeRepository.findById(testRecipe.getId()).orElse(null);
        assertNotNull(updatedRecipe);
        assertEquals("updatedRecipe", updatedRecipe.getName());
    }
}
