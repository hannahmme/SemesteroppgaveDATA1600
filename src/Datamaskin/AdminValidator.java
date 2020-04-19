package Datamaskin;

public class AdminValidator {

    public static boolean validateAdmin(String username, String password){
        if(username.matches("admin") && password.matches("admin")){
            return true;
        }
        return false;
    }
}
