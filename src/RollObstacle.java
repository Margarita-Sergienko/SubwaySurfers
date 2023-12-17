import processing.core.PApplet;
import processing.core.PImage;

public class RollObstacle extends MovingObject {
    private PImage rollObstacleImage;
    public RollObstacle(int x, int lane){
        super(x, lane);
    }
    public void draw(PApplet window){
        rollObstacleImage = window.loadImage("images/rollObstacle.png");
        window.image(rollObstacleImage, x, y);

    }
}
