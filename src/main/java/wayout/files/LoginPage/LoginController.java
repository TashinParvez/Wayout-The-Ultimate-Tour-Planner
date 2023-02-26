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
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

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
        String mail=EmailorUsernameBox.getText();
        String pass=passwordBox.getText();

        String url = "jdbc:mysql://127.0.0.1/wayout_project";
        String username = "root";
        String password = "";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        try{

            String chk="Select * from User_accounts where email=?";
            Connection con;
            PreparedStatement pst;

            con= DriverManager.getConnection(url, username, password);
            pst=con.prepareStatement(chk);
            pst.setString(1,mail);
            ResultSet rs;
            rs=pst.executeQuery();

            if(rs.next()){
                String pas=rs.getString("pass");
                if(pas.equals(pass)){
                    String fullName=rs.getString("name");
                    String em=rs.getString("email");
                    String datofBirth=rs.getString("dob");
                    String gend=rs.getString("gender");
                    String usern=rs.getString("username");

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Welcome");
                    alert.setHeaderText("Login Successful");
                    alert.setContentText("Name: "+fullName+"\nEmail: "+em+"\nDate of Birth: "+datofBirth+"\nGender: "+gend+"\nUsername: "+usern);
                    alert.showAndWait();

                }else {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Wrong password");
                    alert.setContentText("Please enter your password correctly!");
                    alert.showAndWait();
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Wrong email/password");
                alert.setContentText("Please enter your email and password correctly!");
                alert.showAndWait();
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }

    @FXML
    void signinGoogleClicked(ActionEvent event) {

    }

}
