package ucf.assignments;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class Main {
    public static void main(String []args) { launch(args); }
        public void start(Stage primaryStage) throws IOException {
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("InventoryController.fxml"));
            primaryStage.setTitle("Inventory");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
}
