package wayout.files.Dashboard;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import wayout.files.LoginPage.LoginController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class Admin_Dashboard implements Initializable {
    @FXML
    private Label add_package;

    @FXML
    private Label earnings;

    @FXML
    private Label home;

    @FXML
    private Label inbox;

    @FXML
    private LineChart<?, ?> linearChart;

    @FXML
    private Label logout;

    @FXML
    private Label manage_user;
    private Vector<Node> nodesVector = new Vector<>();
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private AnchorPane mainPanel;


    //  problem chart
    private void setLinearChart() {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("January", 10));
        series.getData().add(new XYChart.Data("February", 14));
        series.getData().add(new XYChart.Data("March", 20));
        series.getData().add(new XYChart.Data("April", 12));
        series.getData().add(new XYChart.Data("May", 12));
        series.getData().add(new XYChart.Data("June", 15));
        series.getData().add(new XYChart.Data("July", 18));
        series.getData().add(new XYChart.Data("August", 20));
        series.getData().add(new XYChart.Data("September", 4));
        series.getData().add(new XYChart.Data("October", 15));
        series.getData().add(new XYChart.Data("November", 10));
        series.getData().add(new XYChart.Data("December", 17));
        linearChart.getData().addAll(series);
    }

//    @FXML
//    private PieChart pieChart;
//
//    private void inPieChart() {
//        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
//                new PieChart.Data("Dhaka", 10),
//                new PieChart.Data("Chattogram", 10),
//                new PieChart.Data("Shylet", 10),
//                new PieChart.Data("Khulna", 10),
//                new PieChart.Data("Rajshahi", 10),
//                new PieChart.Data("Cox's Bazar", 10),
//                new PieChart.Data("Bogura", 10)
//        );
//        pieChart.setData(pieChartData);
//    }


    public void changeAllRemaining(Node node) {
        new Thread(new Runnable() {
            @Override
            public void run() {


                for (int i = 0; i < nodesVector.size(); i++) {
                    if (!nodesVector.get(i).equals(node)) {
                        nodesVector.get(i).setStyle("-fx-text-fill: #ffffff; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 13px;" +
                                "-fx-transition: background-color 1s pointer;" +
                                "-fx-cursor: pointer");
                    } else {
                        nodesVector.get(i).setStyle("-fx-text-fill: #00d38b; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 15px;" +
                                "-fx-transition: background-color 1s pointer;" +
                                "-fx-cursor: pointer");
                    }
                }

            }
        }).start();
    }

    public void addAllSideNodes() {
        nodesVector.add(home);
        nodesVector.add(manage_user);
        nodesVector.add(earnings);
        nodesVector.add(inbox);
        nodesVector.add(add_package);
//        nodesVector.add(transport);
//        nodesVector.add(cart);
//        nodesVector.add(chat);
//        nodesVector.add(rewards);
//        nodesVector.add(edit_profile);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        setLinearChart();
//        inPieChart();



//        home.setOnMouseClicked(event -> {
////            addAllSideNodes();
////            changeAllRemaining(trips);
////
////            try {
////                root = FXMLLoader.load(getClass().getResource("tour_packages.fxml"));
////                mainPanel.getChildren().add(root);
////
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//        });

        home.setStyle("-fx-text-fill: #00d38b; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 15px;" +
                "-fx-transition: background-color 1s pointer;" +
                "-fx-cursor: pointer");


        home.setOnMouseClicked((event) -> {
            addAllSideNodes();
            changeAllRemaining(home);

            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        synchronized (this) {
                            Platform.runLater(() -> {
                                try {
                                    root = FXMLLoader.load(getClass().getResource("admin_dashboard.fxml"));
                                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            });
                        }


                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        manage_user.setOnMouseClicked(event -> {
            addAllSideNodes();
            changeAllRemaining(manage_user);

            try {
                root = FXMLLoader.load(getClass().getResource("BanUser.fxml"));
                mainPanel.getChildren().add(root);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        earnings.setOnMouseClicked(event -> {
            addAllSideNodes();
            changeAllRemaining(earnings);

            try {
                root = FXMLLoader.load(getClass().getResource("Earn.fxml"));
                mainPanel.getChildren().add(root);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        inbox.setOnMouseClicked(event -> {
            addAllSideNodes();
            changeAllRemaining(inbox);

//            try {
//                root = FXMLLoader.load(getClass().getResource("BanUser.fxml"));
//                mainPanel.getChildren().add(root);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        });

        add_package.setOnMouseClicked(event -> {
            addAllSideNodes();
            changeAllRemaining(add_package);
//
//            try {
//                root = FXMLLoader.load(getClass().getResource("BanUser.fxml"));
//                mainPanel.getChildren().add(root);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        });

        logout.setOnMouseClicked((event) -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Platform.runLater(()->{
                        try {
                            root = FXMLLoader.load(LoginController.class.getResource("login.fxml"));
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }).start();
        });

    }
}
