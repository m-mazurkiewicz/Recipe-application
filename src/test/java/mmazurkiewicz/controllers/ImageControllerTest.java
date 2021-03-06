package mmazurkiewicz.controllers;

import mmazurkiewicz.commands.RecipeCommand;
import mmazurkiewicz.services.ImageService;
import mmazurkiewicz.services.RecipesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipesService recipesService;

    ImageController imageController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        imageController = new ImageController(imageService, recipesService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void getImageForm() throws Exception{
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipesService.findCommandById(anyLong())).thenReturn(command);

        //when
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));

        verify(recipesService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void handleImagePost() throws Exception{
        MockMultipartFile multipartFile = new MockMultipartFile("imageFile", "testing.txt", "text/plain",
                "Spring framework".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    public void renderImageFromDatabase() throws Exception{
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        String string = "fake text";
        Byte[] bytesBoxed = new Byte[string.getBytes().length];

        int iterator = 0;

        for (byte primitiveByte : string.getBytes()){
            bytesBoxed[iterator++] = primitiveByte;
        }

        command.setImage(bytesBoxed);

        when(recipesService.findCommandById(anyLong())).thenReturn(command);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeImage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(string.getBytes().length, responseBytes.length);
    }
}
