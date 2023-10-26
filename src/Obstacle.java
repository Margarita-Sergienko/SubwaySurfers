public class Obstacle {
    public int x, y, ySpeed, lane;
    public boolean reachthebottom;
    public Obstacle(int x, int lane){
        this.x = x;
        this.y = 0;
        this.ySpeed = 2;
        reachthebottom = false;
        this.lane= lane;
    }
    protected void move(){
        y+=ySpeed;
        if(y>600){
            reachthebottom = true;
        }
    }

}
