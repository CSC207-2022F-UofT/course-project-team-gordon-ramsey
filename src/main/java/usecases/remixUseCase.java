package usecases;

import entities.Recipe;

public class remixUseCase extends useCase {

    private Recipe toRemix;



    public remixUseCase(Recipe recipe){
        this.toRemix = recipe;
    }

    public remixUseCase(){}



}
