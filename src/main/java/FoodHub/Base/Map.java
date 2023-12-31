package FoodHub.Base;
import FoodHub.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Map {
    static void InsertMapFromFile() {
        try {
            Main.sql.deleteMap();
            File myObj = new File("graph2.txt");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                int node1 = Functions.parseInt(data.split(" ")[0] , "Map insert");
                int node2 = Functions.parseInt(data.split(" ")[1] , "Map insert");
                int weight = Functions.parseInt(data.split(" ")[2] , "Map insert");
                Main.sql.InsertToMap(node1, node2, weight);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static Vertex findPath(int node1, int node2) {
        ArrayList<Branch> all = Main.sql.getAllBranch();
        //System.out.println("searchText start!");
        ArrayList<Integer> indexed = new ArrayList<Integer>();
        ArrayList<Vertex> list = new ArrayList<Vertex>();
        list.add(new Vertex(node1));

        while (list.get(0).nodeName != node2) {
            //System.out.println("on vertex: "+list.get(0).nodeName + "   " + list.get(0).pathLenght);
            ArrayList<Branch> connected = Main.sql.getConnectedBranch(list.get(0).nodeName);
            //ArrayList<Branch> connected = getConnectedBranches(list.get(0).nodeName, all);
//             for (int i = 0; i < connected.size(); i++) {
//                 System.out.println(connected.get(i).node1 + "    "  + connected.get(i).node2 + "     " + connected.get(i).weight);
//             }
            for (int i = 0; i < connected.size(); i++) {
                if(connected.get(i).node1 == list.get(0).nodeName && !indexed.contains(connected.get(i).node2))
                {
                    int index = searchInList(connected.get(i).node2, list);
                    if(index == -1)
                        list.add(new Vertex(list.get(0),connected.get(i).weight, connected.get(i).node2));
                    else{
                        if(list.get(0).pathLength + connected.get(i).weight < list.get(index).pathLength)
                            list.set(index, new Vertex(list.get(0),connected.get(i).weight, connected.get(i).node2));
                    }

                }
                else if(connected.get(i).node2 == list.get(0).nodeName && !indexed.contains(connected.get(i).node1))
                {
                    int index = searchInList(connected.get(i).node1, list);
                    if(index == -1)
                        list.add(new Vertex(list.get(0),connected.get(i).weight, connected.get(i).node1));
                    else{
                        if(list.get(0).pathLength + connected.get(i).weight < list.get(index).pathLength)
                            list.set(index, new Vertex(list.get(0),connected.get(i).weight, connected.get(i).node1));
                    }
                }
            }
            indexed.add(list.get(0).nodeName);
            list.remove(0);

            for (int i = 0; i < list.size(); i++) {
                for (int j = i; j < list.size()-1; j++) {
                    if( list.get(j+1).pathLength < list.get(j).pathLength)
                    {
                        Collections.swap(list, j+1, j);
                    }
                }
            }
            //Collections.reverse(list);
        }
        //System.out.println("path finded - path detail:");
        //for (int i = 0; i < list.get(0).nodes.length; i++) {
        //    System.out.print(list.get(0).nodes[i] + " ");
        //}
        //System.out.println();
        //System.out.println("path size:"+list.get(0).pathLenght);
        return list.get(0);
    }
    static int searchInList(int node , ArrayList<Vertex> list) {
        for (int i = 0; i < list.size(); i++) {
            if (node == list.get(i).nodeName)
                return i;
        }
        return -1;
    }
    public static void showMap()
    {
        ArrayList<Branch> branches = Main.sql.getAllBranch();
        String leftAlignFormat = "| %-5d | %-5d | %-10d |%n";
        String leftAlignHeaderFormat = "| %-5s | %-5s | %-10s |%n";
        System.out.println("------------------------------");
        System.out.format(leftAlignHeaderFormat,"Node1","Node2","Weight");
        System.out.println("------------------------------");

        for (Branch branch : branches) {
            System.out.format(leftAlignFormat,branch.node1,branch.node2,branch.weight);
        }
        System.out.println("------------------------------");
    }
    static ArrayList<Branch> getConnectedBranches(int node , ArrayList<Branch> all) {
        ArrayList<Branch> ans = new ArrayList<Branch>();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).node1 == node || all.get(i).node2 == node)
                ans.add(all.get(i));
        }
        return ans;
    }
}