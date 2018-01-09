package mmazurkiewicz.controllers;

import mmazurkiewicz.services.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private RecipesService recipesService;

    @Autowired
    public IndexController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping({"/","/index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipes", recipesService.getRecipes());
        return "index";
    }
}
