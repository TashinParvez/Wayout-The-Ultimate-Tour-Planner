module wayout.files {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires com.jfoenix;
    requires java.sql;


    opens wayout.files to javafx.fxml;
    exports wayout.files;

    opens wayout.files.LoginPage to javafx.fxml;
    exports wayout.files.LoginPage;


}