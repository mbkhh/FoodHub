package FoodHub.Control;

import FoodHub.Base.Comment;
import FoodHub.Base.Food;
import FoodHub.Base.Restaurant;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class CoResownerRController {
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

    @FXML
    private ImageView ov5;
    boolean hasreply=false;
    int sumRate=0;

    @FXML
    public void initialize() throws IOException {
        System.out.println("123");
        sumRate=0;
        ArrayList<Comment> comentz = Main.sql.getComment(0,"foodId",true);
        ArrayList<Comment> comentz2= new ArrayList<>();
        for(Comment com: comentz)
        {
            if(com.restaurant!=null && com.food==null) {
                if (com.restaurant.id== Restaurant.currentRestaurant.id  && com.replyComment==null) {
                    hasreply=false;
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("coResowRowR.fxml"));
                    VBox row = fxmlLoader.load();
                    CoResowRowR control = fxmlLoader.getController();
                    control.setData( com.user.username, com.rate, com.addTime.getTime(), com.Comment, com.id);
                    sumRate += com.rate;
                    comentz2.add(com);
                    control.canreply();
                    ArrayList<Comment> reply = Main.sql.getComment(com.id,"replyId",false);

                    if(reply.size()!=0)
                    {
                        hasreply=true;
                        FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("replyRowR.fxml"));
                        VBox row2 = fxmlLoader2.load();
                        ReplyrowR control2 = fxmlLoader2.getController();
                        control2.setData(reply.get(0).Comment,reply.get(0).addTime.getTime(),reply.get(0).id);
                        commentmenu.getChildren().addAll(row,row2);
                        control.cannotReply();
                    }
                    if(!hasreply)commentmenu.getChildren().add(row);

                }

            }




        }
        int average= sumRate/(comentz2.size());
        double aver=(double)(sumRate)/(comentz2.size());


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
    void backkk(ActionEvent event) throws  IOException{
        RestaurantOwnerPanel.show();
    }
    public static void show() throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("coResownerR.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.primaryWidth, MainApplication.primaryHeight);
        MainApplication.primaryStage.setTitle("Hello!");
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }
}
