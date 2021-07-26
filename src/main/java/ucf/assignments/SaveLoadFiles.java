package ucf.assignments;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveLoadFiles {
    public void saveHTMLFile(File file, List<Item> inventoryItems){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("<div><h3>Value Serial Number Name<h3><div>");
            for(int i = 0; i< inventoryItems.size(); i++){
                bufferedWriter.write("<div><p>" + inventoryItems.get(i) +  " " + "<p><div><br>");
            }
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    public void saveTextFile(File file, List<Item> inventoryItems){
        try {
            FileWriter writeToFile = new FileWriter(file);
            writeToFile.write("Value \t \t Serial Number \t Name");
            for (int i = 0; i<inventoryItems.size(); i++) {
                writeToFile.write("\n");
                writeToFile.write(inventoryItems.get(i).getValue() + " " + inventoryItems.get(i).getSerialNumber() + " " + inventoryItems.get(i).getName());
            }
            writeToFile.close();
        }
        catch (Exception e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
