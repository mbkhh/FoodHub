module com.example.foodhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires com.gluonhq.charm.glisten;
    requires org.controlsfx.controls;
    exports FoodHub;
    exports FoodHub.Control;
    opens FoodHub to javafx.fxml;
    opens FoodHub.Control to javafx.fxml;
}