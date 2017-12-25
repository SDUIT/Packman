//package com.company;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Zoir on 4-May-17.
 */
public class MyPlayer implements Player {
    static Position position;
    static Map map;
    static Pane ball = new Pane();
    int pos[][]; // its my matrix
    int distance[][] = new int[100][100]; //here i save minimum distance
    boolean used[][] = new boolean[100][100]; //i use it for cheking some postion(did i come or not?)
    
    public MyPlayer( Map n ) { // Creating player getting position of player from map
        this.map = n;
        position = new Position(n.getStartPosition().getX(), n.getStartPosition().getY());
        pos = n.getMap();
        
	}

    public static Pane getCircle() { // put image in new postion of my Pane
        ball.getChildren().clear();
        int xdir = (position.getX()*map.getUnit());
        int ydir = (position.getY()*map.getUnit());
        Circle circle = new Circle(xdir+map.getUnit()/2,ydir+map.getUnit()/2,map.getUnit()/2-5);
        circle.setFill(Color.RED);
        ball.getChildren().add(circle);
        return ball;
    }

    public void moveRight() { // changing postion of player
        if( pos[position.getX()+1][position.getY()] != 1 && position.getX()+1 < map.getSize() ) {   //changing position of player
            if( distance[position.getX()][position.getY()]-1 == distance[position.getX()+1][position.getY()]  ) {
                position.setX(position.getX() + 1);
                Platform.runLater(() -> {
                    ball.getChildren().clear();
                    int xdir = (position.getX() * map.getUnit());
                    int ydir = (position.getY() * map.getUnit());
                    Circle circle = new Circle(xdir+map.getUnit()/2,ydir+map.getUnit()/2,map.getUnit()/2-5);
                    circle.setFill(Color.RED);
                    ball.getChildren().add(circle);
                });
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
    }

    public void moveLeft()  {   // changing postion of player
        if( position.getX() > 0 && pos[position.getX()-1][position.getY()] != 1   ) {   //changing position of player
            if( distance[position.getX()][position.getY()]-1 == distance[position.getX()-1][position.getY()]  ) {
                position.setX(position.getX() - 1);
                Platform.runLater(() -> {
                    ball.getChildren().clear();
                    int xdir = (position.getX() * map.getUnit());
                    int ydir = (position.getY() * map.getUnit());
                    Circle circle = new Circle(xdir+map.getUnit()/2,ydir+map.getUnit()/2,map.getUnit()/2-5);
                    circle.setFill(Color.RED);
                    ball.getChildren().add(circle);
                });
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
    }
    public void moveDown()  {   // changing postion of player
        if( pos[position.getX()][position.getY()+1] != 1 && position.getY()+1 < map.getSize() ) { //changing position of player
            if( distance[position.getX()][position.getY()]-1 == distance[position.getX()][position.getY()+1]  ) {
                position.setY(position.getY() + 1);
                Platform.runLater(() -> {
                    ball.getChildren().clear();
                    int xdir = (position.getX() * map.getUnit());
                    int ydir = (position.getY() * map.getUnit());
                    Circle circle = new Circle(xdir+map.getUnit()/2,ydir+map.getUnit()/2,map.getUnit()/2-5);
                    circle.setFill(Color.RED);
                    ball.getChildren().add(circle);
                });
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
    }
    public void moveUp()    {   // changing postion of player
        if(  position.getY() > 0 && pos[position.getX()][position.getY()-1] != 1  ) {   //changing position of player
            if( distance[position.getX()][position.getY()]-1 == distance[position.getX()][position.getY()-1]  ) {
                position.setY(position.getY() - 1);
                Platform.runLater(() -> {
                    ball.getChildren().clear();
                    int xdir = (position.getX() * map.getUnit());
                    int ydir = (position.getY() * map.getUnit());
                    Circle circle = new Circle(xdir+map.getUnit()/2,ydir+map.getUnit()/2,map.getUnit()/2-5);
                    circle.setFill(Color.RED);
                    ball.getChildren().add(circle);
                });
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
    }
    public Position getPosition() {
        return position;
    } // return position of player

    public int min(int m, int l) {  //my own method for giving minimum of two integer
        if(m > l)  return l; else return m;
    }
    //My recursion for finding minimum way
    public void rec(int x, int y) {
        // here i check can i go left
        if( x-1 >= 0 && distance[x-1][y] > distance[x][y] && pos[x-1][y]!=1 ){
            if(used[x-1][y] == true) {  //if i came here once i check is it better by new way to come
                distance[x-1][y] = min(distance[x][y]+1,distance[x-1][y]);
            }
            else {  //if i didn't came i get note that i came here and i save distance
                used[x-1][y] = true;
                distance[x-1][y] = distance[x][y]+1;
            }
            rec(x-1,y); //new recursion from new place
        }
        // here i check can i go Right
        if(x+1<map.getSize() && distance[x+1][y] > distance[x][y] && pos[x+1][y]!=1 ) {
            if(used[x+1][y]) {  //if i came here once i check is it better by new way to come
                distance[x+1][y] = min(distance[x][y]+1,distance[x+1][y]);
            }
            else {  //if i didn't came i get note that i came here and i save distance
                used[x+1][y] = true;
                distance[x+1][y] = distance[x][y]+1;
            }
            rec(x+1,y); //new recursion from new place
        }
        // here i check can i go Up
        if( y-1 >= 0 && distance[x][y-1] > distance[x][y] && pos[x][y-1]!=1 ) {
            if(used[x][y-1]) {//if i came here once i check is it better by new way to come
                distance[x][y-1] = min(distance[x][y]+1,distance[x][y-1]);
            }
            else {
                used[x][y-1] = true;    //if i didn't came i get note that i came here and i save distance
                distance[x][y-1] = distance[x][y]+1;
            }
            rec(x,y-1); //new recursion from new place
        }
        // here i check can i go Down
        if( y+1<map.getSize() && distance[x][y+1] > distance[x][y] && pos[x][y+1]!=1 ) {
            if(used[x][y+1]) {  //if i came here once i check is it better by new way to come
                distance[x][y+1] = min(distance[x][y]+1,distance[x][y+1]);
            }
            else {  //if i didn't came i get note that i came here and i save distance
                used[x][y+1] = true;
                distance[x][y+1] = distance[x][y]+1;
            }
            rec(x,y+1); //new recursion from new place
        }
  }
    public void Print() {
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                System.out.print(distance[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
  }
    public int[][] getDistance() {
      return distance;
  }
    public void FindShortWay(int x ,int y) {
        used[x][y] = true; // I save that i came this postion
        for(int i=0; i < map.getSize(); i++) {        // Here i put 500,000 to all off distance at the first
            for( int j=0; j < map.getSize(); j++ ) {  // Here i put 500,000 to all off distance at the first
                distance[i][j] = 500000;              // Here i put 500,000 to all off distance at the first
            }
        }
        distance[x][y] = 0; // Here I put 0 in my player postion
        rec(x,y);   // Here i go to my recursion and i will find minimal distance from this point to all other position
  }
}
