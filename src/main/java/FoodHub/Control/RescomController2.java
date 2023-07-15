package FoodHub.Control;

import FoodHub.Base.Comment;
import FoodHub.Base.Food;
import FoodHub.Base.Restaurant;
import FoodHub.Base.User;
import FoodHub.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Date;

public class RescomController2 {
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
        String x =myarea.getText();
        System.out.println(x);
        Comment.addComment(User.currentUser.id,0, Restaurant.currentRestaurant.id,0,score,x,new Date().getTime());
        RescomController.show();
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
        RescomController.show();
    }


    public static void show() throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("customerCommentR.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.secondaryWidth, Main.secondaryHeight);
        Main.primaryStage.setTitle("Comment Box!");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
}
