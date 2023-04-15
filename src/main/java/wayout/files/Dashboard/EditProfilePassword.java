package wayout.files.Dashboard;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class EditProfilePassword {
    @FXML
    private MFXButton Back;

    @FXML
    private MFXButton Submit;

    @FXML
    private MFXPasswordField confirmNewPass;

    @FXML
    private MFXPasswordField currentPass;

    @FXML
    private MFXPasswordField newPass;
    private Dashboard3 parentController;

    private EditProfile parentContainer;
    Dashboard dashboard;

    public void setParentContainer(EditProfile parentContainer) {
        this.parentContainer = parentContainer;
    }

    @FXML
    void backClicked(ActionEvent event) throws IOException {
        // load the edit profile screen
        dashboard = new Dashboard();

    }
}
