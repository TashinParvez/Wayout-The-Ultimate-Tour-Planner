package wayout.files.Dashboard;

import io.github.palexdev.materialfx.controls.MFXButton;
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

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Guide_Manage implements Initializable {


    @FXML
    private AnchorPane main_anchorPane;

    @FXML
    private VBox packages_vbox;

    private void addGuide(Image image, String guideName, String days, String availArea, String guideDetails, String costHour) {




        HBox hBox = new HBox();
        hBox.setPrefHeight(300);
        hBox.setPrefWidth(1050);
//        hBox.setStyle("-fx-background-color: red");

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(1050);
        anchorPane.setPrefHeight(300);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(250);
        imageView.setFitWidth(350);

        imageView.setLayoutX(10);
        imageView.setLayoutY(20);


        Label label = new Label(guideName);
        label.setLayoutX(400);
        label.setLayoutY(20);
        label.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" +
                "-fx-font-size: 20px;" +
                "");


        Label duration = new Label(days);
        duration.setLayoutX(400);
        duration.setLayoutY(55);
        duration.setMaxWidth(85);
//        duration.setWrapText(true);


        duration.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-padding: 5px;" +
                        "-fx-background-color: #0066ff;" +
                        "-fx-text-fill: white" +
                        "");


        Label feat = new Label(availArea);
        feat.setLayoutX(490);
        feat.setLayoutY(55);
        feat.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-padding: 5px;" +
                        "-fx-background-color: #ffc600;" +
                        "-fx-text-fill: black;" +
                        "");


        Label detailS_lbl = new Label(guideDetails);
        detailS_lbl.setLayoutX(400);
        detailS_lbl.setLayoutY(95);
        detailS_lbl.setMaxWidth(500);
//        detailS_lbl.setMaxHeight(150);
        detailS_lbl.setWrapText(true);

        detailS_lbl.setStyle("-fx-font-size: 14px;");

        Label cost_package = new Label(costHour + " TK/Hour");
        cost_package.setLayoutX(400);
        cost_package.setLayoutY(240);
        cost_package.setStyle("-fx-background-color: #00ab71;" +
                "-fx-padding: 5px;" +
                "-fx-font-size: 14px;" +
                "-fx-text-fill: white");


        MFXButton viewDetailsButtn = new MFXButton("View details");
        viewDetailsButtn.setStyle("-fx-font-size: 14px;" +
                "-fx-padding: 5 10 5 10px;" +
                "-fx-text-fill: white;" +
                "-fx-background-color: teal;" +
                "-fx-border-radius: 0px;" +
                "-fx-background-radius: 0px");
        viewDetailsButtn.setLayoutY(240);
        viewDetailsButtn.setLayoutX(505);

//        MFXButton hireNowButton = new MFXButton("Hire now");
//        hireNowButton.setStyle("-fx-font-size: 14px;" +
//                "-fx-padding: 5 10 5 10px;" +
//                "-fx-text-fill: white;" +
//                "-fx-background-color: #004b4b;" +
//                "-fx-border-radius: 0px;" +
//                "-fx-background-radius: 0px");
//        hireNowButton.setLayoutY(240);
//        hireNowButton.setLayoutX(510);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    anchorPane.getChildren().add(imageView);
                    anchorPane.getChildren().add(label);
                    anchorPane.getChildren().add(duration);
                    anchorPane.getChildren().add(feat);
                    anchorPane.getChildren().add(detailS_lbl);
                    anchorPane.getChildren().add(cost_package);
                    // anchorPane.getChildren().add(viewDetailsButtn);
                  //  anchorPane.getChildren().add(hireNowButton);

                    hBox.getChildren().add(anchorPane);
                    packages_vbox.getChildren().add(hBox);
                });
            }
        });
        t.start();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    String url = "jdbc:mysql://127.0.0.1/wayout";
                    String username = "root";
                    String password = "";

                    try {
                        Connection conn = DriverManager.getConnection(url, username, password);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM guides_list");

//                        Package_Name,City,Price,Package_Duration,Package_Description,Image

                        while (rs.next()) {
                            String name = rs.getString("Name");
                            String city = rs.getString("City");
                            String price = rs.getString("Hourly_Charge");
                            String duration = rs.getString("Available_time");
                            String details = rs.getString("Details");
                            InputStream image = rs.getBinaryStream("image");
                            Image img = new Image(image);

                            Platform.runLater(() -> {
                                addGuide(img, name, duration, city, details, price);
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } catch (Exception e) {
                    System.out.println("Error");
                }
            }
        }).start();

    }
}
