package FoodHub.Control;

import FoodHub.Base.Cart;
import FoodHub.Base.Map;
import FoodHub.Base.Order;
import FoodHub.Base.User;
import FoodHub.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SingleOrderController {
    public Label mainTitleLBL,orderDiscountLBL,orderTotalLBL,addTimeLBL,estimatedLBL,discriptionLBL,statusLBL,addressLBL;
    public VBox orderVbox;
    public int id;
    public static SingleOrderController singleOrderController;
    @FXML
    public void initialize() throws IOException
    {
        System.out.println(id);
        //singleOrderController = this;
        ArrayList<Order> te = FoodHub.Base.Main.sql.getAllOrderById(id ,User.currentUser.id);
        mainTitleLBL.setText("Order "+id);
        orderDiscountLBL.setText("Discount: "+te.get(0).totalDiscount);
        orderTotalLBL.setText("Total Price: "+te.get(0).totalprice);
        DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(te.get(0).addTime);
        addTimeLBL.setText("Add Time: "+f.format(date));
        estimatedLBL.setText("Estimated Time: "+te.get(0).estimatedTime);
        statusLBL.setText("Status: "+te.get(0).status);
        discriptionLBL.setText("Discription: "+te.get(0).discription);
        addressLBL.setText("Path: "+te.get(0).path);

        ArrayList<Cart> tes = FoodHub.Base.Main.sql.getCart(User.currentUser.id, id);
        for(Cart cart: tes)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cartRow.fxml"));
            HBox row = fxmlLoader.load();
            row.setId("cart_"+cart.id);
            for (int i = 0; i < row.getChildren().size(); i++)
                if (row.getChildren().get(i).getId().equals("removeBTN"))
                    row.getChildren().remove(row.getChildren().get(i));

            CartRow control = fxmlLoader.getController();
            control.setData(cart.id,cart.food.name,Integer.toString(cart.food.price),Integer.toString(cart.food.discountPercent*cart.food.price),Integer.toString(cart.count),Integer.toString(cart.count*(cart.food.price - cart.food.discountPercent*cart.food.price)));
            orderVbox.getChildren().add(row);
        }
    }

    public static void show(int id) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("order.fxml"));
        SingleOrderController.singleOrderController = new SingleOrderController();
        SingleOrderController.singleOrderController.id = id;
        fxmlLoader.setController(SingleOrderController.singleOrderController);
        Scene scene = new Scene(fxmlLoader.load(), Main.primaryWidth, Main.primaryHeight);
        Main.primaryStage.setTitle("Order "+id);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
    public  void showMap() throws IOException
    {
        ArrayList<Order> te = FoodHub.Base.Main.sql.getAllOrderById(id ,User.currentUser.id);
        MapController.show("UserSingle",id,te.get(0).path);
        //MapController.mapController.markPath(te.get(0).path);
    }
    public void back() throws IOException
    {
        OrderController.show();
    }
}
