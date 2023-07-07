package FoodHub.Control;

import javafx.scene.control.Label;

import java.io.IOException;

public class CartRow {
    public Label nameLBL,costLBL,countLBL,discountLBL,totalLBL;
    int cartId;

    public void remove()
    {
        CartController.cartController.remove(cartId);
    }
    public void setData(int id, String name , String cost, String discount, String count, String total)
    {
        setNameLBL(name);
        setCostLBL(cost);
        setCountLBL(count);
        setDiscountLBL(discount);
        setTotalLBL(total);
        cartId = id;
    }
    public void setNameLBL(String text)
    {
        nameLBL.setText(text);
    }
    public void setCostLBL(String text)
    {
        costLBL.setText(text);
    }
    public void setCountLBL(String text)
    {
        countLBL.setText(text);
    }
    public void setDiscountLBL(String text)
    {
        discountLBL.setText(text);
    }
    public void setTotalLBL(String text)
    {
        totalLBL.setText(text);
    }
}
