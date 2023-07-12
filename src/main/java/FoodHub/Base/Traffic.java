package FoodHub.Base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Traffic extends Thread {
    static int totalCar = 100;
    public HashMap<Integer,Branch> branches;
    public boolean isRunning = true;
    public Traffic()
    {
        ArrayList<Branch> test = Main.sql.getAllBranch();
        branches = new HashMap<>();
        for(Branch te : test){
            te.calculateCapacity();
            System.out.println(te.capactiy);
            branches.put(te.id , te);
        }
        Random random = new Random();
        for (int i = 0; i < totalCar; i++) {
            if(random.nextBoolean())
                branches.get(test.get(random.nextInt(test.size())).id).toNode1++;
            else
                branches.get(test.get(random.nextInt(test.size())).id).toNode2++;
        }
        //printTraffic();
    }
    public void printTraffic()
    {
        branches.forEach((key,val)->{
            System.out.println(key+"  -> toNode1 :"+val.toNode1 + "  toNode2 :"+val.toNode2 + "  total : "+(val.toNode2+val.toNode1) + "   Cap"+ val.capactiy);
        });
    }
    public void run(){
        while (true)
        {
            if(!isRunning)
                break;
            Random random = new Random();
            HashMap<Integer,Branch> temp = new HashMap<>(branches);

            branches.forEach((k,val)->{
                ArrayList<Branch> neighberNode1 = Main.sql.getConnectedBranch(val.node1);
                ArrayList<Branch> neighberNode2 = Main.sql.getConnectedBranch(val.node2);
                for (int i = 0; i < val.toNode1; i++) {
                    boolean ok =false;
                    while (!ok)
                    {
                        int rand = random.nextInt(neighberNode1.size());
                        if (!(neighberNode1.get(rand).node1 == val.node1 && neighberNode1.get(rand).node2 == val.node2))
                        {
                            ok = true;
                            if (neighberNode1.get(rand).node1 == val.node1)
                            {
                                //val.toNode1--;
                                temp.get(val.id).toNode1--;
                                temp.get(neighberNode1.get(rand).id).toNode2++;
                                //neighberNode1.get(rand).toNode2++;
                            }
                            else
                            {
                                temp.get(val.id).toNode1--;
                                temp.get(neighberNode1.get(rand).id).toNode1++;
//                            val.toNode1--;
//                           neighberNode1.get(rand).toNode1++;
                            }
                        }
                    }

                }
                for (int i = 0; i < val.toNode2; i++) {
                    boolean ok =false;
                    while (!ok)
                    {
                        int rand = random.nextInt(neighberNode2.size());
                        if (!(neighberNode2.get(rand).node1 == val.node1 && neighberNode2.get(rand).node2 == val.node2))
                        {
                            ok = true;
                            if (neighberNode2.get(rand).node2 == val.node1)
                            {
                                temp.get(val.id).toNode2--;
                                temp.get(neighberNode2.get(rand).id).toNode2++;
                            }
                            else
                            {
                                temp.get(val.id).toNode2--;
                                temp.get(neighberNode2.get(rand).id).toNode1++;
                            }
                        }
                    }
                }
            });
            branches = temp;
            //printTraffic();
            try {TimeUnit.SECONDS.sleep(1); }
            catch (InterruptedException e) {}
        }

    }
}
