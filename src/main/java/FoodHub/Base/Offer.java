
package FoodHub.Base;

import FoodHub.Control.CartController;
import FoodHub.Control.RestaurantPanel;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Offer extends Thread{

    public boolean isRunning = true;
    int index = 0;
    public void showOffer()
    {
        if (User.currentUser != null && User.currentUser.type == 1)
        {
            HashMap<Integer, ArrayList<Integer>> f =Comment.ratingHistory(User.currentUser.id);
            ArrayList<Restaurant> restaurnat = Main.sql.getRestaurant(0, "0", true, "");
            System.out.println();
            for (int i = 0; i < restaurnat.size(); i++) {
                for (int j = i; j < restaurnat.size()-1; j++) {
                    double res1rate = (f.containsKey(restaurnat.get(j).id))?Functions.avarage(f.get(restaurnat.get(j).id)):2.5;
                    double res2rate = (f.containsKey(restaurnat.get(j+1).id))?Functions.avarage(f.get(restaurnat.get(j+1).id)):2.5;
                    if(res1rate < res2rate)
                        Collections.swap(restaurnat, j+1, j);
                }
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    if(restaurnat.size() <= index)
                        index = 0;
                    alert.setTitle("Offer");
                    alert.setContentText("You may like restaurant " +restaurnat.get(index).name + "\n Do you want to check it out." );
                    //alert.show();
                    Optional<ButtonType> result = alert.showAndWait();
                    if(!result.isPresent())
                        return;
                    else if(result.get() == ButtonType.OK){
                        System.out.println(index);
                        Restaurant.setCurrentRestaurant(restaurnat.get(index).id);
                        try{
                        RestaurantPanel.show();
                            System.out.println("ssdfdffs");}
                        catch (IOException e){
                            e.printStackTrace();
                        }
                        index++;
                    }
                    else if(result.get() == ButtonType.CANCEL)
                        return;
                }
            });

            //MainApplication.showAl();
            System.out.println("Best restaurant for you :)");
            for(Restaurant re : restaurnat){
                System.out.println(re.id + "   " + re.name + "   " + ((f.containsKey(re.id))?Functions.avarage(f.get(re.id)):2.5));
            }
            System.out.println();
        }
    }

    public void run() {
        int x = 0;
        while (true) {
            if (!isRunning)
                break;
            x++;
            if(x == 200)
            {
                showOffer();
                x = 0;
            }
            try {
                TimeUnit.SECONDS.sleep(1); }
            catch (InterruptedException e) {}
        }
    }
}
