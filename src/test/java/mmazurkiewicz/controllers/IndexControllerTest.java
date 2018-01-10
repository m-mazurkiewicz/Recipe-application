package mmazurkiewicz.controllers;

import mmazurkiewicz.services.RecipesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipesService recipesService;

    @Mock
    Model model;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipesService);
    }

    @Test
    public void getIndexPage() throws Exception {

        String output = indexController.getIndexPage(model);
        assertEquals(output, "index");

        verify(recipesService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"),anySet());
    }

}