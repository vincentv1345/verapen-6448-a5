package ucf.assignments;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.awt.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Item> Tableview;
    @FXML
    private TableColumn<Item, String> valueColumn;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, String> serialNumberColumn;
    @FXML
    private TextField value;
    @FXML
    private TextField name;
    @FXML
    private TextField serialNumber;
    @FXML
    private Button Button;
    @FXML
    private TextField searchBar;
    FileChooser fileChooser = new FileChooser();
    changeToCurrency c = new changeToCurrency();
    SearchClass s = new SearchClass();
    SaveLoadFiles sl = new SaveLoadFiles();
    private ObservableList<Item> finalList = FXCollections.observableArrayList();
    List<Item> inventoryList = finalList.stream().collect(Collectors.toList());
    ArrayList<Item> arrayInventoryList = new ArrayList<Item>(inventoryList);
    @FXML
    public void validate(ActionEvent event) {
        Window stage = new Stage();
        Alert serialNumberIsTheSame = new Alert(Alert.AlertType.ERROR);
        serialNumberIsTheSame.getDialogPane().setContentText("Serial Number is the same. ");
        Alert nameSize = new Alert(Alert.AlertType.ERROR);
        nameSize.getDialogPane().setContentText("The name size is too small or too big. ");
        Alert isEmpty = new Alert(Alert.AlertType.ERROR);
        isEmpty.getDialogPane().setContentText("One of the text boxes are empty. ");
        ObservableList<Item> tempList = Tableview.getItems();
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).getSerialNumber().equals(serialNumber.getText()) || serialNumber.getText().length() > 6) {
                serialNumberIsTheSame.initModality(Modality.APPLICATION_MODAL);
                serialNumberIsTheSame.initOwner(stage);
                serialNumberIsTheSame.showAndWait();
                return;
            } else if (name.getText().isEmpty() || serialNumber.getText().isEmpty() || name.getText().isEmpty()) {
                isEmpty.initModality(Modality.APPLICATION_MODAL);
                isEmpty.initOwner(stage);
                isEmpty.showAndWait();
                return;
            } else if (name.getText().length() > 256 || name.getText().length() < 2) {
                nameSize.initModality(Modality.APPLICATION_MODAL);
                nameSize.initOwner(stage);
                nameSize.showAndWait();
                return;
            }
        }
        Item inventoryItem = new Item(c.changeToCurrency(value.getText()), serialNumber.getText(), name.getText());
        Tableview.getItems().add(inventoryItem);
    }
    @FXML
    void saveFile(ActionEvent actionEvent){
        Stage saveStage = new Stage();
        fileChooser.setTitle("Save File");
        fileChooser.setInitialFileName("savefile");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TSV Files", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Html Files", "*.html"));
        try{
            File file = fileChooser.showSaveDialog(saveStage);
            fileChooser.setInitialDirectory(file.getParentFile());
            if(file != null && file.getAbsolutePath().endsWith(".txt")){
                sl.saveTextFile(file, arrayInventoryList);
            }
            else if(file != null && file.getAbsolutePath().endsWith(".html")){
                sl.saveHTMLFile(file, arrayInventoryList);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void close(ActionEvent event){
        Platform.exit();
    }
    @FXML
    void loadFile(ActionEvent actionEvent){
        Stage loadStage = new Stage();
        fileChooser.setTitle("Load File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TSV Files", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Html Files", "*.html"));
        try{
            File file = fileChooser.showOpenDialog(loadStage);
            if(file != null){
                Scanner scanner = new Scanner(System.in);
                while(scanner.hasNextLine()){

                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void saveEditName(TableColumn.CellEditEvent<Item, String> editInventory){
        Item inventoryItem = Tableview.getSelectionModel().getSelectedItem();
        inventoryItem.setName(editInventory.getNewValue());
    }
    @FXML
    public void saveEditValue(TableColumn.CellEditEvent<Item, String> editInventory){
        Item inventoryItem = Tableview.getSelectionModel().getSelectedItem();
        inventoryItem.setValue(editInventory.getNewValue());
    }
    @FXML
    public void saveEditSerialNumber(TableColumn.CellEditEvent<Item, String> editInventory){
        Item inventoryItem = Tableview.getSelectionModel().getSelectedItem();
        inventoryItem.setSerialNumber(editInventory.getNewValue());
    }
    @FXML
    public void removeButton(ActionEvent event){
        ObservableList<Item> itemSelected = Tableview.getSelectionModel().getSelectedItems();
        ObservableList<Item> tempItemArray = Tableview.getItems();
        for(Item i: itemSelected){
            tempItemArray.remove(i);
        }
    }
    @FXML
    public ObservableList<Item> getItems(){
        ObservableList<Item> Item = FXCollections.observableArrayList();
        return Item;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valueColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("value"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        Tableview.setItems(getItems());
        Tableview.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        Tableview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        finalList.addAll(Tableview.getItems());
        List<Item> inventoryList = finalList.stream().collect(Collectors.toList());
        ArrayList<Item> arrayInventoryList = new ArrayList<Item>(inventoryList);
        FilteredList<Item> filteredList = new FilteredList<>(finalList, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(item -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCase = newValue.toLowerCase();
                if(item.getSerialNumber().toLowerCase().indexOf(lowerCase) != -1){
                    return true;
                }
                else if(item.getName().toLowerCase().indexOf(lowerCase) != -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });
        /*
        SortedList<Item> newSortedList = new SortedList<>(filteredList);
        Tableview.setItems(newSortedList);*/
    }
}
