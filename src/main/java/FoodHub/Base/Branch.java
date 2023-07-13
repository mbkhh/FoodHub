package FoodHub.Base;

import FoodHub.Main;

public class Branch {
    public int id;
    public int node1;
    public int node2;
    public int weight;

    public int capactiy;
    public int toNode1;
    public int toNode2;
    public Branch(int id, int node1, int node2, int weight) {
        this.id = id;
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
        toNode1 = 0;
        toNode2 = 0;
    }
    public void calculateCapacity()
    {
        int a[] = Main.sql.getNodeXY(node1);
        int b[] = Main.sql.getNodeXY(node2);
        capactiy = (int)(Math.sqrt(((a[0]-a[1])*(a[0]-a[1]) + (b[0]-b[1])*(b[0]-b[1])))/10)/weight;
    }
}