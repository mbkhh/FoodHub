package FoodHub.Control;

import FoodHub.Base.Cart;
import FoodHub.Base.Order;
import FoodHub.Base.User;
import FoodHub.Main;
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

public class OrderController {
    public VBox OrderList;
    public static OrderController orderController;
    public static void show() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("userOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.primaryWidth, Main.primaryHeight);
        Main.primaryStage.setTitle("Orders");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

    @FXML
    public void initialize() throws IOException
    {
        orderController = this;
        ArrayList<Order> te = FoodHub.Base.Main.sql.getAllOrderOfUser(User.currentUser.id);
        for(Order order: te)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("userOrderRow.fxml"));
            HBox row = fxmlLoader.load();
            row.setId("order_"+order.id);
            UserOrderRow control = fxmlLoader.getController();
            DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date(order.addTime);
            control.setData(order.id,f.format(date),order.totalDiscount+"",order.totalprice+"",order.status.toString(),order.estimatedTime+"");
            OrderList.getChildren().add(row);
        }
    }

    public void back() throws IOException
    {
        CartController.show();
    }
}