package ucf.assignments;
import java.io.*;
import java.util.ArrayList;

public class SaveLoadFiles {
    public void saveHTMLFile(File file, ArrayList<Item> inventoryItems){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("<div><h3>Value Serial Number Name<h3><div>");
            for(int i = 0; i< inventoryItems.size(); i++){
                bufferedWriter.write("<div><p>" + inventoryItems.get(i).getValue() +  " " + inventoryItems.get(i).getSerialNumber() + " " + inventoryItems.get(i).getName()+"<p><div><br>");
            }
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
    public void saveTextFile(File file, ArrayList<Item> inventoryItems){
        try {
            FileWriter writeToFile = new FileWriter(file);
            writeToFile.write("Value \t \t Serial Number \t Name");
            for(int i = 0; i < inventoryItems.size(); i++){
                writeToFile.write(inventoryItems.get(i).getValue() +  " \t \t " + inventoryItems.get(i).getSerialNumber() + " \t " + inventoryItems.get(i).getName()+"\n");
            }
            writeToFile.close();
        }
        catch (Exception e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
