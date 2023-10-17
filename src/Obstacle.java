public class Obstacle {
    protected int x, y, ySpeed;
    boolean reachthebottom;
    public Obstacle(int x){
        this.x = x;
        this.y = 0;
        this.ySpeed = 2;
        reachthebottom = false;
    }
    protected void move(){
        y+=ySpeed;
        if(y>600){
            reachthebottom = true;
        }
    }
}
