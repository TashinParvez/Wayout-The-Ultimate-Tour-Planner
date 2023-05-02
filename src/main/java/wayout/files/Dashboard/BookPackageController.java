package wayout.files.Dashboard;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class BookPackageController implements Initializable {

    @FXML
    private TextArea additional_message;

    @FXML
    private VBox booking_vbox;

    @FXML
    private MFXTextField email;

    @FXML
    private MFXTextField full_name;

    @FXML
    private MFXTextField phn_num;

    public void add_order_info_table(String packageName, String duration, Double cost){
        AnchorPane anchorPane=new AnchorPane();
        anchorPane.setPrefWidth(1020);
//        anchorPane.setPrefHeight(200);
        anchorPane.setLayoutY(100);
//        anchorPane.setStyle("-fx-background-color: red;");
        anchorPane.setPadding(new Insets(0,0,50,0));

        Separator separator2=new Separator();
        separator2.setPrefWidth(980);

        separator2.setLayoutX(20);
        separator2.setLayoutY(20);
        separator2.setOrientation(Orientation.HORIZONTAL);

        Label packageT=new Label("Package: ");
        packageT.setLayoutX(20);
        packageT.setLayoutY(30);
        packageT.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: 'Arial Rounded MT Bold'");

        Label packageN=new Label(packageName);
        packageN.setLayoutX(600);
        packageN.setLayoutY(30);
        packageN.setPrefWidth(400);
        packageN.setAlignment(Pos.CENTER_RIGHT);
        packageN.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: 'Arial Rounded MT Bold';");

        Separator separator=new Separator();
        separator.setPrefWidth(980);

        separator.setLayoutX(20);
        separator.setLayoutY(60);
        separator.setOrientation(Orientation.HORIZONTAL);

        //second row

//        Separator separator3=new Separator();
//        separator3.setPrefWidth(980);
//
//        separator3.setLayoutX(20);
//        separator3.setLayoutY(20);
//        separator3.setOrientation(Orientation.HORIZONTAL);

        Label packageT2=new Label("Package duration: ");
        packageT2.setLayoutX(20);
        packageT2.setLayoutY(70);
        packageT2.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: 'Arial Rounded MT Bold'");

        Label packageN2=new Label(duration+" Day/Days");
        packageN2.setLayoutX(600);
        packageN2.setLayoutY(70);
        packageN2.setPrefWidth(400);
        packageN2.setAlignment(Pos.CENTER_RIGHT);
        packageN2.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: 'Arial Rounded MT Bold';");

        Separator separator_1=new Separator();
        separator_1.setPrefWidth(980);

        separator_1.setLayoutX(20);
        separator_1.setLayoutY(100);
        separator_1.setOrientation(Orientation.HORIZONTAL);


        //3rd row
        Label packageT3=new Label("Package cost: ");
        packageT3.setLayoutX(20);
        packageT3.setLayoutY(110);
        packageT3.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: 'Arial Rounded MT Bold'");

        Label packageN3=new Label(cost+" TK (BDT)");
        packageN3.setLayoutX(600);
        packageN3.setLayoutY(110);
        packageN3.setPrefWidth(400);
        packageN3.setAlignment(Pos.CENTER_RIGHT);
        packageN3.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: 'Arial Rounded MT Bold';");

        Separator separator_2=new Separator();
        separator_2.setPrefWidth(980);

        separator_2.setLayoutX(20);
        separator_2.setLayoutY(140);
        separator_2.setOrientation(Orientation.HORIZONTAL);





        anchorPane.getChildren().add(separator_2);
        anchorPane.getChildren().add(packageT3);
        anchorPane.getChildren().add(packageN3);

        anchorPane.getChildren().add(separator_1);
        anchorPane.getChildren().add(packageT2);
        anchorPane.getChildren().add(packageN2);

        anchorPane.getChildren().add(separator);
        anchorPane.getChildren().add(separator2);
        anchorPane.getChildren().add(packageT);
        anchorPane.getChildren().add(packageN);
        booking_vbox.getChildren().add(anchorPane);
    }


    public void payment_method(double cost) throws IOException {
        AnchorPane anchorPane=new AnchorPane();
        anchorPane.setPrefWidth(1020);
        anchorPane.setPrefHeight(400);
        anchorPane.setLayoutY(100);
//        anchorPane.setStyle("-fx-background-color: red");

//        Label bkash=new Label("bKash");
//        bkash.setLayoutX(20);
//        bkash.setLayoutY(10);
//        bkash.setStyle("-fx-font-family: 'Arial Rounded MT Bold';" +
//                "-fx-font-size: 15px");
        MFXRadioButton radioButton=new MFXRadioButton("bKash");
        radioButton.setLayoutY(10);
        radioButton.setLayoutX(20);
        radioButton.setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: 'Arial Rounded MT Bold'");


        ImageView imageView=new ImageView(new Image(getClass().getResource("bkash.png").openStream()));
        imageView.setFitWidth(50);
        imageView.setFitHeight(30);
        imageView.setLayoutY(4);
        imageView.setLayoutX(110);

        double total_cost= cost+(cost*0.0185);

        Text text=new Text("প্রথমে উল্লেখিত নাম্বারে টাকা পাঠিয়ে ফর্মটি পূরণ করুন। Also note that 1.85% bKash \"SEND MONEY\" cost will be added with net price. Total amount you need to send us at "+total_cost+" tk.");
//        text.maxWidth(980);
        TextFlow textFlow=new TextFlow(text);
        textFlow.setStyle("-fx-font-size: 14px;" +
                "-fx-wrap-text: true;" +
                "-fx-max-width: 980");
        textFlow.setLayoutX(20);
        textFlow.setLayoutY(45);

        Separator separator=new Separator();
        separator.setPrefWidth(980);

        separator.setLayoutX(20);
        separator.setLayoutY(100);
        separator.setOrientation(Orientation.HORIZONTAL);

        Label bkshNumber_wayout=new Label("bKash personal number: +8801738439423");
        bkshNumber_wayout.setLayoutX(20);
        bkshNumber_wayout.setLayoutY(110);
        bkshNumber_wayout.setStyle("-fx-font-size: 14px");

        Separator separator2=new Separator();
        separator2.setPrefWidth(980);

        separator2.setLayoutX(20);
        separator2.setLayoutY(140);
        separator2.setOrientation(Orientation.HORIZONTAL);


        Label user_bkash=new Label("Your bKash payment number: ");
        user_bkash.setLayoutX(20);
        user_bkash.setLayoutY(170);
        user_bkash.setStyle("-fx-font-size: 14px");


        MFXTextField bkshNumberofUser=new MFXTextField();
        bkshNumberofUser.setLayoutX(600);
//        bkshNumberofUser.setLayoutX(120);
        bkshNumberofUser.setLayoutY(165);
        bkshNumberofUser.setStyle("-fx-pref-height: 30;" +
                "-fx-pref-width: 300;" +
                "-fx-padding: 0 0 0 10px");
        bkshNumberofUser.setFloatMode(FloatMode.DISABLED);
        bkshNumberofUser.setPromptText("bKash payment number");

        Separator separator3=new Separator();
        separator3.setPrefWidth(980);

        separator3.setLayoutX(20);
        separator3.setLayoutY(225);
        separator3.setOrientation(Orientation.HORIZONTAL);



        //transc
        Label transc=new Label("Your bKash payment Transaction id: ");
        transc.setLayoutX(20);
        transc.setLayoutY(255);
        transc.setStyle("-fx-font-size: 14px");


        MFXTextField paymentTransaction=new MFXTextField();
        paymentTransaction.setLayoutX(600);
//        bkshNumberofUser.setLayoutX(120);
        paymentTransaction.setLayoutY(250);
        paymentTransaction.setStyle("-fx-pref-height: 30;" +
                "-fx-pref-width: 300;" +
                "-fx-padding: 0 0 0 10px");
        paymentTransaction.setFloatMode(FloatMode.DISABLED);
        paymentTransaction.setPromptText("transaction id");


        Separator separator4=new Separator();
        separator4.setPrefWidth(980);

        separator4.setLayoutX(20);
        separator4.setLayoutY(305);
        separator4.setOrientation(Orientation.HORIZONTAL);


        //

        Text text2=new Text("Your personal data will be used to process your order, support your experience throughout this website, and for other purposes described in our privacy policy.");
//        text.maxWidth(980);
        TextFlow textFlow2=new TextFlow(text2);
        textFlow2.setStyle("-fx-font-size: 14px;" +
                "-fx-wrap-text: true;" +
                "-fx-max-width: 980;");
        textFlow2.setLayoutX(20);
        textFlow2.setLayoutY(320);


        MFXCheckbox mfxCheckbox=new MFXCheckbox("I have read and agree to the terms and conditions *");
        mfxCheckbox.setLayoutX(20);
        mfxCheckbox.setStyle("-fx-font-size: 14px");
        mfxCheckbox.setLayoutY(380);


        MFXButton bookNow=new MFXButton("Book Package");
        bookNow.setLayoutY(440);
        bookNow.setLayoutX(800);
        bookNow.setStyle("-fx-font-size: 14px;" +
                "-fx-font-family: 'Arial Rounded MT Bold';" +
                "-fx-padding: 0 10 0 10px;" +
                "-fx-border-color: #0066ff;" +
                "-fx-background-color: #ecbf2a;"+
                "-fx-border-width: 2px");
        bookNow.setPrefHeight(50);
        bookNow.setPrefWidth(150);


        anchorPane.getChildren().add(bookNow);
        anchorPane.getChildren().add(mfxCheckbox);
        anchorPane.getChildren().add(textFlow2);
        anchorPane.getChildren().add(transc);
        anchorPane.getChildren().add(paymentTransaction);
        anchorPane.getChildren().add(separator4);
        anchorPane.getChildren().add(separator3);
        anchorPane.getChildren().add(bkshNumberofUser);
        anchorPane.getChildren().add(user_bkash);
        anchorPane.getChildren().add(separator2);
        anchorPane.getChildren().add(separator);
        anchorPane.getChildren().add(bkshNumber_wayout);
        anchorPane.getChildren().add(textFlow);
        anchorPane.getChildren().add(radioButton);
        anchorPane.getChildren().add(imageView);
        booking_vbox.getChildren().add(anchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        add_order_info_table("Saint Martin couple tour","","");
        try {
            File file=new File("src/main/resources/wayout/files/Dashboard/package_book_info_temp.txt");

            String filePath = "src/main/resources/wayout/files/Dashboard/package_book_info_temp.txt";

            Path path = Paths.get(filePath);
            if(file.exists()){
                BufferedReader br=new BufferedReader(new FileReader(file));
                String contents;

                byte[] fileBytes = Files.readAllBytes(path);
                contents = new String(fileBytes);


                System.out.println(contents);

                String[] split_Contents=contents.split("~");
                String title=split_Contents[0];
                String duration=split_Contents[1];
                double cost=Double.parseDouble(split_Contents[2]);

                add_order_info_table(title,duration,cost);
                payment_method(cost);
            }

//            add_order_info_table("Saint Martin couple tour","4-5 days",4500.0);
//            payment_method(4500.0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
