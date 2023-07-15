package FoodHub.Control;

import FoodHub.Base.Comment;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class EditControllerR {
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
    int score=1;
    static int comid;
    static int rating;

    @FXML
    public void initialize()
    {
//        System.out.println(CommentRow.rating);
        if(rating==2)
        {
            star2.setOpacity(1);
        } else if (rating==3) {
            star2.setOpacity(1);
            star3.setOpacity(1);
        } else if (rating==4) {
            star2.setOpacity(1);
            star3.setOpacity(1);
            star4.setOpacity(1);
        } else if (rating==5) {
            star2.setOpacity(1);
            star3.setOpacity(1);
            star4.setOpacity(1);
            star5.setOpacity(1);
        }
        score=rating;
        myarea.setText(Comment.getComment(comid).Comment);
    }
    @FXML
    void Ediiiit(ActionEvent event) throws IOException {

        String x =myarea.getText();
        System.out.println(x);
        System.out.println(score);
        Comment.editComment(comid,x,score);
        RescomController.show();
    }
    @FXML
    void backbutton(ActionEvent event) throws IOException {
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
    public static void show(int comID,int Rating) throws IOException
    {

        comid=comID;
        rating=Rating;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("editcuscomR.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.secondaryWidth, MainApplication.secondaryHeight);
        MainApplication.primaryStage.setTitle("Comment Box!");
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }

}
