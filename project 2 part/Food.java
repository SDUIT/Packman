//package com.company;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Random;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food {
    private Map map;
    private Pane foodPane;
    private Player player;
    private Circle circle;
    private Position foodPosition;
    private Label seconds;
    private final int timer = 10;
    private int numOfCircles = 20;
    private int time;
    public int points;
    Position FoodPos;
    private int size;
    private int[][] cells;
    MyPlayer Ballplayer;
    Game gm;
    Thread var3;

    public Food(Map var1, Player var2) {
        this.map = var1;
        Ballplayer = new MyPlayer(map) ;
        this.foodPane = new Pane();
        this.map.Mapp.getChildren().add(this.foodPane);
        this.player = var2;
        this.size = this.map.getSize();
        this.cells = this.map.getMap();
        var3 = new Thread(() -> {
            while(this.numOfCircles > 0) {
                this.createFood();
                Platform.runLater(() -> {
                    this.foodPane.getChildren().addAll(new Node[]{this.circle, this.seconds});
                });

                for(this.time = 5; this.time > 0; --this.time) {
                    Platform.runLater(() -> {
                        this.seconds.setText("" + this.time);
                        this.seconds.setTextFill(Color.WHITE);
                    });
                    if(this.player.getPosition().equals(this.foodPosition)) {
                       this.points += this.time;
                       break;
                    }

                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        ;
                    }
                }

                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    ;
                }

                Platform.runLater(() -> {
                    this.foodPane.getChildren().clear();
                });
                --this.numOfCircles;
            }
        });
        var3.start();
    }
    public int getPoints() {
        return this.points;
    }
    public Position getFoodPos(){ return FoodPos; }

    private void createFood() {
        Random var1 = new Random();
        double var4 = (double)this.map.getUnit();

        int var2;
        int var3;
        do {
            do {
                var2 = var1.nextInt(this.size);
                var3 = var1.nextInt(this.size);
            } while(this.player.getPosition().equals(new Position(var2, var3)));
        } while(this.map.getMap()[var2][var3] == 1);

        FoodPos = new Position(var2,var3);
        circle = new Circle(var2*map.getUnit()+map.getUnit()/2,var3*map.getUnit()+map.getUnit()/2,map.getUnit()/2-20);
		circle.setFill(Color.GREEN);
        this.foodPosition = new Position(var2, var3);
        this.seconds = new Label("5");


        this.seconds.setTranslateX((double)var2 * var4);
        this.seconds.setTranslateY((double)var3 * var4);
    }
}
