package external.interfaces;

import business.rules.ChangeEvent;
import business.rules.Presenter;
import business.rules.UI;
import business.rules.UseCaseHandler.USE_CASE;

import java.util.Scanner;

public class CLI implements UI{

    private Presenter presenter;
    private Scanner reader;

    public CLI(Presenter presenter){
        this.presenter = presenter;
        this.reader = new Scanner(System.in);
    }

    public String getInput(){
        while(true){
            String input = this.reader.nextLine();
            if ("SearchRemixNew_recipeQuit".contains(input)){
                return input;
            }
            else{
                System.out.println("Invalid");
            }
        }

    }

    public void menu(){
        while (true) {
            System.out.print("MENU \n");
            System.out.print("Search, Remix, New_recipe, Quit \n");
            String input = getInput();
                switch (input) {
                    case "Search":
                        search();
                        break;
                    case "Remix":
                        remix();
                        break;
                    case "New_recipe":
                        newRecipe();
                        break;
                    case "Quit":
                        this.reader.close();
                        System.exit(0);
                }

            System.out.print("\n");
        }

    }

    /**
     * start point of a search recipe process, data passed is single String, the search string.
     */
    public void search(){
        System.out.print("Enter Search Keywords: ");
        Object[] keyword = {null};
        keyword[0] = this.reader.nextLine();
        this.presenter.fireEvent(new ChangeEvent(USE_CASE.SEARCH_RECIPE_USECASE, keyword));
    }

    public void remix(){
        System.out.print("Which recipe do you want to change: ");
        Object[] recipe = new Object[2];
        recipe[0] = this.reader.nextLine();
        recipe[1] = recipeInfo();
        this.presenter.fireEvent(new ChangeEvent(USE_CASE.REMIX_RECIPE_USECASE, recipe));

    }

    public void newRecipe(){
        Object[] newRecipe = recipeInfo();
        this.presenter.fireEvent(new ChangeEvent(USE_CASE.ADD_RECIPE_USECASE, newRecipe));
    }

    public Object[] recipeInfo() {
        Object[] info = new Object[5];
        System.out.print("Enter name: ");
        info[0] = this.reader.nextLine();
        System.out.print("Enter description: ");
        info[1] = this.reader.nextLine();
        System.out.print("Enter ingredients: ");
        info[2] = this.reader.nextLine();
        System.out.print("Enter instructions: ");
        info[3] = this.reader.nextLine();
        System.out.print("Enter cooking time: ");
        info[4] = this.reader.nextLine();

        return info;
    }

    public void showMessage(String msg){
        System.out.println(">> " + msg);
    }
}
