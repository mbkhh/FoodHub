package FoodHub.Control;

import FoodHub.Base.Branch;
import FoodHub.Base.Food;
import FoodHub.Base.Functions;
import FoodHub.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    double startX;
    double startY;
    double startHValue;
    double startVValue;
    public static void show(String source, int id) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("map.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.primaryWidth, Main.primaryHeight);
        ((MapController)fxmlLoader.getController()).id = id;
        ((MapController)fxmlLoader.getController()).source = source;
        Main.primaryStage.setTitle("Map");
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
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
        ArrayList<Branch> all = FoodHub.Base.Main.sql.getAllBranch();
        for (Branch b: all)
        {
            int loc1[] = FoodHub.Base.Main.sql.getNodeXY(b.node1);
            int loc2[] = FoodHub.Base.Main.sql.getNodeXY(b.node2);
            Line l = new Line(loc1[0],loc1[1],loc2[0],loc2[1]);
            l.setStyle("-fx-stroke: #aaa;");
            l.setStrokeWidth(6);
            mapPane.getChildren().add(l);
        }
        ArrayList<int[]> te = FoodHub.Base.Main.sql.getAllNodes();
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
            System.out.println(p[i]);
            int loc1[] = FoodHub.Base.Main.sql.getNodeXY(Functions.parseInt(p[i]));
            int loc2[] = FoodHub.Base.Main.sql.getNodeXY(Functions.parseInt(p[i+1]));
            Line l = new Line(loc1[0],loc1[1],loc2[0],loc2[1]);
            l.setStyle("-fx-stroke: #00f;");
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
    }

}
