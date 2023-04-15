package wayout.files.Dashboard;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML
    BorderPane borderPane=new BorderPane();

    @FXML
    private MFXScrollPane _scrollPane;

    @FXML
    private Label aboutBD;

    @FXML
    private Label alerts;

    @FXML
    private Label hireGuide;

    @FXML
    private WebView maps_Hotels;

    @FXML
    private WebView maps_Tours;

    @FXML
    private WebView maps_restaurent;

    @FXML
    private MFXComboBox<?> moreComboBox;

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

    private Parent parent;
    private Scene scene;
    private Stage stage;
    @FXML
    void AlertsClicked(MouseEvent event) {

    }

    @FXML
    void cartClicked(MouseEvent event) {

    }

    @FXML
    void guideHireClicked(MouseEvent event) {

    }

    @FXML
    void moreSelected(ActionEvent event) {

    }


    @FXML
    void reviewClicked(MouseEvent event) {

    }

    @FXML
    void servicesClicked(MouseEvent event) {

    }

    @FXML
    void tourPackagesClicked(MouseEvent event) {

    }

    @FXML
    void profilePicClicked(MouseEvent event) throws IOException {
        parent= FXMLLoader.load(getClass().getResource("editProfile.fxml"));
        borderPane.setCenter(parent);
    }

    void loadDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        borderPane.setCenter(loader.load());
    }

    void loadProfileEdit(EditProfile parentContainer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editProfile.fxml"));
        borderPane.setCenter(loader.load());
    }

    void loadPasswordUpdate() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editPassword.fxml"));
        borderPane.setCenter(loader.load());
    }

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
    }
}
