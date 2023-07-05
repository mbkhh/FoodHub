module com.example.foodhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    exports FoodHub;
    opens FoodHub to javafx.fxml;
}