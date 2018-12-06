package validators;

import java.util.ArrayList;
import java.util.List;

public class RecipeValidator {

    public static List<String> validate(String name,String how_to) {
        List<String> errors = new ArrayList<String>();
        String nameError=nameValidate(name);
        String howToError=howToValidate(how_to);
        if(!nameError.equals("")) {
            errors.add(nameError);
        }
        if(!howToError.equals("")) {
            errors.add(howToError);
        }
        return errors;
    }

    private static String nameValidate(String name) {
        if(name==null||name.equals("")) {
            return "料理名が入力されていません。";
        }
        return "";
    }

    private static String howToValidate(String how_to) {
        if(how_to==null||how_to.equals("")) {
            return "作り方が入力されていません。";
        }
        return "";
    }




}
