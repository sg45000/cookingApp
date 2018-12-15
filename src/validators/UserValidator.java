package validators;

import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserValidator {

    static public List<String> validate(User u ,Boolean password_check_flag,String plain_pass,String plain_pass_confirm){

        List<String> errors = new ArrayList<String>();

        String nameError = nameValidate(u.getName());
        String emailError = emailValidate(u.getEmail());
        String passwordError = passwordValidate(u.getPassword(),password_check_flag,plain_pass,plain_pass_confirm);

        if(nameError!="") {
            errors.add(nameError);
        }
        if(emailError!="") {
            errors.add(emailError);
        }
        if(passwordError!="") {
            errors.add(passwordError);
        }
        return errors;
    }

    static private String nameValidate(String name) {
        if(name==null || name.equals("")) {
            return "アカウント名を入力してください。";
        }
        return "";
    }

    static private String emailValidate(String email) {
        if(email==null||email.equals("")) {
            return "メールアドレスを入力してください";
        }
        return "";
    }
    private static String passwordValidate(String password,Boolean password_check_flag,String plain_pass,String plain_pass_confirm) {

        if(password_check_flag && (password==null || password.equals("")) || !plain_pass.equals(plain_pass_confirm) ) {
            return "パスワードが正しく入力されていません";
        }


        return "";
    }

}
