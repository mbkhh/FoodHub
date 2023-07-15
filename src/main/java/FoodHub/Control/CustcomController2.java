package FoodHub.Control;

import FoodHub.Base.Comment;
import FoodHub.Base.Food;
import FoodHub.Base.User;
import FoodHub.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Date;

public class CustcomController2 {


    @FXML
    private TextArea myarea;

    @FXML
    private ImageView star1;

    @FXML
    private ImageView star2;

    @FXML
    private ImageView star3;

    @FXML
    private ImageView star4;

    @FXML
    private ImageView star5;

    //resources
    int score=1;


    @FXML
    void Confirmbuttton(ActionEvent event) throws IOException {
            System.out.println("yes");
            String x =myarea.getText();
            System.out.println(x);
            Comment.addComment(User.currentUser.id, Food.currentFood.id,0,0,score,x,new Date().getTime());
            CustcomController.show();
    }

    public void ratebyStar1(Event e) {
        score=1;
        star2.setOpacity(0.5);
        star3.setOpacity(0.5);
        star4.setOpacity(0.5);
        star5.setOpacity(0.5);

    }
    public void ratebyStar2(Event e) {
        star2.setOpacity(1);
        score=2;
        star3.setOpacity(0.5);
        star4.setOpacity(0.5);
        star5.setOpacity(0.5);
    }
    public void ratebyStar3(Event e) {
        star2.setOpacity(1);
        star3.setOpacity(1);
        score=3;
        star4.setOpacity(0.5);
        star5.setOpacity(0.5);
    }
    public void ratebyStar4(Event e) {
        star2.setOpacity(1);
        star3.setOpacity(1);
        star4.setOpacity(1);
        score=4;
        star5.setOpacity(0.5);
    }
    public void ratebyStar5(Event e) {
        star2.setOpacity(1);
        star3.setOpacity(1);
        star4.setOpacity(1);
        star5.setOpacity(1);
        score=5;

    }
    @FXML
    void backbuttton(ActionEvent event) throws IOException {
        CustcomController.show();
    }


    public static void show() throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("customerComment.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.secondaryWidth, Main.secondaryHeight);
        Main.primaryStage.setTitle("Comment Box!");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
}
