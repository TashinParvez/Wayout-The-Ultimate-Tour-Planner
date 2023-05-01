package wayout.files.Homepage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import wayout.files.Dashboard.UserDashboardController;

public class Homepage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("HomePage_2nd.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Homepage");

//        Image icon = new Image(UserDashboardController.class.getClass().getResourceAsStream("baggage.png"));
//
//        // Set the stage icons
//        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
