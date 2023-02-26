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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import wayout.files.LoginPage.LoginRunner;

import java.io.IOException;

public class SignUpController implements Initializable{

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
    private MFXComboBox<String> gender;

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

        // Database connection:

        Connection con;
        PreparedStatement pst;

        String url = "jdbc:mysql://127.0.0.1:3306/wayout";
        String username = "limon";
        String password = "1122334455";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        // ending of connection of database



        // Insert data into database:
        String mailAuth="@";


        String a1=fnameBox.getText();
        String a2=lNameBox.getText();
        String a3=emailBox.getText();
        String a4=dateofBirth.getCurrentDate().toString();
        String a5=gender.getValue();
        String a6=passwordBox.getText();


        try {
            if((a1!=null) && (a2!=null) && (a3!=null && a3.contains(mailAuth)) && (a5!=null) && (a6!=null && a6.equals(cPasswordBox.getText()))){
                try{
                    System.out.println(fnameBox.getText());
                    System.out.println(lNameBox.getText());
                    System.out.println(emailBox.getText());
                    System.out.println(dateofBirth.getCurrentDate());
                    System.out.println(gender.getValue());
                    System.out.println(passwordBox.getText());

                    con=DriverManager.getConnection(url, username, password);

                    String user_first_name=fnameBox.getText();
                    String user_last_name=lNameBox.getText();
                    String user_email=emailBox.getText();
                    String user_date_of_birth=dateofBirth.getValue().toString();
                    String user_gender=gender.getValue();
                    String user_password=passwordBox.getText();



                    String query11 = "select count(*) from accountinfo";
                    Statement stmt=con.createStatement();
                    ResultSet rs=stmt.executeQuery(query11);
                    rs.next();
                    int count=rs.getInt(1);
                    System.out.println(count+1);

                    // auto generate username


                    char first_name_firstChar=user_first_name.charAt(0);

                    String[] lastnameX=user_last_name.split("\\s+");
                    String last=lastnameX[lastnameX.length-1];

                    String lowerLastname=last.toLowerCase();

                    String generated_username=first_name_firstChar+lowerLastname+(count+1);

                    String user_FullName=user_first_name+" "+user_last_name;

                    System.out.println(generated_username);


                    pst=con.prepareStatement("INSERT INTO accountinfo(firstName,lastName,fullName,email,dob,gender,username,password) VALUES(?,?,?,?,?,?,?,?)");
                    pst.setString(1,user_first_name);
                    pst.setString(2,user_last_name);
                    pst.setString(3,user_FullName);
                    pst.setString(4,user_email);
                    pst.setString(5, user_date_of_birth);
                    pst.setString(6,user_gender);
                    pst.setString(7,generated_username);
                    pst.setString(8,user_password);
                    pst.execute();

                    System.out.println("Insert successful");

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Welcome");
                    alert.setHeaderText("Login Successful");
                    alert.setContentText("Sign up Successful\nAutomatically Generated Username: "+generated_username+"\nUse this username to login into your account");
                    alert.showAndWait();



                }catch (Exception e){
                    e.getStackTrace();
                }
            }else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Sign UP Failed");
                alert.setContentText("Please enter all data correctly");
                alert.showAndWait();
            }

        }catch (Exception e){
            e.getStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.getItems().addAll(
                "Male",
                "Female",
                "Custom"
        );
    }
}
