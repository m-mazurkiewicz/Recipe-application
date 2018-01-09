package mmazurkiewicz.services;

import mmazurkiewicz.domain.Recipe;
import mmazurkiewicz.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipesServiceImpl implements RecipesService{

    private final RecipeRepository recipeRepository;

    public RecipesServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
