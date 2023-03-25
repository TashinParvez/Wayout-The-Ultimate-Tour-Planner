package wayout.files.Homepage;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import wayout.files.LoginPage.LoginController;
import wayout.files.LoginPage.SignUpController;

import java.io.IOException;

public class HomePage_2nd_Controller {

    @FXML
    private Hyperlink aboutUs;

    @FXML
    private MFXButton signIn;

    @FXML
    private MFXButton signUP;

    private Stage stage;
    private Scene scene;

    @FXML
    void aboutUsClicked(ActionEvent event) {

    }

    @FXML
    void guideClicked(MouseEvent event) {

    }

    @FXML
    void hotelClicked(MouseEvent event) {

    }

    @FXML
    void signInClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(LoginController.class.getResource("login.fxml"));
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signUPClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(SignUpController.class.getResource("signUP.fxml"));
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void transportClicked(MouseEvent event) {

    }

    @FXML
    void tripsClicked(MouseEvent event) {

    }

}
