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
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class RescomController {
    @FXML
    private Label Resnamee;

    @FXML
    private Label avelable;

    @FXML
    private VBox commentmenu;

    @FXML
    private ImageView ov1;

    @FXML
    private ImageView ov2;

    @FXML
    private ImageView ov3;

    @FXML
    private ImageView ov4;
    int sumRate=0;
    @FXML
    private ImageView ov5;
    boolean hasreply=false;
    @FXML
    public void initialize() throws IOException {
        System.out.println("sdfadsf");
        sumRate=0;
        Resnamee.setText(Restaurant.currentRestaurant.name);
        ArrayList<Comment> comentz = FoodHub.Base.Main.sql.getComment(Restaurant.currentRestaurant.id, "restaurantId",false);




        for(Comment com: comentz) {
            hasreply = false;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("commentRowR.fxml"));
            VBox row = fxmlLoader.load();
            CommentRowR control = fxmlLoader.getController();
            control.setData( com.user.username, com.rate, com.addingTime.getTime(), com.Comment, com.id);
            sumRate += com.rate;
            if (User.currentUser.username.equals(com.user.username)) {
                control.serEditvisible();
            }

            ArrayList<Comment> reply = FoodHub.Base.Main.sql.getComment(com.id, "replyId", false);

            if (reply.size() != 0) {
                hasreply = true;
                FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("replyRowR.fxml"));
                VBox row2 = fxmlLoader2.load();
                ReplyrowR control2 = fxmlLoader2.getController();
                control2.setData(reply.get(0).Comment, reply.get(0).addingTime.getTime(), reply.get(0).id);
                commentmenu.getChildren().addAll(row, row2);
                control2.setdelunvisible();
            }


            if (!hasreply) commentmenu.getChildren().add(row);

        }
            int average= sumRate/(comentz.size());
        double aver=(double)(sumRate)/(comentz.size());


        avelable.setText(String.format("%.2f",aver));
        if(average<1.5 && average>=0.5)
        {
            ov1.setOpacity(1);
        } else if (average<2.5 && average>=1.5) {
            ov1.setOpacity(1);
            ov2.setOpacity(1);
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


    @FXML
    void addcom(ActionEvent event) throws IOException {
        RescomController2.show();
    }

    @FXML
    void backkk(ActionEvent event) {
        System.out.println("back");
    }

    public static void show() throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("rescomMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.primaryWidth, Main.primaryHeight);
        Main.primaryStage.setTitle("Hello!");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
}
