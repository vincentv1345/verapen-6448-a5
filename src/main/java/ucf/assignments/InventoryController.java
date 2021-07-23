package ucf.assignments;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.awt.*;
import javafx.stage.DirectoryChooser;
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
    FileChooser fileChooser = new FileChooser();
    List<String> fileNames;
    changeToCurrency c = new changeToCurrency();
    @FXML
    public void handleButton(ActionEvent event){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Alert.AlertType alertType = Alert.AlertType.ERROR;
        Alert serialNumberIsTheSame = new Alert(alertType, "Serial Number is the same");
        Alert nameSize = new Alert(alertType, "The name is either too small or too big");
        Alert isEmpty = new Alert(alertType, "The field is empty");
        ObservableList<Item> tempList = Tableview.getItems();
        try {
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i).getSerialNumber() == serialNumber.getText()) {
                    serialNumberIsTheSame.showAndWait();
                } else if (name.getText().isEmpty() || serialNumber.getText().isEmpty() || name.getText().isEmpty()) {
                    isEmpty.showAndWait();
                } else if (name.getText().length() > 256 || name.getText().length() < 2) {
                    nameSize.showAndWait();
                } else {
                    changeToCurrency c = new changeToCurrency();
                    ObservableList<Item> Item = Tableview.getItems();
                    Item inventoryItem = new Item(c.changeToCurrency(value.getText()), serialNumber.getText(), name.getText());
                    Tableview.getItems().add(inventoryItem);
                }
            }
        }
        catch(Exception e){
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
    void loadFile(ActionEvent actionEvent){

        Stage saveStage = new Stage();
        fileChooser.setTitle("Load File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TSV Files", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Html Files", "*.html"));
        try{
            File file = fileChooser.showOpenDialog(saveStage);
            fileChooser.setInitialDirectory(file.getParentFile());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void addButton(ActionEvent event){
        try{
            ObservableList<Item> Item = Tableview.getItems();
            Item inventoryItem = new Item(c.changeToCurrency(value.getText()), serialNumber.getText(), name.getText());
            Tableview.getItems().add(inventoryItem);
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
    }
}
