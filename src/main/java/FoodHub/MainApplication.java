package FoodHub;

import FoodHub.Base.*;
import FoodHub.Control.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static Stage primaryStage;
    public static String scene;
    public static final int primaryWidth = 1000;
    public static final int primaryHeight = 700;
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
        User.currentUser = User.getUserById(1);
        Restaurant.setCurrentRestaurant(1);
        AddRestaurant.show();
//        RestaurantPanel.show();
////        OwnerPanel.show();
//        EditRestaurant.show();
//        RestaurantOwnerPanel.show();
//        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("RestaurantOwnerPanelRow.fxml"));
//        Scene root = new Scene(loader.load());
//        primaryStage.setScene(root);
//        primaryStage.show();
//        ServiceOrder.show();
//        RestaurantOrder.show(1);
       // OwnerPanel.show();
//        Restaurants.show("shit");
//        primaryStage.setOnCloseRequest(e -> {
//            Platform.exit();
//            traffic.isRunning = false;
//        });
        //CartController.show();
//        OrderController.show();
//        MapController.show();
//        MapController.mapController.markPath("1 - 2 - 6 - 7 - 8 - 9 - 11 - ");
//        MapController.mapController.markPath("1 - 3 - 4 - 5 - ");
    }

    public static void main(String[] args) {
        launch();
    }
}