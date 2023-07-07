package FoodHub.Control;

import FoodHub.Base.*;
import FoodHub.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CartController {
    public VBox foodMenu;
    public Label priceLBL,discountLBL,postCostLBL,totalLBL;
    public TextField discountCodeTXT;
    public TextArea discriptionTXT;
    public static CartController cartController;
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
        cartController = this;
        System.out.println("sdd");
        int price = 0;
        int discount = 0;
        int totalprice=0;
        int postCost = 0;
        /*for (int i = 0; i <8; i++) {
            HBox row2 = FXMLLoader.load(Main.class.getResource("cartRow.fxml"));
            foodMenu.getChildren().add(row2);
        }*/
        ArrayList<Cart> te = FoodHub.Base.Main.sql.getCart(User.currentUser.id, 0);
        for(Cart cart: te)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cartRow.fxml"));
            HBox row = fxmlLoader.load();
            row.setId("cart_"+cart.id);
            CartRow control = fxmlLoader.getController();
            control.setData(cart.id,cart.food.name,Integer.toString(cart.food.price),Integer.toString(cart.food.discountPercent*cart.food.price),Integer.toString(cart.count),Integer.toString(cart.count*(cart.food.price - cart.food.discountPercent*cart.food.price)));
            foodMenu.getChildren().add(row);
            price +=cart.food.price;
            discount+=cart.food.discountPercent*cart.food.price;
            totalprice+=cart.count*(cart.food.price - cart.food.discountPercent*cart.food.price);
        }
        if (te.size()>0) {
            totalprice += te.get(0).food.restaurant.postCost;
            postCost = te.get(0).food.restaurant.postCost;
        }
        priceLBL.setText("Price : "+price);
        discountLBL.setText("Discount : "+discount);
        postCostLBL.setText("Post Cost : "+postCost);
        totalLBL.setText("Total Price : "+totalprice);
    }
    public void confirm()
    {
        System.out.println("fu");

        ArrayList<Cart> te = FoodHub.Base.Main.sql.getCart(User.currentUser.id, 0) ;
        if(te.size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No food in cart!");
            alert.show();
        }
        else {
            int totalPrice = 0;
            int totalDiscount = 0;
            for (int i = 0; i < te.size(); i++) {
                totalPrice += (te.get(i).food.price* (1-te.get(i).food.discountPercent))* te.get(i).count;
                totalDiscount += te.get(i).food.price* te.get(i).food.discountPercent* te.get(i).count;
            }
            totalPrice += te.get(0).food.restaurant.postCost;
            if(totalPrice > User.currentUser.balance)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You do not have enough credit");
                alert.show();
            }
            else {
                String discription = discriptionTXT.getText();
                String code = discountCodeTXT.getText();
                Address resturantAddress = Address.getAddress(0, te.get(0).food.restaurant.id);
                Address userAddress = Address.getAddress(User.currentUser.id, 0);
                int CodePrice = 0;
                if (!code.isEmpty())
                {
                    ArrayList<DiscountCode>  discountCode = FoodHub.Base.Main.sql.getDiscountCodeOfUser(User.currentUser.id,code);
                    if(discountCode.size() != 0)
                    {
                        CodePrice = totalPrice*discountCode.get(0).percent/100;
                        FoodHub.Base.Main.sql.deleteFromDiscountCode(discountCode.get(0).id);
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid Code!");
                        alert.show();
                        return;
                    }
                }
                System.out.println(System.currentTimeMillis());
                Vertex x = Map.findPath(resturantAddress.node, userAddress.node);
                FoodHub.Base.Main.sql.InsertToOrder(User.currentUser.id, te.get(0).food.restaurant.id, 0, x.getPath(), x.pathLength, x.pathLength*100, System.currentTimeMillis(), totalPrice-CodePrice, totalDiscount+CodePrice, OrderStatus.Registered, discription);
                int lastId = FoodHub.Base.Main.sql.getOrderLastId();
                FoodHub.Base.Main.sql.finalizeCart(User.currentUser.id, lastId);
                User.reductionBalance(totalPrice-CodePrice);
                ArrayList<Order> tes =  FoodHub.Base.Main.sql.getAllOrderOfUser(User.currentUser.id);
                if(tes.size() == 3)
                    FoodHub.Base.Main.sql.InsertToDiscountCode(User.currentUser.id, "3ORDER", 10  );
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Order with id "+lastId + " added successfully.");
                alert.show();
            }
        }
    }
    public void remove(int id)
    {
        FoodHub.Base.Main.sql.deleteFromCart(id);
        reload();
    }
    public void reload()
    {
        System.out.println("fu");
        System.out.println(foodMenu.getChildren());
        for (int i = foodMenu.getChildren().size()-1; i >=0 ; i--) {
            if(!foodMenu.getChildren().get(i).getId().equals("cartTitleRow"))
                foodMenu.getChildren().remove(foodMenu.getChildren().get(i));
        }
        try {initialize();}
        catch (IOException e){System.out.println("Reload cart error : "+e.getMessage());}
    }
}
