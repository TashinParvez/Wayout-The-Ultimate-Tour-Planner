package wayout.files.LoginPage;

import com.jfoenix.controls.JFXCheckBox;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import javafx.scene.shape.Circle;
import java.util.ResourceBundle;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.oauth2.Oauth2;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Arrays;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class LoginController implements Initializable {

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
    void forgotPassClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("resetPass.fxml"));
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void rememberMeSelected(ActionEvent event) {

    }

    @FXML
    void signInClicked(ActionEvent event) {
        String mail=EmailorUsernameBox.getText();
        String pass=passwordBox.getText();

        String url = "jdbc:mysql://127.0.0.1/wayout";
        String username = "root";
        String password = "";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        try{

            String chk1="Select * from accountinfo where email=?";
            String chk2="Select * from accountinfo where username=?";
            Connection con;
            PreparedStatement pst1,pst2;

            con= DriverManager.getConnection(url, username, password);

            pst1=con.prepareStatement(chk1);
            pst2=con.prepareStatement(chk2);

            pst1.setString(1,mail);
            pst2.setString(1,mail);

            ResultSet rs1,rs2;
            rs1=pst1.executeQuery();
            rs2=pst2.executeQuery();

            if(rs1.next()){
                String pas=rs1.getString("password");
                if(pas.equals(pass)){
                    String fullName=rs1.getString("fullName");
                    String em=rs1.getString("email");
                    String datofBirth=rs1.getString("dob");
                    String gend=rs1.getString("gender");
                    String usern=rs1.getString("username");

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
            } else if (rs2.next()) {
                String pas=rs2.getString("password");
                if(pas.equals(pass)){
                    String fullName=rs2.getString("fullName");
                    String em=rs2.getString("email");
                    String datofBirth=rs2.getString("dob");
                    String gend=rs2.getString("gender");
                    String usern=rs2.getString("username");

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

            } else{
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

    // google api properties
    private static final String CLIENT_ID = "156066230892-j88hek91hj3h39bcqt1aqh1d176i76o5.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

    private static final String SCOPE = "email profile";


    private GoogleAuthorizationCodeFlow flow;


    @FXML
    void signinGoogleClicked(ActionEvent event) throws GeneralSecurityException, IOException, URISyntaxException {
        // Generate the Google Authorization URL
        String authorizationUrl = flow.newAuthorizationUrl()
                .setRedirectUri(REDIRECT_URI)
                .build();

        // Open the Authorization URL in the user's default browser
        Desktop.getDesktop().browse(new URI(authorizationUrl));

        // Prompt the user to enter the authorization code
        String authorizationCode = JOptionPane.showInputDialog("Enter the authorization code:");


        // Exchange the authorization code for an access token and a refresh token
        GoogleTokenResponse response = flow.newTokenRequest(authorizationCode)
                .setRedirectUri(REDIRECT_URI)
                .execute();

        // Use the access token to fetch the user's profile data
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(GoogleNetHttpTransport.newTrustedTransport())
                .setJsonFactory(new GsonFactory())
                .setClientSecrets(CLIENT_ID, CLIENT_SECRET)
                .build()
                .setAccessToken(response.getAccessToken())
                .setRefreshToken(response.getRefreshToken());

        Oauth2 oauth2 = new Oauth2.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                new GsonFactory(),
                credential)
                .build();

        Userinfo userinfo = oauth2.userinfo().get().execute();
        // Print the user's profile data to the console
        System.out.println("User ID: " + userinfo.getId());
        System.out.println("Email: " + userinfo.getEmail());
        System.out.println("Name: " + userinfo.getName());
        System.out.println("Given Name: " + userinfo.getGivenName());
        System.out.println("Gender: "+userinfo.getGender());
        System.out.println("Image: "+userinfo.getPicture());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            flow = new GoogleAuthorizationCodeFlow.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    new GsonFactory(),
                    CLIENT_ID,
                    CLIENT_SECRET,
                    Collections.singleton(SCOPE))
                    .setAccessType("offline")
                    .build();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
