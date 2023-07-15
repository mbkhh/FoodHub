package FoodHub;

import FoodHub.Base.*;
import FoodHub.Control.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static Stage primaryStage;
    public static String scene;
    public static final int primaryWidth = 1000;
    public static final int primaryHeight = 700;
    public static final int secondaryWidth = 350;
    public static final int secondaryHeight = 350;

    public static Traffic traffic;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
        Main.sql=new Sql();
//        traffic = new Traffic();
//        traffic.start();
//        RestaurantPanel.show();
////        OwnerPanel.show();
//        EditRestaurant.show();
//        RestaurantOwnerPanel.show();
        traffic = new Traffic();
        traffic.start();
        Offer offer = new Offer();
        offer.start();
        User.currentUser = User.getUserById(1);
//        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));
        Panel.show();
//        ServiceOrder.show();
//        RestaurantOrder.show(1);
       // OwnerPanel.show();
//        Restaurants.show("shit");
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            traffic.isRunning = false;
            offer.isRunning = false;
        });
    }

    public static void main(String[] args) {
        launch();
    }
}