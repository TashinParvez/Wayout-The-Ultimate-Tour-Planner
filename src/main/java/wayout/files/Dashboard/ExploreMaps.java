package wayout.files.Dashboard;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

public class ExploreMaps implements Initializable {

    @FXML
    private MFXTextField locationNameBox;

    @FXML
    private Label locationNameSearched;

    @FXML
    private MFXButton searchButton;

    @FXML
    private WebView webpage;

    public static String generateMapLink(String location) throws UnsupportedEncodingException {
        String encodedLocation = URLEncoder.encode(location, "UTF-8");
        String mapLink = "https://www.google.com/maps?q=" + encodedLocation + "&hl=en&theme=dark";
        return mapLink;
    }

    public void writeFile(String search) throws IOException {
        File file = new File("src/main/resources/wayout/files/Dashboard/maps_data.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write(search);
        bw.close();
    }

    public String ReadFile() throws IOException {
        File file = new File("src/main/resources/wayout/files/Dashboard/maps_data.txt");
        String line = "";

        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();

        } else {
            System.out.println("File not found");


        }
        return line;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (ReadFile() != null) {



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String loc=ReadFile();
                            String link=generateMapLink(loc);

                            WebEngine webEngine=webpage.getEngine();
                            webEngine.load(link);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                searchButton.setOnAction((event -> {



                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                WebEngine engine = webpage.getEngine();
                                String address = generateMapLink(locationNameBox.getText());
                                engine.setJavaScriptEnabled(true);
                                Platform.runLater(() -> {
                                    if (address != null) {

                                        engine.executeScript("document.documentElement.lang='en';");
//                                engine.setUserStyleSheetLocation(getClass().getResource("webview-style.css").toString());
                                        locationNameSearched.setText("Search result for: " + locationNameBox.getText().trim());
                                        System.out.println(locationNameBox.getText());
                                        engine.load(address);
                                        locationNameBox.clear();

                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("Location cannot be empty.");
                                        alert.setContentText("Please enter a location to continue!");
                                    }
                                });

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();

                }));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
