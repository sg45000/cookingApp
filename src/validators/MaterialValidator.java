package validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.MaterialsType;
import model.MaterialsUnit;

public class MaterialValidator {

    public static List<String> validate(String name,String unit,String type){
        List<String> errors = new ArrayList<String>();
        String nameError = validateName(name);
        String unitError = validateUnit(unit);
        String typeError = validateType(type);

        if(nameError!="") {
            errors.add(nameError);
        }
        if(unitError!="") {
            errors.add(unitError);
        }
        if(typeError!="") {
            errors.add(typeError);
        }

        return errors;
    }


    private static String validateName(String name) {
        if(name==null || name.equals("")) {
            return "材料名が入力されていません。";
        }
        return "";
    }

    private static String validateUnit(String unit) {
        if(!Arrays.asList(MaterialsUnit.UNIT_NAMES).contains(unit)) {
            return "食材の単位が正しく指定されていません。";
        }
        return "";
    }

    private static String validateType(String type) {
        if(!Arrays.asList(MaterialsType.TYPES).contains(type)) {
            return "食材のタイプが正しく指定されていません。";
        }
        return "";
    }

}
