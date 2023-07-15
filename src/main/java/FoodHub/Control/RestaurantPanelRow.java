package FoodHub.Control;
import FoodHub.Base.Cart;
import FoodHub.Base.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RestaurantPanelRow {
    @FXML
    public Label name, type, price, discount, number, rate;
    public int foodId;
    public void setData(int foodId, String name, String type, int price, int discount, double rate) {
        number.setText("");
        this.foodId = foodId;
        this.name.setText(name);
        this.type.setText(type);
        this.price.setText(String.valueOf(price));
        this.discount.setText((discount > 0) ? String.valueOf(discount) : "");
        this.rate.setText((rate > 0) ? String.valueOf(rate) : "");
    }

    public void comments(ActionEvent event) {

    }
    public void add(ActionEvent event) {
        if (number.getText().equals(""))
            number.setText("1");
        else
            number.setText(String.valueOf(Integer.parseInt(number.getText()) + 1));
        Cart.addToCart(foodId, User.currentUser);
    }
    public void subtract(ActionEvent event) {
        if (!number.getText().equals("")) {
            if (number.getText().equals("1"))
                number.setText("");
            else
                number.setText(String.valueOf(Integer.parseInt(number.getText()) - 1));
            Cart.removeFromCart(foodId, User.currentUser);
        }
    }
}
