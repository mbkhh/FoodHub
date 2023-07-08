package FoodHub.Control;

import javafx.scene.control.Label;

import java.io.IOException;

public class UserOrderRow {
    public Label idLBL,totalLBL,discountLBL,addTimeLBL,statusLBL,estimatedLBL;
    int orderId;

    public void show() throws IOException {
        System.out.println("dsd");
        SingleOrderController.show(orderId);
    }
    public void setData(int id, String addTime , String discount, String total, String status, String estimated)
    {
        idLBL.setText(id+"");
        totalLBL.setText(total);
        discountLBL.setText(discount);
        addTimeLBL.setText(addTime);
        statusLBL.setText(status);
        estimatedLBL.setText(estimated);
        orderId = id;
    }
}
