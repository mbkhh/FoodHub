module com.example.foodhub {
    requires javafx.controls;
    requires javafx.fxml;
    exports FoodHub;
    opens FoodHub to javafx.fxml;
}