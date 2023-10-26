public class Player {
    public int y , x, lane;
    public Player(){
        y=500;
        x=300;
        lane = 2;
    }
    public void jump(){

    }
    public void roll(){

    }
    public void shiftLeft(){
        if(x > 200){
            x-=100;
            lane--;
        }
    }
    public void shiftRight(){
        if(x < 400){
            x+=100;
            lane++;
        }
    }
}
