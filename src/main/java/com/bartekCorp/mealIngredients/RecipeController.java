package com.bartekCorp.mealIngredients;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
public class RecipeController {

    private final RecipeRepository repository;

    RecipeController(RecipeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/recipies")
    List<Recipe> all() {
        return (List<Recipe>) repository.findAll();
    }

    @GetMapping("/ingredients")
    List<Ingredient> ingredients() {
        return repository.findIngredients();
    }

    @PostMapping(path = "/recipie", consumes = "application/json")
    @ResponseBody
    ResponseEntity<String> post(@RequestBody RecipeModel recipeModel) {
        Recipe recipe = new Recipe(recipeModel.getName(),new HashSet<>());
        for (String ingredient : recipeModel.getIngredients()) {
            recipe.addIngredient(new Ingredient(ingredient));
        }
        repository.save(recipe);
        return new ResponseEntity<String>("POST Response", HttpStatus.OK);
    }
}
