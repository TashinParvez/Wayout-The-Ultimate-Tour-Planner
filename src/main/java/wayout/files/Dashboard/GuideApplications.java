package wayout.files.Dashboard;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class GuideApplications implements Initializable {
    @FXML
    private VBox cards_vbox;



    public void add_guide(String name, String city, String email, String mobile, String details, String nid, String hourly_charge, String available_time, Image image){
        HBox hBox=new HBox();
//        hBox.setStyle("-fx-background-color: rgba(137,194,154,0.3)");
        hBox.setPrefWidth(1020);
        hBox.setPrefHeight(380);
        AnchorPane anchorPane=new AnchorPane();

        anchorPane.setPrefWidth(1020);
        anchorPane.setPrefHeight(350);
//        anchorPane.setStyle("-fx-background-color: #ffffff");



        ImageView imageView=new ImageView(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(250);
        imageView.setLayoutY(10);
        imageView.setLayoutX(10);

        Label g_name=new Label(name);
        g_name.setLayoutX(290);
        g_name.setLayoutY(7);
        g_name.setStyle("-fx-font-size: 20px;" +
                "-fx-font-weight: bold" );


        Label av_time=new Label("Available time: "+available_time);
        av_time.setLayoutX(290);
        av_time.setLayoutY(252);
        av_time.setStyle(
                "-fx-padding: 0px;" +
                "-fx-font-size: 13px;" +
                "-fx-text-fill: black;" +
                        "-fx-font-weight: bold");


        Label av_loc=new Label("Available Area: "+city);
        av_loc.setLayoutX(290);
        av_loc.setLayoutY(232);
        av_loc.setStyle(
                "-fx-padding: 0px;" +
                "-fx-font-size: 13px;" +
                "-fx-text-fill: black;" +
                        "-fx-font-weight: bold");


        Label desc=new Label(details);
        desc.setLayoutX(290);
        desc.setLayoutY(45);
        desc.setMaxWidth(700);
        desc.setWrapText(true);


//+"|| Contact: "+ mobile +"|| NID/Passport: "+nid +"|| Hourly Charge: "+hourly_charge+"/= TK"
        Label email_=new Label("Email: "+email );
        email_.setLayoutX(290);
        email_.setLayoutY(172);
        email_.setStyle("-fx-background-color: #ffffff;" +
                "-fx-padding: 0px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold");


        Label phn=new Label("Contact: "+mobile);
        phn.setLayoutX(290);
        phn.setLayoutY(192);
        phn.setStyle("-fx-background-color:#ffffff ;" +
                "-fx-padding: 0px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"+
                "-fx-text-fill: black");



        Label nid_num=new Label("NID/Passport: "+nid);
        nid_num.setLayoutX(290);
        nid_num.setLayoutY(212);
        nid_num.setStyle(
                "-fx-padding: 0px;" +
                "-fx-font-size: 13px;" +
                "-fx-text-fill: black;"+
                "-fx-font-weight: bold;");



        Label charge=new Label("Hourly Charge: "+hourly_charge+" /= TK");
        charge.setLayoutX(290);
        charge.setLayoutY(272);
        charge.setStyle(
                "-fx-padding: 0px;" +
                "-fx-font-size: 13px;" +
                "-fx-text-fill: black;"+
                "-fx-font-weight: bold;");


        MFXButton approve=new MFXButton("Approve");
        approve.setLayoutX(780);
        approve.setLayoutY(250);
        approve.setStyle("-fx-padding: 7 15 7 15px;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 0px;" +
                "-fx-border-radius: 0px;" +
                "-fx-background-color: #00d38b;" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold");





        MFXButton reject=new MFXButton("Reject");
        reject.setLayoutX(870);
        reject.setLayoutY(250);
        reject.setStyle("-fx-padding: 7 15 7 15px;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 0px;" +
                "-fx-border-radius: 0px;" +
                "-fx-background-color: red;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white");

        anchorPane.getChildren().add(approve);
        anchorPane.getChildren().add(reject);
        anchorPane.getChildren().add(charge);
        anchorPane.getChildren().add(nid_num);
        anchorPane.getChildren().add(phn);
        anchorPane.getChildren().add(email_);
        anchorPane.getChildren().add(desc);
        anchorPane.getChildren().add(av_loc);
        anchorPane.getChildren().add(av_time);
        anchorPane.getChildren().add(g_name);
        anchorPane.getChildren().add(imageView);
        hBox.getChildren().add(anchorPane);
        cards_vbox.getChildren().add(hBox);




        approve.setOnAction(event -> {

            cards_vbox.getChildren().remove(hBox);


            String url = "jdbc:mysql://127.0.0.1/wayout";
            String username = "root";
            String password = "";


            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                String updateQuery = "UPDATE guides_list SET Status = ? WHERE NID = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

                preparedStatement.setString(1, "Approved");
                preparedStatement.setString(2, nid);

                // Execute the update statement
                int rowsUpdated = preparedStatement.executeUpdate();

                // Close the PreparedStatement object and the database connection
                preparedStatement.close();
                conn.close();

                // Print the number of rows updated to the console
                System.out.println(rowsUpdated + " rows updated");

            }catch (Exception e){
                e.printStackTrace();
            }


        });



        reject.setOnAction(event -> {
            cards_vbox.getChildren().remove(hBox);


            String url = "jdbc:mysql://127.0.0.1/wayout";
            String username = "root";
            String password = "";


            try {
                Connection conn = DriverManager.getConnection(url, username, password);
                String updateQuery = "UPDATE guides_list SET Status = ? WHERE NID = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQuery);

                preparedStatement.setString(1, "Rejected");
                preparedStatement.setString(2, nid);

                // Execute the update statement
                int rowsUpdated = preparedStatement.executeUpdate();

                // Close the PreparedStatement object and the database connection
                preparedStatement.close();
                conn.close();

                // Print the number of rows updated to the console
                System.out.println(rowsUpdated + " rows updated");

            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "jdbc:mysql://127.0.0.1/wayout";
                String username = "root";
                String password = "";

                try {
                    Connection conn = DriverManager.getConnection(url, username, password);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM guides_list");

                    while (rs.next()) {
                        String name = rs.getString("Name");
                        String city = rs.getString("City");
                        String email = rs.getString("Email");
                        String mobile = rs.getString("Mobile");
                        String details = rs.getString("Details");
                        String nid = rs.getString("NID");
                        String hourlyCharge = String.valueOf(rs.getDouble("Hourly_Charge"));
                        String availableTime = rs.getString("Available_time");
                        String Status = rs.getString("Status");
                        InputStream image = rs.getBinaryStream("image");


                        Image img = new Image(image);


                        if(Status.toLowerCase().equals("pending")){
                            Platform.runLater(()->{
                                add_guide(name,city,email,mobile,details,nid,hourlyCharge,availableTime,img);
//                                add_guide(name,city,email,mobile,details,nid,hourlyCharge,availableTime,img);
//                                add_guide(name,city,email,mobile,details,nid,hourlyCharge,availableTime,img);
                            });
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
