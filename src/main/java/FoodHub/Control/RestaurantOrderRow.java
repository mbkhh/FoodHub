package FoodHub.Control;

import javafx.scene.control.Label;

import java.io.IOException;

public class RestaurantOrderRow {
    public Label idLBL,totalLBL,usernameLBL,addTimeLBL,statusLBL;
    int orderId;

    public void show() throws IOException {
        System.out.println("dsd");
        RestaurantSingleOrder.show(orderId);
    }
    public void setData(int id, String addTime , String username, String total, String status)
    {
        idLBL.setText(id+"");
        totalLBL.setText(total);
        usernameLBL.setText(username);
        addTimeLBL.setText(addTime);
        statusLBL.setText(status);
        orderId = id;
    }
}
