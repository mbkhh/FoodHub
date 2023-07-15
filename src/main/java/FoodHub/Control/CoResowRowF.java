package FoodHub.Control;

import FoodHub.Base.Comment;
import FoodHub.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoResowRowF {
    @FXML
    private Label ComLa;

    @FXML
    private VBox barVbox;

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
    int comid;
    int rating;
    String fo,us,co;
    long time;
    @FXML
    private Button reprep;

    public void setData(String Foodname,String username,int rate,long addtime,String comment,int id)
    {
        DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(addtime);
        timeLa.setText(date.toString());
        foodnameLa.setText(Foodname);
        usernameLa.setText(username);
        ComLa.setText(comment);
        if (rate == 1) {
            im1.setOpacity(1);

        } else if (rate == 2){
            im1.setOpacity(1);
            im2.setOpacity(1);

        }else if (rate==3) {
            im1.setOpacity(1);
            im2.setOpacity(1);
            im3.setOpacity(1);
        } else if (rate==4) {
            im1.setOpacity(1);
            im2.setOpacity(1);
            im3.setOpacity(1);
            im4.setOpacity(1);
        } else if (rate==5) {
            im1.setOpacity(1);
            im2.setOpacity(1);
            im3.setOpacity(1);
            im4.setOpacity(1);
            im5.setOpacity(1);
        }
        comid=id;
        rating=rate;
        us=username;
        fo=Foodname;
        time=addtime;
        co=comment;
    }
    @FXML
    void deletePress(ActionEvent event) throws IOException {

    }

    @FXML
    void replyPress(ActionEvent event) throws IOException {
        System.out.println("rep");
        AddReplyController.show(fo,us,rating,time,co,comid);
    }
    public  void cannotReply()
    {
        reprep.setVisible(false);
    }
    public void canreply()
    {
        reprep.setVisible(true);
    }

    public void serDelRepvisible()
    {
        barVbox.setVisible(true);
    }


}
