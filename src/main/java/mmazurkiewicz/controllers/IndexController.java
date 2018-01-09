package mmazurkiewicz.controllers;

import mmazurkiewicz.domain.Category;
import mmazurkiewicz.domain.UnitOfMeasure;
import mmazurkiewicz.repositories.CategoryRepository;
import mmazurkiewicz.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @GetMapping({"/","/index"})
    public String getIndexPage(){

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon");

        System.out.println("Cat id: " + categoryOptional.get().getId());
        System.out.println("Unit id: " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
