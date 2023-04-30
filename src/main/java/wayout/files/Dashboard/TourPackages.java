package wayout.files.Dashboard;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class TourPackages implements Initializable {

    @FXML
    private VBox packages_vbox;
    @FXML
    private AnchorPane main_anchorPane;
    private Parent root;

    private void addPackages(Image image, String tourTitle, String days, String features, String details, double cost) {
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


        Label label = new Label(tourTitle);
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


        Label feat = new Label(features);
        feat.setLayoutX(490);
        feat.setLayoutY(55);
        feat.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-padding: 5px;" +
                        "-fx-background-color: #ffc600;" +
                        "-fx-text-fill: black;" +
                        "");


        Label detailS_lbl = new Label(details);
        detailS_lbl.setLayoutX(400);
        detailS_lbl.setLayoutY(95);
        detailS_lbl.setMaxWidth(500);
//        detailS_lbl.setMaxHeight(150);
        detailS_lbl.setWrapText(true);

        detailS_lbl.setStyle("-fx-font-size: 14px;");

        Label cost_package = new Label(cost + " TK/=");
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

        MFXButton bookNowButton = new MFXButton("Book now");
        bookNowButton.setStyle("-fx-font-size: 14px;" +
                "-fx-padding: 5 10 5 10px;" +
                "-fx-text-fill: white;" +
                "-fx-background-color: #004b4b;" +
                "-fx-border-radius: 0px;" +
                "-fx-background-radius: 0px");
        bookNowButton.setLayoutY(240);
        bookNowButton.setLayoutX(615);

        bookNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    Alert alertx=new Alert(Alert.AlertType.WARNING);
                    alertx.setContentText("Please wait for a second!");
                    alertx.show();
                    Thread thread=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);

                                Platform.runLater(()->{

                                    try {
                                        alertx.hide();
                                        root= FXMLLoader.load(getClass().getResource("payment_page.fxml"));
                                        main_anchorPane.getChildren().add(root);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }

                                });
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.setDaemon(true);
                    thread.start();


                    File file=new File("src/main/resources/wayout/files/Dashboard/package_book_info_temp.txt");
                    if(file.exists()){
                        PrintWriter printWriter=new PrintWriter(new FileWriter(file));
                        printWriter.print(tourTitle+"~"+days+"~"+cost);
                        printWriter.close();
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Platform.runLater(() -> {
            anchorPane.getChildren().add(imageView);
            anchorPane.getChildren().add(label);
            anchorPane.getChildren().add(duration);
            anchorPane.getChildren().add(feat);
            anchorPane.getChildren().add(detailS_lbl);
            anchorPane.getChildren().add(cost_package);
            anchorPane.getChildren().add(viewDetailsButtn);
            anchorPane.getChildren().add(bookNowButton);

            hBox.getChildren().add(anchorPane);
            packages_vbox.getChildren().add(hBox);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();



        try {
            Image image = new Image(getClass().getResource("image2.jpg").openStream());
            addPackages(image, "Saint-Martin Couple Tour Package", "14-15 days", "Buisness class bus ticket", "Saint-Martin is the perfect destination " +
                    "for honeymoon couple to spending relax & private times. Our package includes bus tickets, ship tickets, resort booking, " +
                    "foods & others facilities. Tour package depends on avaibility. Confirm your booking ASAP.", 15000.0);


            addPackages(image, "Sajek Valley", "5-7 days", "None", "Sajek Valley is the most attractive place for every tourist. It's like a cloud kingdom above the sky. All seasons are best time to visit the sajek valley.\n" +
                    "We have sajek tour package for family, couple & corporate. We operate all tours by our own tour guide. This package also includes Khagrachori all tourist spot.", 8000);


//            addPackages(image, "Saint-Martin Couple Tour Package", "14-15 days", "Buisness class bus ticket", "Saint-Martin is the perfect destination " +
//                    "for honeymoon couple to spending relax & private times. Our package includes bus tickets, ship tickets, resort booking, " +
//                    "foods & others facilities. Tour package depends on avaibility. Confirm your booking ASAP.", "15000");


//            try {
//                String filePathString = "src/main/resources/wayout/files/Dashboard/tour_package_info.txt";
//                Path filePath = Paths.get(filePathString);
//                File file = new File(filePathString);
//
//                if (file.exists()) {
//                    byte[] fileBytes = Files.readAllBytes(filePath);
//                    String fileContents = new String(fileBytes, StandardCharsets.UTF_8);
////                                    System.out.println("File contents:\n" + fileContents);
//
//
//                    String[] Packages = fileContents.split("~");
//
//                    for (int i = 0; i < Packages.length; i++) {
//                        try {
//                            String[] singlePackageExtract = Packages[i].split("@");
//
//                            String imageURL = singlePackageExtract[0];
//                            System.out.println(imageURL);
//
//                            String title = singlePackageExtract[1];
//                            System.out.println(title);
//
//                            String days = singlePackageExtract[2];
//                            System.out.println(days);
//
//                            String features = singlePackageExtract[3];
//                            System.out.println(features);
//
//                            String details = singlePackageExtract[4];
//                            System.out.println(details);
//
//                            String cost = singlePackageExtract[5];
//                            System.out.println(cost);
//
//                            try{
//                                addPackages(new Image(getClass().getResource(imageURL).openStream()), title, days, features, details, cost);
//                            }catch (Exception e){
//                                System.out.println("Failed to load image");
//                            }
//
//                            System.out.println("Ok");
//                        } catch (Exception e) {
//
//                        }
//                    }
//
//                }
//            } catch (Exception e) {
//
//            }


//            addPackages(new Image(getClass().getResource("caption.jpg").openStream()),"Sajek","4-5","None","ddddd","5000");


//                    addPackages(image, "Saint-Martin Couple Tour Package","14-15 days","Buisness class bus ticket","Saint-Martin is the perfect destination " +
//                            "for honeymoon couple to spending relax & private times. Our package includes bus tickets, ship tickets, resort booking, " +
//                            "foods & others facilities. Tour package depends on avaibility. Confirm your booking ASAP.",15000);
        } catch (Exception e) {
            System.out.println("Error");
        }



    }
}
