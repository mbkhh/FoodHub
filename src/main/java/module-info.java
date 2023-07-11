module com.example.foodhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    exports FoodHub;
    exports FoodHub.Control;
    opens FoodHub to javafx.fxml;
    opens FoodHub.Control to javafx.fxml;
}