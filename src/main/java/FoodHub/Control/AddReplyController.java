package FoodHub.Control;

import FoodHub.Base.Comment;
import FoodHub.Base.Food;
import FoodHub.Base.Restaurant;
import FoodHub.Base.User;
import FoodHub.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReplyController {
    @FXML
    private TextArea Toreply;

    @FXML
    private Label commentofcustomerLa;

    @FXML
    private Label foodnameLa;

    @FXML
    private ImageView im1;

    @FXML
    private ImageView im2;

    @FXML
    private ImageView im3;

    @FXML
    private ImageView im4;

    @FXML
    private ImageView im5;

    @FXML
    private Label timeLa;

    @FXML
    private Label usernameLa;
   static int comid;
    static int rating;
    static String fo,us,co;
    static long time;

    @FXML
    public void initialize()
    {
        DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(time);
        timeLa.setText(date.toString());
        usernameLa.setText(us);

        commentofcustomerLa.setText(co);
        foodnameLa.setText(fo);
        if (rating == 1) {
            im1.setOpacity(1);

        } else if (rating == 2){
            im1.setOpacity(1);
            im2.setOpacity(1);

        }else if (rating ==3) {
            im1.setOpacity(1);
            im2.setOpacity(1);
            im3.setOpacity(1);
        } else if (rating ==4) {
            im1.setOpacity(1);
            im2.setOpacity(1);
            im3.setOpacity(1);
            im4.setOpacity(1);
        } else if (rating ==5) {
            im1.setOpacity(1);
            im2.setOpacity(1);
            im3.setOpacity(1);
            im4.setOpacity(1);
            im5.setOpacity(1);
        }

    }

    @FXML
    void Confirmbuttton(ActionEvent event) throws IOException {
        System.out.println("conf");
        String x = Toreply.getText();
        Comment.addComment(User.currentUser.id,0, Restaurant.currentRestaurant.id, comid,0,x,new Date().getTime());
        CoResownerFController.show();
    }

    @FXML
    void backbuttton(ActionEvent event) throws IOException {
        System.out.println("back");
        CoResownerFController.show();
    }

    public static void show(String foo,String uss,int ratingg,long timee,String coo,int comidd) throws IOException
    {
      fo=foo;
      us=uss;
      rating=ratingg;
      time=timee;
      co=coo;
      comid=comidd;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addreply.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.secondaryWidth, Main.secondaryHeight);
        Main.primaryStage.setTitle("Comment Box!");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }

}
