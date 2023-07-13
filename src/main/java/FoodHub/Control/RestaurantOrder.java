package FoodHub.Control;

import FoodHub.Base.Cart;
import FoodHub.Base.Order;
import FoodHub.Base.User;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RestaurantOrder {
    @FXML
    public VBox OrderList;
    public static RestaurantOrder orderController;
    int restaurantId;
    public static void show(int restaurantId) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("restaurantOrder.fxml"));
        RestaurantOrder or = new RestaurantOrder();
        or.restaurantId = restaurantId;
        fxmlLoader.setController(or);
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.primaryWidth, MainApplication.primaryHeight);
        MainApplication.primaryStage.setTitle("Orders");
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }
    @FXML
    public void initialize() throws IOException
    {
        orderController = this;
        ArrayList<Order> te = Main.sql.getRestaurantAllOrder(restaurantId);
        for(Order order: te)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("restaurantOrderRow.fxml"));
            HBox row = fxmlLoader.load();
            row.setId("order_"+order.id);
            RestaurantOrderRow control = fxmlLoader.getController();
            DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date(order.addTime);
            control.setData(order.id,f.format(date),order.user.username,order.totalprice+"",order.status.toString());
            OrderList.getChildren().add(row);
        }
    }

    public void back() throws IOException
    {
        Panel.show();
    }
}
