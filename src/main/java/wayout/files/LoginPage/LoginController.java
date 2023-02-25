package wayout.files.LoginPage;

import com.jfoenix.controls.JFXCheckBox;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private MFXTextField EmailorUsernameBox;

    @FXML
    private MFXButton SignIn;

    @FXML
    private Hyperlink createAcc;

    @FXML
    private Hyperlink forgotPass;

    @FXML
    private MFXPasswordField passwordBox;

    @FXML
    private JFXCheckBox remember;

    @FXML
    private MFXButton signinGoogle;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void createAccClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("signUP.fxml"));
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void forgotPassClicked(ActionEvent event) {

    }

    @FXML
    void rememberMeSelected(ActionEvent event) {

    }

    @FXML
    void signInClicked(ActionEvent event) {

    }

    @FXML
    void signinGoogleClicked(ActionEvent event) {

    }

}
