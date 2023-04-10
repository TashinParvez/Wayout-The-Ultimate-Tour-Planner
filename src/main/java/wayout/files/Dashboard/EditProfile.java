package wayout.files.Dashboard;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class EditProfile {

    @FXML
    private MFXButton BackButton;

    @FXML
    private MFXButton SubmitButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MFXDatePicker dob;

    @FXML
    private MFXTextField firstName;

    @FXML
    private MFXTextField lastName;

    @FXML
    private MFXTextField nationality;

    @FXML
    private MFXTextField phnNumber;

    @FXML
    private Circle profileCircle;

    @FXML
    private Hyperlink updatePass;

    @FXML
    private Hyperlink updateProfilePic;
    private Dashboard dashboard;

    @FXML
    void backClicked(ActionEvent event) {

    }

    @FXML
    void submitClicked(ActionEvent event) {

    }

    @FXML
    void updatePassClicked(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editPassword.fxml"));
        Parent root = loader.load();
        Dashboard dashboardController = loader.getController();
        dashboardController.borderPane.setCenter((Node)root);

//        Alert alert=new Alert(Alert.AlertType.INFORMATION);
//        alert.setContentText("Update Password Clicked");
//        alert.showAndWait();
    }

    @FXML
    void updateProfilePicClicked(ActionEvent event) throws Exception {

    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

}
