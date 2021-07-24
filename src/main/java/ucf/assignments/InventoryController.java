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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
public class InventoryController implements Initializable {
    @FXML
    private TextField searchBar;
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
    FileChooser fileChooser = new FileChooser();
    changeToCurrency c = new changeToCurrency();

    public TextField getSearchBar() {
        return searchBar;
    }
    @FXML
    public void searchList(){
        FilteredList<Item> filteredList = getFilter();
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> { filteredList.setPredicate(item -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String toLowerCase = newValue.toLowerCase();
            //Compares the names of items to each other
            if (item.getName().toLowerCase().indexOf(toLowerCase) != -1) {
                return true;
            } else if (item.getSerialNumber().indexOf(toLowerCase) != -1) {
                return true;
            } else {
                return false;
            }
            });
        });
        SortedList<Item> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(Tableview.comparatorProperty());
        Tableview.setItems(sortedList);
    }
    @FXML
    public void validate() {
        Stage stage = new Stage();
        Alert serialNumberIsTheSame = new Alert(Alert.AlertType.ERROR);
        serialNumberIsTheSame.getDialogPane().setContentText("Serial Number is the same. ");
        Alert nameSize = new Alert(Alert.AlertType.ERROR);
        nameSize.getDialogPane().setContentText("The name size is too small or too big. ");
        Alert isEmpty = new Alert(Alert.AlertType.ERROR);
        isEmpty.setContentText("One of the text boxes are empty. ");
        ObservableList<Item> tempList = Tableview.getItems();
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).getSerialNumber().equals(serialNumber.getText())) {
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
    public void handleButton(){
                    try {
                        changeToCurrency c = new changeToCurrency();
                        ObservableList<Item> Item = Tableview.getItems();
                        Item inventoryItem = new Item(c.changeToCurrency(value.getText()), serialNumber.getText(), name.getText());
                        Tableview.getItems().add(inventoryItem);
                    }
                    catch (Exception e){
                        System.out.println("Something went wrong");
                        e.printStackTrace();
                    }
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
            fileChooser.setInitialDirectory(file.getParentFile());
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
    @FXML
    public FilteredList<Item> getFilter(){
        FilteredList<Item> filteredList = new FilteredList<>(Tableview.getItems(), b -> true);
        return filteredList;
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
    }
}
