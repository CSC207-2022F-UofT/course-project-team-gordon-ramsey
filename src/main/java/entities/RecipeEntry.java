package entities;

import java.time.Instant;

public class RecipeEntry extends Entry {
    private Recipe recipe;

    public RecipeEntry(Instant time, Recipe recipe) {
        super(time);
        this.recipe = recipe;
    }
}
