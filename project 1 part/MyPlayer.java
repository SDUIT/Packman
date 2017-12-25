
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
    int pos[][];
    static ImageView imageView = new ImageView("5.gif");


    static boolean l = false,r=true,d=false,u=false;

    public MyPlayer( Map n ) {
        this.map = n;
        position = new Position(n.getStartPosition().getX(), n.getStartPosition().getY());
        pos = n.getMap();
    }

    public static Pane getCircle() {
        ball.getChildren().clear();
        int xdir = (position.getX()*map.getUnit());
        int ydir = (position.getY()*map.getUnit());
        imageView.setFitHeight(map.getUnit());
        imageView.setFitWidth(map.getUnit());
        imageView.setX(xdir);
        imageView.setY(ydir);
        ball.getChildren().add( imageView);
        return ball;
    }

    public void moveRight() {
        if(!r) {
            imageView.setRotate(0);
        }
        u=false; l=false; r=true; d=false;
        if( pos[position.getX()+1][position.getY()] != 1 && position.getX()+1 < map.getSize() ) {
            position.setX(position.getX() + 1);
        }
    }

    public void moveLeft()  {
        if(!l) {
             imageView.setRotate(180);
        }
        u=false; l=true; r=false; d=false;
        if( position.getX() > 0 && pos[position.getX()-1][position.getY()] != 1   ) {
            position.setX(position.getX() - 1);
        }
    }
    public void moveDown()  {
        if(!d) {
            imageView.setRotate(90);
        }
        u=false; l=false; r=false; d=true;
        if( pos[position.getX()][position.getY()+1] != 1 && position.getY()+1 < map.getSize() ) {
            position.setY(position.getY() + 1);
        }
    }
    public void moveUp()    {
        if(!u) {
            imageView.setRotate(270);
        }
        u=true; l=false; r=false; d=false;
        if(  position.getY() > 0 && pos[position.getX()][position.getY()-1] != 1  ) {
            position.setY(position.getY() - 1);
        }
    }
    public Position getPosition() {
        return position;
    }
    }
