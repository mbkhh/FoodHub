package FoodHub.Control;

import FoodHub.Base.User;
import FoodHub.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.w3c.dom.events.Event;

import java.io.IOException;

public class ChargeController {

    @FXML
    private Label myResult;
    @FXML
    private Button Chargeme;
    @FXML
    private TextField howmuch;
    @FXML
    private Button showBalance;
    @FXML
    private Label balancer;


    @FXML
    void Entertext(KeyEvent event) {
            if(event.getCode().equals(KeyCode.ENTER))
            {
                if(howmuch.getText().matches("\\d+")) {

            User.increaseBalance(Integer.parseInt(howmuch.getText()));
                    myResult.setVisible(true);
                    myResult.setText("Operation succeeded");
                    System.out.println("Operation succeeded");
                    howmuch.setVisible(false);

        }
       else {
                    myResult.setVisible(true);
                    myResult.setText("Operation failed");
                    System.out.println("Operation failed");
                    howmuch.setVisible(false);
       }
            }
    }
    @FXML
    void parseMethod(ActionEvent event) {
        if(event.getSource()==Chargeme)
        {
            balancer.setVisible(false);
            howmuch.setVisible(true);
            myResult.setVisible(false);
        }
        else if(event.getSource()==showBalance)
        {
            myResult.setVisible(false);
            howmuch.setVisible(false);
            balancer.setVisible(true);
            balancer.setText(Integer.toString(User.getUserById(1).balance)+"Tomans are available in your account");
        }

    }

    public static void show() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("charge.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.primaryWidth, Main.primaryHeight);
        Main.primaryStage.setTitle("Hello!");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

}
