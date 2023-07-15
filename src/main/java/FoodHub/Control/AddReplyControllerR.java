package FoodHub.Control;

import FoodHub.Base.Comment;
import FoodHub.Base.Restaurant;
import FoodHub.Base.User;
import FoodHub.Main;
import FoodHub.MainApplication;
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

public class AddReplyControllerR {
    @FXML
    private TextArea Toreply;

    @FXML
    private Label commentofcustomerLa;

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
    static String us,co;
    static long time;

    @FXML
    public void initialize()
    {
        DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(time);
        timeLa.setText(date.toString());
        timeLa.setText(Long.toString(time));
        usernameLa.setText(us);

        commentofcustomerLa.setText(co);
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
        String x = Toreply.getText();
        Comment.addComment(Restaurant.currentRestaurant.owner.id,0,0, comid,0,x,new Date().getTime());
        CoResownerRController.show();
    }

    @FXML
    void backbuttton(ActionEvent event) throws IOException {
        CoResownerRController.show();
    }
    public static void show(String uss,int ratingg,long timee,String coo,int comidd) throws IOException
    {

        us=uss;
        rating=ratingg;
        time=timee;
        co=coo;
        comid=comidd;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addreplyR.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.secondaryWidth, MainApplication.secondaryHeight);
        MainApplication.primaryStage.setTitle("Comment Box!");
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }
}
