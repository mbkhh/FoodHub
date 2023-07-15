module com.example.foodhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    exports FoodHub;
    exports FoodHub.Control;
    opens FoodHub to javafx.fxml;
    opens FoodHub.Control to javafx.fxml;
}