package FoodHub.Control;

import javafx.scene.control.Label;

import java.io.IOException;

public class ServiceOrderRow {
    public Label idLBL,usernameLBL,postCostLBL,addTimeLBL,statusLBL;
    int orderId;

    public void show() throws IOException {
        System.out.println("dsd");
        ServiceSingleOrder.show(orderId);
    }
    public void setData(int id, String addTime , String username, String postCost, String status)
    {
        idLBL.setText(id+"");
        usernameLBL.setText(username);
        postCostLBL.setText(postCost);
        addTimeLBL.setText(addTime);
        statusLBL.setText(status);
        orderId = id;
    }
}
