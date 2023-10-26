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
            if (obstacles.get(obstacles.size()-1).lane == user.lane) {
                System.out.println(1);
                if(obstacles.get(obstacles.size()-1).y+60 >= user.y-25) {
                    System.out.println(2);
                    gameOver = true;
                }
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
        int x = 0;
        if(lane == 1){
            x = 150;
        }
        else if(lane == 2){
            x = 250;
        }
        else if(lane == 3){
            x = 350;
        }
        return new Obstacle(x, lane);
    }

    public void generateItem(){

    }
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}

