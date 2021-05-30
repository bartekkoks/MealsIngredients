package com.bartekCorp.mealIngredients;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {

    List<Recipe> findByName(String name);

    @Query("SELECT e FROM Ingredient e WHERE e.recipe.id = (:id)")
    List<Ingredient> findIngredientByRecipeId(long id);
}
