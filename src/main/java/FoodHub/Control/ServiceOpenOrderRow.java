package FoodHub.Control;

import FoodHub.Base.Order;
import FoodHub.Base.User;
import FoodHub.Main;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public class ServiceOpenOrderRow {
    public Label idLBL,usernameLBL,userAddressLBL,restaurantNameLBL,restaurantAddressLBL,postCostLBL;
    int orderId;

    public void accept() {
        ArrayList<Order> te = Main.sql.getFreeOrderById(orderId);
        Main.sql.editOrder2(orderId, User.currentUser.id, te.get(0).status.toString());
        System.out.println("order accepted");
        // TODO Redirect to single order page
    }
    public void setData(int id, String username , String userAddress, String restaurantName, String restaurantAddress, String postCost)
    {
        idLBL.setText(id+"");
        usernameLBL.setText(username);
        userAddressLBL.setText(userAddress);
        restaurantNameLBL.setText(restaurantName);
        restaurantAddressLBL.setText(restaurantAddress);
        postCostLBL.setText(postCost);
        orderId = id;
    }
}
