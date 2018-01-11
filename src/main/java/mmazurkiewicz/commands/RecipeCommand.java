package mmazurkiewicz.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mmazurkiewicz.domain.Category;
import mmazurkiewicz.domain.Difficulty;
import mmazurkiewicz.domain.Ingredient;
import mmazurkiewicz.domain.Notes;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private Notes notes;
    private Set<Category> categories = new HashSet<>();
}
