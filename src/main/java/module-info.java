module com.example.foodhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
   // requires org.controlsfx.controls;
    requires controlsfx;
    exports FoodHub;
    exports FoodHub.Control;
    opens FoodHub to javafx.fxml;
    opens FoodHub.Control to javafx.fxml;
}