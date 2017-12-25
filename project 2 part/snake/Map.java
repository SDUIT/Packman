//package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Zoir on 4-May-17.
 */
public class Map  {
    int unit=100;
    int size;
    int map[][] = new int[100][100];
    Position start;
    StackPane Mapp = new StackPane();

    public Map(String x) {
         fill(x);
         DrowMap();
    }
    public void fill(String x) {
        try {
            int row = -1, column = -1;
            Scanner input = new Scanner( new File(x) );
            while(input.hasNext()) {
                int num = input.nextInt();
                if(row == -1 && column == -1) {
                    size = num;
                    row=0;
                }
                else {
                    column++;
                    if(column == size ) {
                        row++;
                        column=0;
                    }
                    if( num == 2 ) {
                        start = new Position(column, row);
                    }
                    else map[column][row] = num;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void DrowMap() {
           GridPane pane = new GridPane();
           Pane pn = new Pane();
           for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (map[i][j] == 1) {
                        Rectangle rac= new Rectangle(unit,unit);	
						rac.setFill(Color.BLUE);
						pane.add(rac,i,j);
                    }
					else {
					    Rectangle rac= new Rectangle(unit,unit);	
						rac.setFill(Color.GREY);
						pane.add(rac,i,j);
					}
                }
           }
           for( int i=0; i<=size*unit; i+=unit) {
               Line line = new Line(0, i, size * unit, i);
               Line line2 = new Line(i, 0, i, size * unit);
               pn.getChildren().addAll(line, line2);
           }
           Mapp.getChildren().addAll(pane,pn);
    }

    public int getUnit(){return unit;}
    public int getSize() {
        return size;
    }
    public int[][] getMap() {
        return map;
    }
    public Position getStartPosition() {
        return start;
    }
}
