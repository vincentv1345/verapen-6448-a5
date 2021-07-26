package ucf.assignments;
import static javafx.application.Application.launch;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Vincent Verapen
 */
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
public class App extends Application {
    public static void main(String []args) { launch(args); }
        public void start(Stage primaryStage) throws IOException {
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("InventoryController.fxml"));
            primaryStage.setTitle("Inventory");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
}
