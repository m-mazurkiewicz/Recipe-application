package mmazurkiewicz.controllers;

import mmazurkiewicz.services.ImageService;
import mmazurkiewicz.services.RecipesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipesService recipesService;

    public ImageController(ImageService imageService, RecipesService recipesService) {
        this.imageService = imageService;
        this.recipesService = recipesService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String showUploadForm(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipesService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/imageUploadForm";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String handleImagePost(@PathVariable String recipeId, @RequestParam("imageFile") MultipartFile file){
        imageService.saveImageFile(Long.valueOf(recipeId), file);

        return "redirect:/recipe/" + recipeId + "/show";
    }
}
