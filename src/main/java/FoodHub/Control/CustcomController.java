package FoodHub.Control;

import FoodHub.Base.Cart;
import FoodHub.Base.Comment;
import FoodHub.Base.Food;
import FoodHub.Base.User;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class CustcomController {

    @FXML
    private VBox commentmenu;
    int score=1;
    int sumRate=0;
    int average;
    double aver;
    @FXML
    private Label Foodnamee;
    @FXML
    private ImageView ov1;

    @FXML
    private ImageView ov2;

    @FXML
    private ImageView ov3;

    @FXML
    private ImageView ov4;

    @FXML
    private ImageView ov5;
    @FXML
    private Label avelable;

boolean hasreply=false;

    @FXML
    public void initialize() throws IOException {
        System.out.println("sdfadsf");
        sumRate=0;
        Foodnamee.setText(Food.currentFood.name);
        ArrayList<Comment> comentz = Main.sql.getComment(Food.currentFood.id,"foodId",false);

        for(Comment com: comentz)
        {
            hasreply=false;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("commentRow.fxml"));
            VBox row = fxmlLoader.load();
            CommentRow control = fxmlLoader.getController();
            control.setData(com.food.name,com.user.username,com.rate,com.addTime.getTime(),com.Comment,com.id);
            sumRate+=com.rate;
            if(User.currentUser.username.equals(com.user.username))
            {
                control.serEditvisible();
            }

            ArrayList<Comment> reply = Main.sql.getComment(com.id,"replyId",false);

            if(reply.size()!=0)
            {
                hasreply=true;
                FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("replyRow.fxml"));
                VBox row2 = fxmlLoader2.load();
                Replyrow control2 = fxmlLoader2.getController();
                control2.setData(reply.get(0).Comment,reply.get(0).addTime.getTime(),reply.get(0).id);
                commentmenu.getChildren().addAll(row,row2);
                control2.setdelunvisible();
            }


            if(!hasreply)commentmenu.getChildren().add(row);


        }

        if(comentz.size()!=0) {
            int average = sumRate / (comentz.size());
            double aver = (double) (sumRate) / (comentz.size());
            System.out.println(aver);
            avelable.setText(String.format("%.2f",aver));
            if(average<1.5 && average>=0.5)
            {
                ov1.setOpacity(1);
            } else if (average<2.5 && average>=1.5) {
                ov1.setOpacity(1);
                ov2.setOpacity(1);
                System.out.println("2star");
            } else if (average<3.5 && average>=2.5) {
                ov1.setOpacity(1);
                ov2.setOpacity(1);
                ov3.setOpacity(1);
            } else if (average<4.5 && average>=3.5) {
                ov1.setOpacity(1);
                ov2.setOpacity(1);
                ov3.setOpacity(1);
                ov4.setOpacity(1);

            } else if (average>=4.5) {
                ov1.setOpacity(1);
                ov2.setOpacity(1);
                ov3.setOpacity(1);
                ov4.setOpacity(1);
                ov5.setOpacity(1);
            }
        }
        else {
            int average = sumRate;
            double aver = (double) (sumRate);
            avelable.setText(String.format("%.2f",aver));
        }




    }

    @FXML
    void addcom(ActionEvent event) throws IOException {
        CustcomController2.show();
    }

    @FXML
    void backkk(ActionEvent event)throws IOException {
        RestaurantPanel.show();
    }


    public static void show() throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("commentMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.primaryWidth, MainApplication.primaryHeight);
        MainApplication.primaryStage.setTitle("Hello!");
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }
}
