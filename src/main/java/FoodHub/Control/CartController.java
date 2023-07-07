package FoodHub.Control;

import FoodHub.Base.Cart;
import FoodHub.Base.User;
import FoodHub.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CartController {
    public VBox foodMenu;
    public static void show() throws IOException
    {
         FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cart.fxml"));
         Scene scene = new Scene(fxmlLoader.load(), Main.primaryWidth, Main.primaryHeight);
         Main.primaryStage.setTitle("Hello!");
         Main.primaryStage.setScene(scene);
         Main.primaryStage.show();
    }

    @FXML
    public void initialize() throws IOException
    {
        System.out.println("sdd");
        HBox row2 = FXMLLoader.load(Main.class.getResource("cartRow.fxml"));
        foodMenu.getChildren().add(row2);
        ArrayList<Cart> te = FoodHub.Base.Main.sql.getCart(User.currentUser.id, 0);
        for(Cart cart: te)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cartRow.fxml"));
            HBox row = fxmlLoader.load();
            CartRow control = fxmlLoader.getController();
            control.setData(cart.id,cart.food.name,Integer.toString(cart.food.price),Integer.toString(cart.food.discountPercent*cart.food.price),Integer.toString(cart.count),Integer.toString(cart.count*(cart.food.discountPercent*cart.food.price)));
            foodMenu.getChildren().add(row);
        }
    }
}
