import processing.core.PApplet;
import processing.core.PImage;

public class Coin extends MovingObject{
    private PImage coinImage;

    public Coin(int x, int lane){
        super(x, lane);
        width = 60;
        height = 60;
    }
    public int bottom(){
        return y+height;
    }

    public int top(){
        return y;
    }

    public void draw(PApplet window){
        coinImage = window.loadImage("images/coin.png");
        window.image(coinImage, x, y);
    }

}
