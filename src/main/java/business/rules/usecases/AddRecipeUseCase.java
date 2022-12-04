package business.rules.usecases;

import business.rules.base.UseCase;
import business.rules.base.UseCaseRequest;
import business.rules.base.UseCaseResponse;

public class AddRecipeUseCase implements UseCase {
    private final String addSuccess = "Recipe added successfully";
    private final String addFailure = "Failed to add recipe";

    @Override
    public UseCaseResponse process(UseCaseRequest ucr) {
        return null;
    }

    @Override
    public int getEndStage() {
        return 1;
    }

    @Override
    public String getJob() {
        return "adding recipe";
    }
}
