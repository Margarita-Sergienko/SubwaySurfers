import processing.core.PApplet;
import processing.core.PImage;

public class Player {
    private int y , x, lane, width, height;
    private String status;
    private PImage playerImage;
    private String runImage, jumpImage, rollImage;
    public Player(PApplet window){
        y=500;
        x=260;
        lane = 2;
        status = "";
        width = 80;
        height = 80;
        runImage = "images/run.png";
        jumpImage = "images/jump.png";
        rollImage = "images/roll.png";
        playerImage = window.loadImage(runImage);
    }
    public void jump(PApplet window){
        playerImage = window.loadImage(jumpImage);
        status = "j";
    }
    public String getStatus(){
        return status;
    }

    public void stopAction(PApplet window){
        playerImage = window.loadImage(runImage);
        status = "";
    }
    public void roll(PApplet window){
        playerImage = window.loadImage(rollImage);
        status = "r";
    }
    public void shiftLeft(){
        if(x > 150){
            x-=100;
            lane--;
        }
    }
    public void shiftRight(){
        if(x+width < 400){
            x+=100;
            lane++;
        }
    }

    public int bottom(){
        return y+height;
    }

    public int top(){
        return y;
    }

    public int lane(){
        return lane;
    }
    public void draw(PApplet window){
        window.image(playerImage, x, y);
    }

}
