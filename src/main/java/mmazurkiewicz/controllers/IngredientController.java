package mmazurkiewicz.controllers;

import lombok.extern.slf4j.Slf4j;
import mmazurkiewicz.services.RecipesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IngredientController {

    private final RecipesService recipesService;

    public IngredientController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        model.addAttribute("recipe", recipesService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }
}
