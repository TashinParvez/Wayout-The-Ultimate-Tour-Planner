module wayout.files {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires com.jfoenix;
    requires java.sql;
    requires org.json;
    requires mail;
    requires google.api.client;
    requires com.google.api.client.json.gson;
    requires google.api.services.oauth2.v2.rev157;
    requires java.desktop;
    requires com.google.api.client;
    requires javafx.web;


    opens wayout.files to javafx.fxml;
    exports wayout.files;

    opens wayout.files.LoginPage to javafx.fxml;
    exports wayout.files.LoginPage;

    opens wayout.files.Homepage to javafx.fxml;
    exports wayout.files.Homepage;

    //opens wayout.files.Dashboard to javafx.fxml;
    //exports wayout.files.Dashboard;


}