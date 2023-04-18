package wayout.files.Dashboard;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ServerController implements Initializable {

    @FXML
    private VBox messageBodyVbox;

    @FXML
    private MFXTextField messageBox;

    @FXML
    private MFXButton sendBtn;
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter writer;
    private Scanner scanner;

    public void sendMessage(String message, Boolean isClient) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isClient) {
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    hBox.setPadding(new Insets(10, 20, 0, 20));

                    Text text = new Text(message);
                    text.setFill(Color.BLACK);

                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setStyle("-fx-background-color: #e8e8e8;" +
                            "-fx-text-fill: White;" +
                            "-fx-padding: 5 10 5 10px;" +
                            "-fx-font-size: 14px;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 20px;" +
                            "-fx-max-width: 400px");

                    textFlow.setPadding(new Insets(10, 10, 0, 10));


                    hBox.getChildren().add(textFlow);

                    Platform.runLater(() -> {
                        messageBodyVbox.getChildren().add(hBox);
                    });
                } else {
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(10, 20, 0, 20));

                    Text text = new Text(message);
                    text.setFill(Color.rgb(255, 255, 255));

                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setStyle("-fx-background-color: #0066ff;" +
                            "-fx-text-fill: White;" +
                            "-fx-padding: 5 10 5 10px;" +
                            "-fx-font-size: 14px;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 15px;" +
                            "-fx-max-width: 400px");

                    textFlow.setPadding(new Insets(10, 10, 0, 10));


                    hBox.getChildren().add(textFlow);

                    Platform.runLater(() -> {
                        messageBodyVbox.getChildren().add(hBox);
                    });
                }

            }
        }).start();
    }

    public void sendButtonClicked(Event e) {
        String message = messageBox.getText().trim();
        System.out.println(message);
        if (!message.isEmpty()) {
            sendMessage(message, false);
            writer.println(message);
            messageBox.clear();
        }
    }


    @FXML
    private VBox userList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            File file = new File("src/main/resources/wayout/files/Dashboard/account_name.txt");
//            BufferedReader br = new BufferedReader(new FileReader(file));
//
//            String filePathString = "src/main/resources/wayout/files/Dashboard/account_name.txt";
//            Path filePath = Paths.get(filePathString);
//
//            byte[] fileBytes = Files.readAllBytes(filePath);
//            String names = new String(fileBytes, StandardCharsets.UTF_8);
//
//            String[] individual_names = names.split("@");
//
//            for (int i=0;i<individual_names.length;i++) {
//
//                if(!individual_names[i].isEmpty()){
//                    System.out.println(individual_names[i]);
//
//                    VBox vBox=new VBox();
//
//                    AnchorPane anchorPane = new AnchorPane();
//                    anchorPane.setPrefHeight(50);
//                    anchorPane.setPrefWidth(230);
////                    anchorPane.setStyle("-fx-background-color: blue");
//
//
//                    Circle circle=new Circle(20,Color.RED);
//                    circle.setCenterX(30);
//                    circle.setCenterY(25);
//
//                    Label users = new Label(individual_names[i]);
//                    users.setLayoutX(60);
//                    users.setLayoutY(15);
//                    users.setStyle("-fx-font-size: 15px;" +
//                            "-fx-font-family: 'Arial Rounded MT Bold'");
//
//                    anchorPane.getChildren().add(circle);
//                    anchorPane.getChildren().add(users);
//                    vBox.getChildren().add(anchorPane);
//                    userList.getChildren().add(vBox);
//
//                    final boolean[] flag = {true};
//
//                    users.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                        @Override
//                        public void handle(MouseEvent event) {
//                            if(flag[0]){
//                                anchorPane.setStyle("-fx-background-color: gray");
//                                flag[0] =false;
//                            }else {
//                                anchorPane.setStyle("");
//                            }
//                            System.out.println("OK");
//                        }
//                    });
//                }
//            }


            if (file.exists()) {
                try {
                    serverSocket = new ServerSocket(22223);
                    socket = serverSocket.accept();
                    System.out.println("Server accepted a connection");

                    // Initialize the writer object
                    writer = new PrintWriter(socket.getOutputStream(), true);

                    // Initialize the scanner object
                    scanner = new Scanner(socket.getInputStream());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                String input = scanner.nextLine();
                                sendMessage(input, true);
                            }
                        }
                    }).start();

                } catch (Exception e) {
                    // Print the exception message to the console
                    System.out.println("Error initializing server: " + e.getMessage());
                }
            }

        } catch (Exception e) {

        }

    }

}
