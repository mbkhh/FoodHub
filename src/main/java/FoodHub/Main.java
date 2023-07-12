package FoodHub;

import FoodHub.Base.*;
import FoodHub.Control.CartController;
import FoodHub.Control.MapController;
import FoodHub.Control.OrderController;
import FoodHub.Control.Panel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage;
    public static final int primaryWidth = 1000;
    public static final int primaryHeight = 700;
    public static Traffic traffic;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
        FoodHub.Base.Main.sql=new Sql();
        traffic = new Traffic();
        traffic.start();
        User.currentUser = User.getUserById(1);

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            traffic.isRunning = false;
        });
        //CartController.show();
        OrderController.show();
//        MapController.show();
//        MapController.mapController.markPath("1 - 2 - 6 - 7 - 8 - 9 - 11 - ");
//        MapController.mapController.markPath("1 - 3 - 4 - 5 - ");
    }

    public static void main(String[] args) {
        launch();
    }
}