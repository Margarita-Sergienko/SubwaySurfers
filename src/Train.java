import processing.core.PApplet;
import processing.core.PImage;

public class Train extends MovingObject {
    PImage trainObstacle;
    public Train(int x, int lane){
        super(x, lane);
    }
    public void draw(PApplet window){
        trainObstacle = window.loadImage("images/trainObstacle.png");
        window.image(trainObstacle, x, y);
    }
}
