package ucf.assignments;

import java.util.ArrayList;

public class CheckErrors {
    public Boolean checkIf(String serialNumber, ArrayList<Item> itemArrayList){
        boolean check = true;
        for(int i = 0; i <itemArrayList.size(); i++){
            if(itemArrayList.get(i).getSerialNumber().toString().equalsIgnoreCase(serialNumber.toString())){
                check = false;
            }
        }
        return check;
    }

}
