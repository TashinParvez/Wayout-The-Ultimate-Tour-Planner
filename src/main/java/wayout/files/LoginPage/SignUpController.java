package wayout.files.LoginPage;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wayout.files.LoginPage.LoginRunner;

import java.io.IOException;

public class SignUpController {

    @FXML
    private MFXButton backButton;

    @FXML
    private MFXPasswordField cPasswordBox;

    @FXML
    private MFXDatePicker dateofBirth;

    @FXML
    private MFXTextField emailBox;

    @FXML
    private MFXTextField fnameBox;

    @FXML
    private MFXComboBox<?> gender;

    @FXML
    private MFXTextField lNameBox;

    @FXML
    private MFXPasswordField passwordBox;

    @FXML
    private MFXButton signUP;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void backButtonClicked(ActionEvent event) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signUPClicked(ActionEvent event) {

    }

}
