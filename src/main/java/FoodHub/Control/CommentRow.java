package FoodHub.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentRow {

    @FXML
    private Label ComLa;

    @FXML
    private Label timeLa;

    @FXML
    private Label usernameLa;
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
    private VBox editVbox;
 int comid;
  int rating;


    public void setData(String Foodname,String username,int rate,long addtime,String comment,int id)
    {
        DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(addtime);
    usernameLa.setText(username);
    timeLa.setText(date.toString());
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

    }
    public void serEditvisible()
    {
     editVbox.setVisible(true);
    }

    @FXML
    void editPress(ActionEvent event) throws IOException {
        System.out.println(comid);
        EditController.show(comid,rating);
    }
}
