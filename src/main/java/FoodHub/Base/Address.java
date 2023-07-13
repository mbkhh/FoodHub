package FoodHub.Base;

import FoodHub.Main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Address {
    public int id;
    User user;
    public Restaurant restaurant;
    public int node;
    public Address(int id, int userId , int restaurantId, int node) {
        this.id = id;
        if(userId != 0)
            this.user = User.getUserById(userId);
        else
            this.user = null;
        if(restaurantId != 0)
            this.restaurant = Restaurant.getRestaurant(restaurantId);
        else
            this.restaurant =null;
        this.node = node;
    }
    public static Address getAddress(int userId ,int restaurantId) {
        Address ans = null;
        ans = Main.sql.getAddress(userId, restaurantId);
        return ans;
    }
    public static void addAddress(int userId , int restaurantId , int node) {
        Main.sql.InsertToAddress(userId, restaurantId, node);
    }
    public static ArrayList<Restaurant> findNearRestaurant(ArrayList<Restaurant> restaurants, int userId)
    {
        Address userAddress = getAddress(userId , 0);
        int userLoc[] = Main.sql.getNodeXY(userAddress.node);
        ArrayList<Restaurant> ans = new ArrayList<>(restaurants);
        for (int i = 0; i < ans.size(); i++) {
            for (int j = i; j < ans.size() - 1; j++) {
                Address res1 = getAddress(0 , ans.get(j).id);
                Address res2 = getAddress(0 , ans.get(j+1).id);
                int rest1[] = Main.sql.getNodeXY(res1.node);
                int rest2[] = Main.sql.getNodeXY(res2.node);
                double distance1= Math.sqrt(((userLoc[0] - rest1[0])*(userLoc[0] - rest1[0])) + ((userLoc[1] - rest1[1])*(userLoc[1] - rest1[1])));
                double distance2 =  Math.sqrt(((userLoc[0] - rest2[0])*(userLoc[0] - rest2[0])) + ((userLoc[1] - rest2[1])*(userLoc[1] - rest2[1])));
                if( distance1 < distance2)
                {
                    Collections.swap(ans,j,j+1);
                }
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}