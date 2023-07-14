package FoodHub.Control;

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

public class ServiceOrder {
    @FXML
    public VBox OrderList;
    public static ServiceOrder orderController;
    public static void show() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxmls/service/serviceOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.primaryWidth, MainApplication.primaryHeight);
        MainApplication.primaryStage.setTitle("Orders");
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }

    @FXML
    public void initialize() throws IOException
    {
        orderController = this;
        ArrayList<Order> te = Main.sql.getOrderDelivery(User.currentUser.id);
        for(Order order: te)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("fxmls/service/serviceOrderRow.fxml"));
            HBox row = fxmlLoader.load();
            row.setId("order_"+order.id);
            ServiceOrderRow control = fxmlLoader.getController();
            DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date(order.addTime);
            control.setData(order.id,f.format(date),order.user.username , order.restaurant.postCost+"" , order.status.toString());
            OrderList.getChildren().add(row);
        }
    }

    public void back() throws IOException
    {
        ServiceOpenOrder.show();
    }
}
