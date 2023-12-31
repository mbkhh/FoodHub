package FoodHub.Base;
import FoodHub.Main;

import java.util.ArrayList;
public class Cart {
    public int id;
    public Food food;
    public User user;
    public Order order;
    public int cost;
    public int count;
    public static ArrayList<Cart> currentCart = new ArrayList<Cart>();
    public Cart(int id, int foodId, int userId, int orderId, int cost, int count) {
        this.id = id;
        food = Food.getFood(foodId);
        user = User.getUserById(userId);
        if(orderId == 0)
            order = null;
        // TODO : add else for when orderId is not zero
        this.cost = cost;
        this.count = count;
    }
    public static boolean removeFromCart(Food food, User user) {
        ArrayList<Cart> te = Main.sql.getCart(food.id, user.id, 0) ;
        if(te.size() != 0) {
            Main.sql.deleteFromCart(te.get(0).id);
        }
        return true;
    }
    public static void removeFromCart(int foodId, User user) {
        Food food = Food.getFood(foodId);
        if(food == null)
            System.out.println("There is no food with id: "+foodId);
        else {
            ArrayList<Cart> te = Main.sql.getCart(food.id, user.id, 0) ;
            if(te.size() != 0) {
                if(te.get(0).count <= 1)
                    Main.sql.deleteFromCart(te.get(0).id);
                else
                    Main.sql.EditCart(te.get(0).id, te.get(0).food.price, te.get(0).count-1);
                System.out.println("Food removed from cart successfully");
            }
            else
                System.out.println("there is some error in deleting food Code:52");
        }
    }
    public static void addToCart(int foodId, User user) {
        Food food = Food.getFood(foodId);
        if(food == null) {
            System.out.println("There is no food with id: "+foodId);
        }
        else {
            ArrayList<Cart> te = Main.sql.getCart(food.id, user.id, 0) ;
            if(te.size() == 0) {
                Main.sql.InsertToCart(food.id, user.id, 0, food.price, 1);
                System.out.println("Food added to cart successfully");
            }
            else if(te.get(0).food.restaurant.id != food.restaurant.id) {
                System.out.println("You can't add this food to your cart because it's impossible to order from two different restaurant with one order.");
            }
            else {
                Main.sql.EditCart(te.get(0).id, te.get(0).food.price, te.get(0).count+1);
                System.out.println("Food added to cart successfully");
            }
        }
    }
    public static void printCart(ArrayList<Cart> cart) {
        String leftAlignFormat = "| %-15s | %-10d | %-5d | %-11d | %-10d |%n";
        String leftAlignHeaderFormat = "| %-15s | %-10s | %-5s | %-11s | %-10s |%n";
        System.out.println("-------------------------------------------------------------------");
        System.out.format(leftAlignHeaderFormat,"   FoodName"," Cost PE","Count","Discount PE","Total Cost");
        System.out.println("-------------------------------------------------------------------");
        for (int i = 0; i < cart.size(); i++) {
            // System.out.println(cart.get(i).food.name + "\t" +  cart.get(i).cost + "\t" +  cart.get(i).count + "\t" +  "0" + "\t" +  cart.get(i).cost*cart.get(i).count);
            int[] prices = cart.get(i).food.getPrice();
            System.out.format(leftAlignFormat, cart.get(i).food.name, prices[0], cart.get(i).count, prices[0] - prices[1], (prices[1] * cart.get(i).count));
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Post cost :" + cart.get(0).food.restaurant.postCost);
    }
    public static void printCart(User user) {
        ArrayList<Cart> te = Main.sql.getCart(user.id, 0);
        if(te.size() == 0)
            System.out.println("Your cart is empty");
        else
            printCart(te);
    }
}