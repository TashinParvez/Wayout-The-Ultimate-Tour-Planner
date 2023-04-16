package wayout.files.Dashboard;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import wayout.files.LoginPage.LoginController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.stream.Collectors;

public class UserDashboardController implements Initializable {
    @FXML
    private HBox card_must_go_Lists;

    @FXML
    private Label cart;

    @FXML
    private Label chat;

    @FXML
    private Label edit_profile;

    @FXML
    private Label guides;

    @FXML
    private Label home;

    @FXML
    private Label hotel;

    @FXML
    private Label logout;

    @FXML
    private MFXScrollPane main_scrollPane;

    @FXML
    private Label maps;

    @FXML
    private HBox popular_hotels;

    @FXML
    private Circle profile_Picture;

    @FXML
    private HBox recently_viewed_places;

    @FXML
    private Label rewards;

    @FXML
    private MFXTextField searchBox;

    @FXML
    private MFXButton searchBtn;

    @FXML
    private MFXButton see_best_hotel_listBtn;

    @FXML
    private Label transport;

    @FXML
    private Label trips;

    @FXML
    private Label userLoc;

    @FXML
    private Label username;

    @FXML
    private HBox ways_to_tour_Dhaka_city;

    @FXML
    private Label weatherLbl;

    private Vector<Node>nodesVector=new Vector<>();
    private Stage stage;
    private Parent root;
    private Scene scene;
//    @FXML
    private ListView<String> suggestionListView=new ListView<>();

    private List<String> suggestions = new ArrayList<>();

    private Popup suggestionPopup = new Popup();



    public void sideBarMove(Node node){
        new Thread(new Runnable() {
            @Override
            public void run() {
                node.setStyle("-fx-translate-x: 25px;-fx-text-fill: #00d38b; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 15px;");

            }
        }).start();
    }

    public void changeAllRemaining(Node node){
        new Thread(new Runnable() {
            @Override
            public void run() {


                for(int i=0;i<nodesVector.size();i++){
                    if(!nodesVector.get(i).equals(node)){
                        nodesVector.get(i).setStyle("-fx-text-fill: #ffffff; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 13px;");
                    }else {
                        nodesVector.get(i).setStyle("-fx-translate-x: 25px;-fx-text-fill: #00d38b; -fx-font-family: 'Arial Rounded MT Bold'; -fx-font-size: 15px;");
                    }
                }

            }
        }).start();
    }

    public void addAllSideNodes(){
        nodesVector.add(home);
        nodesVector.add(maps);
        nodesVector.add(trips);
        nodesVector.add(guides);
        nodesVector.add(hotel);
        nodesVector.add(transport);
        nodesVector.add(cart);
        nodesVector.add(chat);
        nodesVector.add(rewards);
        nodesVector.add(edit_profile);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        searchBox.getItems().addAll("Apple","Banana");
//        sideBarMove(home);

        maps.setOnMouseClicked((event)->{
            addAllSideNodes();
            changeAllRemaining(maps);
        });

        home.setOnMouseClicked((event)->{
            addAllSideNodes();
            changeAllRemaining(home);
        });

        trips.setOnMouseClicked((event)->{
            addAllSideNodes();
            changeAllRemaining(trips);
        });

        guides.setOnMouseClicked((event)->{
            addAllSideNodes();
            changeAllRemaining(guides);
        });

        hotel.setOnMouseClicked((event)->{
            addAllSideNodes();
            changeAllRemaining(hotel);
        });

        transport.setOnMouseClicked((event)->{
            addAllSideNodes();
            changeAllRemaining(transport);
        });

        cart.setOnMouseClicked((event)->{
            addAllSideNodes();
            changeAllRemaining(cart);
        });

        chat.setOnMouseClicked((event)->{
            addAllSideNodes();
            changeAllRemaining(chat);
        });

        rewards.setOnMouseClicked((event)->{
            addAllSideNodes();
            changeAllRemaining(rewards);
        });
        edit_profile.setOnMouseClicked((event)->{
            addAllSideNodes();
            changeAllRemaining(edit_profile);
        });

        logout.setOnMouseClicked((event) -> {
            try{
                root= FXMLLoader.load(LoginController.class.getResource("login.fxml"));
                stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Thread Clocation=new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    try {
                        String url = "https://mylocation.org/";
                        Document doc = Jsoup.connect(url).get();
                        Element info = doc.selectFirst(".info");

                        String city = "";
                        String country = "";

                        if (info != null) {
                            Elements rows = info.select("table tr");
                            for (Element row : rows) {
                                String label = row.selectFirst("td").text();
                                String value = row.select("td").get(1).text();
                                if (label.equals("City")) {
                                    city = value;
                                } else if (label.equals("Country")) {
                                    country = value;
                                }
                            }
                            userLoc.setText(city+", "+country);

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
            }
        });



       Thread weather=new Thread(new Runnable() {
           @Override
           public void run() {
               //weather information
               try{
                   String url = "https://www.tomorrow.io/weather/";
                   Document doc = Jsoup.connect(url).get();

                   Elements weatherElements = doc.select("div.fvkAud span._3fQrr5");

                   for (Element weatherElement : weatherElements) {
                       String temperature = weatherElement.text();


                       String[] split=temperature.split("°");
                       int temp=Integer.parseInt(split[0]);

                       Platform.runLater(()->{
                           if(temp>=45){
                               weatherLbl.setText(temperature+"F");
                           }else {
                               weatherLbl.setText(temperature+"C");
                           }
                       });
                   }
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
       });

        Clocation.start();
        weather.start();


    }
}
