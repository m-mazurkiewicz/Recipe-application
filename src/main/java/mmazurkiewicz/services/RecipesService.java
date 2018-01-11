package mmazurkiewicz.services;

import mmazurkiewicz.domain.Recipe;

import java.util.Set;

public interface RecipesService {
    public Set<Recipe> getRecipes();
    public Recipe findById(long l);
}
