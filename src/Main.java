import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {
    ArrayList<Obstacle> obstacles;
    Player user;
    int units = 0;
    boolean gameOver;
    public void settings(){
        size(600,600);
    }
    public void setup(){
        obstacles = new ArrayList<>();
        user = new Player();
        gameOver = false;
    }
    public void draw(){
        background(180);
        fill(161, 233, 176);
        rect(150, 0, 300, 600);
        fill(250, 139, 154);
        ellipse(user.x, user.y, 50, 50);
        if(!gameOver) {
            if (units % 150 == 0) {
                obstacles.add(generateObstacle());
            }
            for (int i = 0; i < obstacles.size(); i++) {
                Obstacle currObstacle = obstacles.get(i);
                if (currObstacle.y >= 600) {
                    obstacles.remove(currObstacle);
                } else {
                    fill(117, 219, 210);
                    rect(currObstacle.x + 10, currObstacle.y, 80, 60);
                    currObstacle.move();
                }
            }
            if (obstacles.get(obstacles.size() - 1).y >= user.y + 50 && obstacles.get(obstacles.size() - 1).x == user.x) {
                gameOver = true;
            }


            units++;
        }
    }
    public void keyReleased(){
        if (key == 'w'){
            user.jump();
        }
        if (key == 'a'){
            user.shiftLeft();
        }
        if (key == 's'){
            user.roll();
        }
        if (key == 'd'){
            user.shiftRight();
        }

    }
    public Obstacle generateObstacle(){
        int lane = (int)(Math.random()*3+1);
        int x;
        if(lane == 1){
            x = 150;
        }
        else if(lane == 2){
            x = 250;
        }
        else {
            x = 350;
        }
        return new Obstacle(x);
    }

    public void generateItem(){

    }
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}

