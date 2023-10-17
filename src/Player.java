public class Player {
    public int y , x;
    public Player(){
        y=500;
        x=300;
    }
    public void jump(){

    }
    public void roll(){

    }
    public void shiftLeft(){
        if(x > 200){
            x-=100;
        }
    }
    public void shiftRight(){
        if(x < 400){
            x+=100;
        }
    }
}
