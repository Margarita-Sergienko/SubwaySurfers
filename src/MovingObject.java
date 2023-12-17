import processing.core.PApplet;

public class MovingObject {
    protected int x, y, ySpeed, lane, width, height;
    protected boolean reachBottom;

    public MovingObject(int x, int lane){

        this.x = x;
        this.y = 0;
        this.ySpeed = 2;
        reachBottom = false;
        this.lane= lane;
        width = 80;
        height = 80;
    }
    public void move(){
        y+=ySpeed;
        if(y>600){
            reachBottom = true;
        }
    }
    public boolean reachedBottom(){
        return reachBottom;
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
        window.fill(117, 219, 210);
        window.rect(this.x, this.y, width, height);
    }

}
