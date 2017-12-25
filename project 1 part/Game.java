
import javafx.application.Application;
import javafx.scene.Scene;
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

    public void start(Stage stage) {
        StackPane main = new StackPane();
        BorderPane p = new BorderPane();
        Pane pane = new Pane();
        pane.setOnKeyPressed(e -> {
            switch( e.getCode() ) {
                case UP : moveUp(); break;
                case DOWN : moveDown(); break;
                case RIGHT : moveRight(); break;
                case LEFT : moveLeft(); break;
            }
            pane.getChildren().clear();
            main.getChildren().clear();
            p.getChildren().clear();
            pane.getChildren().add(player.getCircle());
            main.getChildren().addAll(map.Mapp,pane);
            p.setTop( new Label("Your Total Score:" + food.points ) );
            p.setBottom(main);
        } );
        pane.getChildren().add(player.getCircle());
        main.getChildren().addAll(map.Mapp,pane);
        p.setTop(new Label("Your Total Score:" + food.points ) );
        p.setBottom(main);
        Scene scene = new Scene(p,map.getUnit()*map.getSize(),map.getUnit()*map.getSize()+30);
        stage.setScene(scene);
        pane.requestFocus();
        stage.setTitle("MyPackMan");
        stage.show();
    }
    public void moveUp()    {
         player.moveUp();
    }
    public void moveDown()  {
         player.moveDown();
    }
    public void moveLeft()  {
         player.moveLeft();
    }
    public void moveRight() {
         player.moveRight();
    }
}

