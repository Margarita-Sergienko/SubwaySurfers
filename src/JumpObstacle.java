import processing.core.PApplet;
import processing.core.PImage;

public class JumpObstacle extends MovingObject {
    private PImage jumpObstacleImage;
    public JumpObstacle(int x, int lane){
        super(x, lane);
    }
    public void draw(PApplet window){
        jumpObstacleImage = window.loadImage("images/jumpObstacle.png");
        window.image(jumpObstacleImage, x, y);
    }

}
