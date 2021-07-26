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
    public Boolean checkSerialLen(String x){
        Boolean check = false;
        if(x.length()<10 || x.length() > 10){
            check = true;
        }
        return check;
    }
    public boolean checkNameLen(String x){
        Boolean check = false;
        if(x.length() < 2 || x.length() > 256){
            check = true;
        }
        return check;
    }
}
