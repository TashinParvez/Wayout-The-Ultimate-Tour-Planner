package wayout.files.Dashboard;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Dashboard3 implements Initializable {

    @FXML
    BorderPane _borderPane;

    @FXML
    private Label aboutBD;

    @FXML
    private Label alerts;

    @FXML
    private Label cart;

    @FXML
    private Label hireGuide;

    @FXML
    private Circle profileCircle;

    @FXML
    private Label review;

    @FXML
    private Label services;

    @FXML
    private AnchorPane topPane;

    @FXML
    private MFXTextField topSearchBar;

    @FXML
    private Label tours;
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private HBox _hboxForCard1;

    @FXML
    private WebView maps_restaurent;

    @FXML
    private WebView maps_Hotels;
    @FXML
    private WebView maps_Tours;
    @FXML
    MFXComboBox<String> moreComboBox;

    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] _possibleSuggestions={"Dhaka", "Chittagog","Chattagram","Sylhet","Narayanganj","Sonargaon","Comilla","UIU"};

    private Set<String> possibleSug=new HashSet<>(Arrays.asList(_possibleSuggestions));

    public void set_borderPane(Node node) {
        this._borderPane.setCenter(node);
    }

    Thread changePass=new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                root = FXMLLoader.load(getClass().getResource("editPassword.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
           set_borderPane(root);
        }
    });

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    WebEngine w1 = maps_restaurent.getEngine();
                    w1.load("https://www.google.com/maps/search/top+restaurants+in+bangladesh/@23.8095345,90.39476,12.75z");

                    WebEngine w2 = maps_Hotels.getEngine();
                    w2.load("https://www.google.com/maps/search/top+hotels+and+resorts+in+bangladesh/@23.8149308,90.4220729,14z/data=!3m1!4b1");

                    WebEngine w3 = maps_Tours.getEngine();
                    w3.load("https://www.google.com/maps/search/tourist+places+in+bangladesh/@23.8150074,90.4220729,14z/data=!3m1!4b1");
                });
            }
        });

        thread.start();


        // hover effect on label from controller:
        review.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                review.setStyle("-fx-font-family: 'Poppins', sans-serif; -fx-font-size: 15px; -fx-background-color: #ff0000; -fx-background-radius: 15px; -fx-background-insets: -7; -fx-cursor: pointer");
            }
        });
        review.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                review.setStyle("-fx-font-family: 'Poppins', sans-serif; -fx-font-size: 15px; -fx-background-color: #f9e45b; -fx-background-radius: 15px; -fx-background-insets: -7;");
            }
        });


//         profile pic circle properties

        Platform.runLater(() -> {
            try {
                profileCircle.setFill(new ImagePattern(new Image(getClass().getResource("user.png").openStream())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        profileCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    root = FXMLLoader.load(getClass().getResource("editProfile.fxml"));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                set_borderPane(root);
            }
        });

//        ......
        moreComboBox.getItems().addAll("About Bangladesh", "Contact us", "Logout");






        //autocomplete

        TextFields.bindAutoCompletion(topSearchBar,"Dhaka", "Chittagog","Chattagram","Sylhet","Narayanganj","Sonargaon","Comilla","UIU");


    }



    private EditProfile editProfileController;
    private EditProfilePassword changePasswordController;
    private void loadEditProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editProfile.fxml"));
            Parent editProfile = loader.load();
            editProfileController = loader.getController();
//            editProfileController.setParentController(this);
            _borderPane.setCenter(editProfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadChangePassword() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editPassword.fxml"));
            Parent changePassword = loader.load();
            changePasswordController = loader.getController();
//            changePasswordController.setParentController(this);
            _borderPane.setCenter(changePassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EditProfile getEditProfileController() {
        return editProfileController;
    }

    public EditProfilePassword getChangePasswordController() {
        return changePasswordController;
    }


}
