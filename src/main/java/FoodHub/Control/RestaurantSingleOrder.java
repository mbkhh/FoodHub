package FoodHub.Control;

import FoodHub.Base.*;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RestaurantSingleOrder {
    public Label mainTitleLBL,orderDiscountLBL,orderTotalLBL,addTimeLBL,discriptionLBL,statusLBL,addressLBL;
    public TextField estimatedTXT;
    public ChoiceBox statusInput;
    public VBox orderVbox;
    public int id;
    public int restaurantId;
    public static RestaurantSingleOrder singleOrderController;
    @FXML
    public void initialize() throws IOException
    {
        System.out.println(id);
        //singleOrderController = this;
        ArrayList<Order> te = Main.sql.getAllOrderById(id );
        restaurantId = te.get(0).restaurant.id;
        mainTitleLBL.setText("Order "+id);
        orderDiscountLBL.setText("Discount: "+te.get(0).totalDiscount);
        orderTotalLBL.setText("Total Price: "+te.get(0).totalprice);
        DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(te.get(0).addTime);
        addTimeLBL.setText("Add Time: "+f.format(date));
        estimatedTXT.setText(""+te.get(0).estimatedTime);
        estimatedTXT.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Functions.isNumeric(newValue))
                Main.sql.editOrder(id,Functions.parseInt(newValue) , te.get(0).status.toString());
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid time!");
                alert.show();
                estimatedTXT.setText(oldValue);
            }
        });

        statusInput.setItems(FXCollections.observableArrayList(OrderStatus.values()));
        statusInput.setValue(te.get(0).status);
        statusInput.setOnAction(e-> Main.sql.editOrder(id,Functions.parseInt(estimatedTXT.getText()) , statusInput.getValue().toString()));


        statusLBL.setText("Status: "+te.get(0).status);
        discriptionLBL.setText("Discription: "+te.get(0).discription);
        addressLBL.setText("Path: "+te.get(0).path);

        ArrayList<Cart> tes = Main.sql.getCart(te.get(0).user.id, id);
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("resaurantOrderSingle.fxml"));
        RestaurantSingleOrder.singleOrderController = new RestaurantSingleOrder();
        RestaurantSingleOrder.singleOrderController.id = id;
        fxmlLoader.setController(RestaurantSingleOrder.singleOrderController);
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.primaryWidth, MainApplication.primaryHeight);
        MainApplication.primaryStage.setTitle("Order "+id);
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }
    public  void showMap() throws IOException
    {
        ArrayList<Order> te = Main.sql.getAllOrderById(id);
        MapController.show("RestaurantSingle",id,te.get(0).path);
        //MapController.mapController.markPath(te.get(0).path);
    }
    public void back() throws IOException
    {
        RestaurantOrder.show(restaurantId);
    }
}
