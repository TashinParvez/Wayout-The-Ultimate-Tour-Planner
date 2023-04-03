package wayout.files.Dashboard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML
    private WebView web;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebEngine webEngine=web.getEngine();
        webEngine.load("https:www.google.com/maps");
    }
}
