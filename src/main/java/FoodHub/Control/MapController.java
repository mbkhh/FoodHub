package FoodHub.Control;

import FoodHub.Base.Branch;
import FoodHub.Base.Functions;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.util.ArrayList;

public class MapController {
    public Pane mapPane;
    public ScrollPane main;
    String source;
    int id;
    public static MapController mapController;
    boolean isPressed = false;
    String path;
    double startX;
    double startY;
    double startHValue;
    double startVValue;
    public static void show(String source, int id ,String path) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map.fxml"));
        MapController map = new MapController();
        map.id=id;
        map.source = source;
        map.path = path;
        fxmlLoader.setController(map);
        //((MapController)fxmlLoader.getController()).id = id;
        //((MapController)fxmlLoader.getController()).source = source;
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.primaryWidth, MainApplication.primaryHeight);
        MainApplication.primaryStage.setTitle("Map");
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }

    @FXML
    public void initialize()
    {
        mapController = this;
        Circle c1 = new Circle(100,100,50);
        Circle c2 = new Circle(1500,1500,50);
        Line l1 = new Line(c1.getCenterX(),c1.getCenterY(),c2.getCenterX(),c2.getCenterY());
        l1.setStrokeWidth(8);
        //mapPane.getChildren().addAll(c1,c2,l1);
        main.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        main.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        System.out.println(path);
        String[] p = path.split(" - ");
        for (int i = 0 ; i < p.length - 1 ; i++)
        {
            //System.out.println(p[i]);
            int loc1[] = Main.sql.getNodeXY(Functions.parseInt(p[i]));
            int loc2[] = Main.sql.getNodeXY(Functions.parseInt(p[i+1]));
            Line l = new Line(loc1[0],loc1[1],loc2[0],loc2[1]);
            l.setStyle("-fx-stroke: #0f0;");
            l.setStrokeWidth(13);
            mapPane.getChildren().add(l);
        }

        ArrayList<Branch> all = Main.sql.getAllBranch();
        for (Branch b: all)
        {
            int loc1[] = Main.sql.getNodeXY(b.node1);
            int loc2[] = Main.sql.getNodeXY(b.node2);
            Line l = new Line(loc1[0],loc1[1],loc2[0],loc2[1]);
            double percent = (double)(MainApplication.traffic.branches.get(b.id).toNode1 + MainApplication.traffic.branches.get(b.id).toNode2)/ MainApplication.traffic.branches.get(b.id).capactiy;
            //System.out.println(b.node1 + "  " + b.node2 + "   => " +  percent);
            if(percent <= .2)
                l.setStyle("-fx-stroke: #00f;");
            else if (percent >.2 && percent < .6)
                l.setStyle("-fx-stroke: #f80;");
            else
                l.setStyle("-fx-stroke: #f00;");
            //l.setStyle("-fx-stroke: #aaa;");
            l.setStrokeWidth(6);
            mapPane.getChildren().add(l);
        }
        ArrayList<int[]> te = Main.sql.getAllNodes();
        for (int a[]:te)
        {
            Circle c = new Circle(a[1],a[2],25,Color.RED);
            Label l = new Label(a[0]+"");
            l.setLayoutX(a[1]+15);
            l.setLayoutY(a[2]-40);
            l.setStyle("-fx-font-size: 18;");
            mapPane.getChildren().addAll(c,l);
        }
    }
    public void markPath(String path)
    {
        String[] p = path.split(" - ");
        for (int i = 0 ; i < p.length - 1 ; i++)
        {
            //System.out.println(p[i]);
            int loc1[] = Main.sql.getNodeXY(Functions.parseInt(p[i]));
            int loc2[] = Main.sql.getNodeXY(Functions.parseInt(p[i+1]));
            Line l = new Line(loc1[0],loc1[1],loc2[0],loc2[1]);
            l.setStyle("-fx-stroke: #fc03db;");
            l.setStrokeWidth(6);
            mapPane.getChildren().add(l);
        }
        System.out.println(p.length);
    }

    public void mouseClick(MouseEvent event)
    {
//        System.out.println(main.getHvalue() + "    " + main.getHmax());
//        System.out.println(mapPane.getHeight() + "    " + main.getHeight());
        if(event.getButton() == MouseButton.PRIMARY)
        {
            isPressed = true;
            startX = event.getSceneX();
            startY = event.getSceneY();
            startHValue = main.getHvalue();
            startVValue = main.getVvalue();
        }

    }
    public void mouseRelease(MouseEvent event)
    {
        isPressed = false;

    }

    public void mouseMove(MouseEvent event)
    {
        if (isPressed) {
            //System.out.println(event.getX());
            double xChange = event.getSceneX() - startX;
            double yChange = event.getSceneY() - startY;
            //System.out.println(event.getSceneX() + "   "+ startX + "    " +xChange/main.getWidth());
            if(startHValue-(xChange/main.getWidth()) < 1)
                main.setHvalue(startHValue-xChange/main.getWidth());
            else
                main.setHvalue(1);
            if(startVValue-(yChange/main.getHeight()) < 1)
                main.setVvalue(startVValue-yChange/main.getHeight());
            else
                main.setVvalue(1);
        }
    }

    public void back() throws IOException
    {
        if (source.equals("UserSingle"))
        {
            SingleOrderController.show(id);
        }
        else if(source.equals("RestaurantSingle"))
        {
            RestaurantSingleOrder.show(id);
        }
        else if(source.equals("ServiceSingle"))
        {
            ServiceSingleOrder.show(id);
        }
    }

}
