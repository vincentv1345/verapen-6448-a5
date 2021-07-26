package ucf.assignments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Vincent Verapen
 */
public class SaveLoadFiles {
    public void saveHTMLFile(File file, List<Item> inventoryItems){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("<div><h3>Value Serial Number Name<h3><div>");
            for(int i = 0; i< inventoryItems.size(); i++){
                bufferedWriter.write("<div><s>" + inventoryItems.get(i).getValue() +  " " + inventoryItems.get(i).getSerialNumber() + " " + inventoryItems.get(i).getName() + "<s><div><br>");
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
            writeToFile.write("Value \t Serial Number \t Name");
            for (int i = 0; i<inventoryItems.size(); i++) {
                writeToFile.write("\n");
                writeToFile.write(inventoryItems.get(i).getValue() + "\t" + inventoryItems.get(i).getSerialNumber() + "\t" + inventoryItems.get(i).getName());
            }
            writeToFile.close();
        }
        catch (Exception e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    public Collection<Item> openTextFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\t");
        Collection<Item> item = new ArrayList<>();
        while(scanner.hasNext()){
            String value = scanner.next();
            String serialNumber = scanner.next();
            String name = scanner.next();
            Item inventoryItem = new Item(value, serialNumber, name);
            item.add(inventoryItem);
        }
        return item;
    }
    public void openHTMLFile(File file){

    }
}
