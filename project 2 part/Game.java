//package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Zoir on 4-May-17.
 */
public class Game extends Application {
    Map map = new Map("Map.txt");
    MyPlayer player = new MyPlayer(map);
    Food food = new Food(map,player);
    StackPane main;
    Pane pane;
    Thread t1;
    int [][]path;
    public void start(Stage stage) {
        main = new StackPane();
        pane = new Pane();
        t1 = new Thread() {
            public void run(){
                while ( food.var3.isAlive() ) {
                    player.FindShortWay(food.getFoodPos().getX(), food.getFoodPos().getY());
					player.moveDown();
                    player.moveRight();
                    player.moveUp();
                    player.moveLeft();
                }
            }
        };
        t1.start();

        pane.getChildren().add(player.getCircle());
        main.getChildren().addAll(map.Mapp,pane);
        Scene scene = new Scene(main,map.getUnit()*map.getSize(),map.getUnit()*map.getSize());
        stage.setScene(scene);
        pane.requestFocus();
        stage.setTitle("MyPackMan");
        stage.show();
    }
}

