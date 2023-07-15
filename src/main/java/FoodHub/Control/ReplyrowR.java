package FoodHub.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyrowR {

    @FXML
    private Label replabel;

    @FXML
    private Label timela2;
    @FXML
    private VBox delVbox;

    int comid;

    public void setData(String comment,long addtime,int commentid) {
        DateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(addtime);
        timela2.setText(date.toString());
        replabel.setText(comment);
        comid=commentid;
    }
    @FXML
    void deletePress(ActionEvent event) throws IOException {
        System.out.println(comid);
        FoodHub.Base.Main.sql.deleteFromComment(comid,"id");
        CoResownerRController.show();
    }
    public void setdelunvisible()
    {
        delVbox.setVisible(false);
    }


}
