package com.bartekCorp.mealIngredients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;


@SpringBootApplication
public class MealIngredientsApplication {

	private static final Logger log = LoggerFactory.getLogger(MealIngredientsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MealIngredientsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RecipeRepository repository) {
		return (args) -> {
			Recipe recipe = new Recipe("Spaghetti",new HashSet<>());
			recipe.addIngredient(new Ingredient("Makaron Spaghetti"));
			recipe.addIngredient(new Ingredient("Pomidory w Puszce"));
			repository.save(recipe);
			recipe = new Recipe("Jajecznica",new HashSet<>());
			recipe.addIngredient(new Ingredient("Jajko"));
			recipe.addIngredient(new Ingredient("Cebula"));
			repository.save(recipe);
			recipe = new Recipe("Zupa Pomidorowa",new HashSet<>());
			recipe.addIngredient(new Ingredient("Pomidory w Puszce"));
			recipe.addIngredient(new Ingredient("Makaron Swiderki"));
			repository.save(recipe);

			log.info("Recipes found with findAll():");
			log.info("-------------------------------");
			for (Recipe recip : repository.findAll()) {
				log.info(recip.getName());
				log.info(repository.findIngredientByRecipeId(recip.getId()).toString());

			}

			log.info("");

			log.info("Recipe found with findByName('Jajecznica'):");
			log.info("--------------------------------------------");
			repository.findByName("Jajecznica").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");

		};
	}
}
