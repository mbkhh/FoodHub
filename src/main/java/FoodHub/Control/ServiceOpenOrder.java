package FoodHub.Control;

import FoodHub.Base.Address;
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

public class ServiceOpenOrder {
    @FXML
    public VBox OrderList;
    public static ServiceOpenOrder orderController;
    public static void show() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxmls/service/serviceOpenOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.primaryWidth, MainApplication.primaryHeight);
        MainApplication.primaryStage.setTitle("Open Orders");
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }

    @FXML
    public void initialize() throws IOException
    {
        orderController = this;
        ArrayList<Order> te = Main.sql.getFreeOrder();
        for(Order order: te)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxmls/service/serviceOpenOrderRow.fxml"));
            HBox row = fxmlLoader.load();
            row.setId("order_"+order.id);
            ServiceOpenOrderRow control = fxmlLoader.getController();
            Address userAddress = Main.sql.getAddress(order.user.id, 0);
            Address resaurantAddress = Main.sql.getAddress(0, order.restaurant.id);
            control.setData(order.id,order.user.username,userAddress.node+"" , order.restaurant.name , resaurantAddress.node+"",order.restaurant.postCost+"");
            OrderList.getChildren().add(row);
        }
    }

    public void back() throws IOException
    {
        ServiceOrder.show();
    }
}
