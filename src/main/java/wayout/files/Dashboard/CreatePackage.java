package wayout.files.Dashboard;

import com.jfoenix.controls.JFXTextArea;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.UUID;


public class CreatePackage extends Application {
    String sfn;
    String imageURL;
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root=new AnchorPane();
        root.setPrefWidth(1020);
        root.setPrefHeight(750);

        Label label=new Label();
        label.setText("Create Package");
        label.setPrefWidth(1000);
        label.setStyle("-fx-font-size: 18px;" +
                "-fx-font-family: 'Arial Rounded MT Bold';" +
                "");
        label.setAlignment(Pos.CENTER);
        label.setLayoutX(10);
        label.setLayoutY(20);

        MFXTextField title=new MFXTextField();
        title.setPrefHeight(40);
        title.setPrefWidth(300);
        title.setLayoutX(360);
        title.setLayoutY(60);
        title.floatModeProperty().set(FloatMode.DISABLED);
        title.setStyle("-fx-font-size: 13px;" +
                "-fx-padding: 0 0 0 10px");
        title.setPromptText("Package title");


        MFXTextField days=new MFXTextField();
        days.setPrefHeight(40);
        days.setPrefWidth(300);
        days.setLayoutX(360);
        days.setLayoutY(110);
        days.floatModeProperty().set(FloatMode.DISABLED);
        days.setStyle("-fx-font-size: 13px;" +
                "-fx-padding: 0 0 0 10px");
        days.setPromptText("How many days the package is for?");


        MFXTextField features=new MFXTextField();
        features.setPrefHeight(40);
        features.setPrefWidth(300);
        features.setLayoutX(360);
        features.setLayoutY(160);
        features.floatModeProperty().set(FloatMode.DISABLED);
        features.setStyle("-fx-font-size: 13px;" +
                "-fx-padding: 0 0 0 10px");
        features.setPromptText("Iconic features/Uniqueness?");


        MFXTextField details=new MFXTextField();
        details.setPrefHeight(40);
        details.setPrefWidth(300);
        details.setLayoutX(360);
        details.setLayoutY(210);
        details.floatModeProperty().set(FloatMode.DISABLED);
        details.setStyle("-fx-font-size: 13px;" +
                "-fx-padding: 0 0 0 10px");
        details.setPromptText("Short details about the tour location");
        details.setTextLimit(150);


        MFXTextField cost=new MFXTextField();
        cost.setPrefHeight(40);
        cost.setPrefWidth(300);
        cost.setLayoutX(360);
        cost.setLayoutY(260);
        cost.floatModeProperty().set(FloatMode.DISABLED);
        cost.setStyle("-fx-font-size: 13px;" +
                "-fx-padding: 0 0 0 10px");
        cost.setPromptText("Cost of the package");


        MFXButton image=new MFXButton("Choose banner");
        image.setPrefHeight(40);
        image.setPrefWidth(300);
        image.setLayoutX(360);
        image.setLayoutY(310);
        image.setStyle("-fx-font-size: 13px;" +
                "-fx-padding: 0 0 0 10px;" +
                "-fx-background-color: white;" +
                "-fx-border-color: red;" +
                "-fx-text-fill: black;");


        MFXButton submit=new MFXButton("Submit");
        submit.setPrefHeight(40);
        submit.setPrefWidth(300);
        submit.setLayoutX(360);
        submit.setLayoutY(360);
        submit.setStyle("-fx-font-size: 13px;" +
                "-fx-padding: 0 0 0 10px;" +
                "-fx-background-color: #0066ff;" +
                "-fx-text-fill: white;");


        ImageView imageView=new ImageView();
        imageView.setLayoutX(360);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setLayoutY(410);



        root.getChildren().add(label);
        root.getChildren().add(title);
        root.getChildren().add(days);
        root.getChildren().add(features);
        root.getChildren().add(details);
        root.getChildren().add(cost);
        root.getChildren().add(image);
        root.getChildren().add(submit);
        root.getChildren().add(imageView);

        image.setOnAction((event -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select an Image File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                try {
                    // Get the project directory path
                    Path projectDir = Paths.get(System.getProperty("user.dir"));

                    // Get the path to the selected file
                    Path selectedFilePath = selectedFile.toPath();

                    // Get the file name of the selected file
                    String selectedFileName = selectedFile.getName();
                    imageURL= selectedFileName;
                    System.out.println(selectedFileName);

                    // Create a new path for the destination file in the project directory
                    Path destFilePath = projectDir.resolve("src").resolve("main").resolve("resources").resolve("wayout").resolve("files").resolve("Dashboard").resolve(selectedFileName);
                    String selectedFileExtension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));

                    // Create a new name for the image file using UUID
                    String newFileName = UUID.randomUUID().toString() + selectedFileExtension;

                    if (Files.exists(destFilePath)) {
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("This image already exists in the directory");
                        alert.showAndWait();
                    }else {
                        // Copy the selected file to the destination file path
                        Files.copy(selectedFilePath, destFilePath);

                        // Set the image view to display the selected image
                        Image images = new Image(selectedFile.toURI().toString());
                        imageView.setImage(images);
                    }





                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
        image.setTooltip(new Tooltip("Please select an image that does not contains any space in name"));


        submit.setOnAction((event -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        File file = new File("src/main/resources/wayout/files/Dashboard/tour_package_info.txt");


                        System.out.println(imageURL);
                        if (file.exists()){
                            PrintWriter pw=new PrintWriter(new FileWriter(file,true));
                            pw.println(imageURL+"@"+title.getText()+"@"+days.getText()+"@"+features.getText()+"@"+details.getText()+"@"+cost.getText()+"~");
                            System.out.println("OK");
                            pw.close();
                        }else {
                            System.out.println("DNe");
                        }
                    }catch (Exception e){

                    }finally {
                        System.out.println("Done");
                    }


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                Thread.sleep(2000);
                                Platform.exit();
                            }catch (Exception e){

                            }
                        }
                    }).start();
                }
            }).start();
        }));



//        root.getChildren().add(label);

        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
}
