import processing.core.PApplet;
import processing.core.PImage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends PApplet {
    ArrayList<MovingObject> obstacles;
    Player user;
    int timeIn, jumpTimer, rollTimer, coinCount;
    boolean gameOver, gameStart, leaderboardScreen, saved;
    PImage backgroundRailroad;
    public void settings(){
        size(600,600);
    }
    public void setup(){
        saved = false;
        leaderboardScreen = false;
        obstacles = new ArrayList<>();
        user = new Player(this);
        gameOver = false;
        timeIn = 0;
        jumpTimer = 1000;
        rollTimer = 1000;
        coinCount = 0;
        gameStart = false;
        backgroundRailroad = loadImage("images/railroad.png");
        background(219, 196, 245);
        fill(252, 232, 155);
        rect(150, 150, 300, 75);
        rect(140, 250, 320, 75);
        textSize(50);
        fill(0, 0, 0);
        text("start", 250, 200);
        text("leaderboard", 155, 310);
    }
    public void draw(){
        if(leaderboardScreen){
            leaderBoard();
        }
        if(gameStart){
        if(!gameOver) {
            saved = false;
            background(117, 151, 73);
            for (int i = 1; i <= 3; i++) {
                image(backgroundRailroad, 50 + 100*i, 0);
            }
            user.draw(this);
            if (timeIn % 150 == 0) {
                obstacles.add(generateObject());
            }
            if(jumpTimer < 120){
                user.jump(this);
            }
            else if(rollTimer < 120){
                user.roll(this);
            }
            else{
                user.stopAction(this);
            }
            moveObjects();
            timeIn++;
            jumpTimer++;
            rollTimer++;
            fill(0);
            textSize(25);
            text("Points: " + timeIn, 10, 20);
            text("Coins: " + coinCount, 10, 70);
        }
        if(gameOver) {
            gameOverScreen();
        }
        }
    }
    public void gameOverScreen(){
        if(!saved) {
            saveScore();
        }
        background(180);
        fill(255, 0, 0);
        rect(150, 150, 300, 75);
        textSize(50);
        fill(0, 0, 0);
        text("menu", 225, 200);
        text("GAME OVER", 160, 100);
    }
    public void saveScore(){
        try {
            PrintWriter out = new PrintWriter(new FileWriter("files/saveScores.txt", true));
            out.println(timeIn + "," + coinCount);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saved = true;
    }
    public void moveObjects(){
        for (int i = 0; i < obstacles.size(); i++) {
            MovingObject currObstacle = obstacles.get(i);
            if(currObstacle.lane() == user.lane()){
                if(currObstacle.bottom() > user.top()){
                    if(currObstacle.top() < user.bottom()){
                        if(currObstacle instanceof RollObstacle){
                            if(!user.getStatus().equals("r")){
                                gameOver = true;
                            }
                        }
                        else if(currObstacle instanceof JumpObstacle){
                            if(!user.getStatus().equals("j")){
                                gameOver = true;
                            }
                        } else if(currObstacle instanceof Coin){
                            coinCount++;
                            timeIn+= 100;
                            obstacles.remove(currObstacle);
                        }
                        else{
                            gameOver = true;
                        }
                    }
                }
            }
            if (currObstacle.reachedBottom()) {
                obstacles.remove(currObstacle);
            } else {
                currObstacle.draw(this);
                currObstacle.move();
                if(user.getStatus().equals("j")){
                    user.draw(this);
                }
            }
        }
    }
    public void keyReleased(){
        if (key == 'w'){
            user.jump(this);
            jumpTimer = 0;
            rollTimer = 1000;
        }
        if (key == 'a'){
            user.shiftLeft();
        }
        if (key == 's'){
            user.roll(this);
            rollTimer = 0;
            jumpTimer = 1000;
        }
        if (key == 'd'){
            user.shiftRight();
        }

    }
    public void mouseReleased(){
        if(!gameStart) {
            if (mouseX > 150 && mouseX < 450) {
                if (mouseY > 150 && mouseY < 225) {
                    gameStart = true;
                }
            }
            if (mouseX > 150 && mouseX < 450) {
                if (mouseY > 250 && mouseY < 325) {
                    leaderboardScreen = true;
                }
            }
        }
        if(gameOver){
            if (mouseX > 150 && mouseX < 450) {
                if (mouseY > 150 && mouseY < 225) {
                    setup();
                }
            }
        }
        if(leaderboardScreen){
            if(mouseX > 150 && mouseX < 150 + 300){
                if(mouseY > 520 && mouseY < 520+75){
                    setup();
                    leaderboardScreen = false;
                }
            }
        }
    }
    public void leaderBoard(){
        background(180);
        fill(255, 0, 0);
        rect(150, 520, 300, 75);
        fill(0);
        textSize(30);
        text("back", 235, 570);
        textSize(30);
        text("MAX SCORES", 220, 50);
        try {
            BufferedReader in = new BufferedReader(new FileReader("files/saveScores.txt"));
            String line;
            int end;
            ArrayList<Integer> scores = new ArrayList<>();
            while((line = in.readLine()) != null){
                scores.add(Integer.parseInt(line.split(",")[0]));
            }
            if(scores.size()<10){
                end = scores.size();
            }
            else {
                end = 10;
            }

            Collections.sort(scores, Collections.reverseOrder());
            for (int i = 0; i < end; i++) {
                textSize(15);
                text(i+1+": " + scores.get(i), 50, 100 + 25*i);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public MovingObject generateObject(){
        int lane = (int)(Math.random()*3+1);
        int obstacleType = (int)(Math.random()*4+1);
        int startX = 50 + (100 * lane);
        if(obstacleType == 1){
            return new RollObstacle(startX+10, lane);
        }
        else if(obstacleType == 2){
            return new Train(startX+10, lane);
        }
        else if(obstacleType == 3){
            return new JumpObstacle(startX+10, lane);
        }
        else{
            return new Coin(startX+20, lane);
        }
    }
    public static void main(String[] args) {
        PApplet.main("Main");
    }
}

