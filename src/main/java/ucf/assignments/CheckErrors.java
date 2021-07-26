package ucf.assignments;

import java.util.ArrayList;

public class CheckErrors {
    public Boolean checkIf(String x){
        Boolean check = false;
        String specialCharacters = "[^a-zA-Z0-9]+";
        if(x == null){
            check = false;
        }
        else if(x.matches(specialCharacters)){
            check = true;
        }
        return check;
    }

}
