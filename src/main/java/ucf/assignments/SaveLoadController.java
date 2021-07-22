package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SaveLoadController implements Initializable {
    FileChooser fileChooser = new FileChooser();
    List<String> fileNames;
    @FXML
    void saveFile(ActionEvent actionEvent){

        Stage saveStage = new Stage();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Files", fileNames));
        try{
            File file = fileChooser.showSaveDialog(saveStage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileNames = new ArrayList<>();
        fileNames.add(" *.txt");
        fileNames.add("*.json");
        fileNames.add("*.html");
    }
}
