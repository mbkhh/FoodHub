package FoodHub.Base;
import FoodHub.Main;

import java.util.ArrayList;
import java.util.Date;

public class Food {
    public static Food currentFood = null;
    public int id;
    public Restaurant restaurant;
    public String name ;
    public int price;
    public FoodType foodType;
    public int discountPercent;
    public Date discountTime;
    public boolean isActive;
    public Food (int id, int restaurantId, String name, int price, String foodType, int discountPercent, long discountTime, String isActive) {
        this.id = id;
        this.restaurant = Restaurant.getRestaurant(restaurantId);
        this.name = name;
        this.price = price;
        this.foodType = Functions.stringToEnum(foodType).get(0);
        this.discountPercent = discountPercent;
        this.discountTime = new Date(discountTime);
        this.isActive = isActive.equals("YES");
    }
    public static Food getFood(int id) {
        return Main.sql.getFood(id, "id", false, "").get(0);
    }
    public static void addFood(int restaurantId, String name, int price, String foodType, int discountPercent, long discountTime, String isActive) {
        Main.sql.insertToFood(restaurantId, name, price, foodType, discountPercent, discountTime, isActive);
    }
    public static boolean editFood(int id, String whichPart, String data1, int data2, long data3) {
        Food food = getFood(id);
        if (Order.openOrders(id).size() == 0) {
            if (food != null) {
                switch (whichPart) {
                    case "name" -> food.name = data1;
                    case "price" -> food.price = data2;
                    case "isActive" -> food.isActive = (data1.equals("yes"));
                    case "discount" -> {
                        food.discountPercent = data2;
                        food.discountTime = new Date(data3);
                    }
                }
                Main.sql.editFood(id, food.restaurant.id, food.name, food.price, food.foodType.getFoodType(), food.discountPercent, food.discountTime.getTime(), (food.isActive) ? "yes" : "no");
                currentFood = getFood(id);
                return true;
            }
        }
        return false;
    }
    public static boolean deleteFood(int id) {
        if (getFood(id) != null) {
            Main.sql.deleteFromCartByFoodId(id);
            Main.sql.deleteFromFood(id, "id");
            Main.sql.deleteFromComment(id, "foodId");
        }
        return false;
    }
    public static void printFood(ArrayList<Food> foods, String topic) {
        String leftAlignFormat = "| %-5d | %-12d | %-15s | %-15s | %-15s | %-17s | %-8s |%n";
        String leftAlignHeaderFormat = "| %-5s | %-12s | %-15s | %-15s | %-15s | %-17s | %-8s |%n";
        String dashedLine = "-------------------------------------------------------------------------------------------------------------";
        System.out.println(topic);
        System.out.println(dashedLine);
        System.out.format(leftAlignHeaderFormat," Id","RestaurantId" ,"     Name","  foodType","DiscountPercent", "  DiscountLasts", "isActive");
        System.out.println (dashedLine);
        for (Food food : foods)
            System.out.format(leftAlignFormat, food.id, food.restaurant.id, food.name, food.foodType.getFoodType(), food.discountPercent + "%", Functions.simpleDateFormat.format(food.discountTime), (food.isActive)? "yes" : "no");
        System.out.println(dashedLine);
    }
    public static void printFood(int restaurantId) {
        ArrayList<Food> foods = Main.sql.getFood(restaurantId, "restaurantId", false, "");
        printFood(foods, "This is all restaurant's foods");
    }
    public static boolean setCurrentFood(int id) {
        Food food = getFood(id);
        if (food != null) {
            currentFood = new Food(id, food.restaurant.id, food.name, food.price, food.foodType.getFoodType(), food.discountPercent, food.discountTime.getTime(), (food.isActive) ? "yes" : "no");
            return true;
        }
        return false;
    }
    public int[] getPrice() {
        int[] prices = new int[2];
        prices[0] = price;
        prices[1] = (discountTime.after(new Date())) ? (int) (price * (1 - (double) discountPercent / 100)) : price;
        return prices;
    }
}