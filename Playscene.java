import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;

import javafx.scene.input.KeyCode;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Playscene extends Application {


    private boolean gameOverTriggered = false;  
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int ROWS = 20;
    private static final int COLOUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH/ROWS;
    private static final String[] FOODS_IMAGE = new String[]{"images/ic_apple.png",
            "images/ic_berry.png",
            "images/ic_cherry.png",
            "images/ic_coconut_.png",
            "images/ic_orange.png",
            "images/ic_peach.png",
            "images/ic_pomegranate.png",
            "images/ic_tomato.png",
            "images/ic_watermelon.png"};
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private GraphicsContext gc;
    private final List<Point> snakeBody = new ArrayList<>();
    private Point snakeHead;
    private Image foodImage;
    private int foodX;
    private int foodY;
    private Boolean gameOver = false;
    private int currentDirection;
    private int score = 0;
    private Timeline timeline;
    private int currentSpeed = 150;
    private final int speedIncrease = 5;
    @Override
    public void start(Stage primaryStage) {


        //stage setup
        primaryStage.setTitle("Slithering Serpent");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH,HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        /*while(true){
           if(gameOverTriggered){
               primaryStage.close();
           }
        
        }
        */

        gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if(code == KeyCode.RIGHT || code == KeyCode.D){
                    if(currentDirection!= LEFT)
                        currentDirection = RIGHT;
                }
                else if(code == KeyCode.LEFT || code == KeyCode.A){
                    if(currentDirection!=RIGHT)
                        currentDirection = LEFT;
                }
                else if(code == KeyCode.UP || code == KeyCode.W){
                    if(currentDirection!=DOWN)
                        currentDirection = UP;
                }
                else if(code == KeyCode.DOWN || code == KeyCode.S){
                    if(currentDirection!=UP)
                        currentDirection = DOWN;
                }
            }
        });
        //creating the starting body of the snake,3 in size
        for(int i=0;i<3;i++)
        {
            snakeBody.add(new Point(5,ROWS/2));
        }
        snakeHead = snakeBody.get(0);

        //getting the correct co-ordinates of the food and not on the snake body
        generateFood();
        timeline = new Timeline(new KeyFrame(Duration.millis(currentSpeed),e->run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
    }
    

    public void run(GraphicsContext gc)
    {
        if(gameOver)
        {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Arial",70));
            gc.fillText("GAME OVER",WIDTH/4, (double) HEIGHT /2);
            timeline.stop();
            return;
        }
        //game board background
        drawBackground(gc);
        //placing the food as a UI on the board
        drawFood(gc);
        //giving color to the snake
        drawSnake(gc);
        //updating scorecard
        drawScore();
        for(int i= snakeBody.size()-1;i>=1;i--)
        {
            snakeBody.get(i).x = snakeBody.get(i-1).x;
            snakeBody.get(i).y = snakeBody.get(i-1).y;
        }

        switch (currentDirection) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
        eatFood();
        gameOver();
    }

    public void drawBackground(GraphicsContext gc)
    {
        for(int i=0;i<ROWS;i++) {
            for (int j = 0; j < COLOUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void generateFood()
    {
        start:
        while(true){
            foodX = (int)(Math.random()*ROWS);
            foodY = (int)(Math.random()*COLOUMNS);

            for(Point snake: snakeBody){
                if(snake.getX() == foodX && snake.getY() == foodY){
                    continue start;
                }
            }

            foodImage = new Image(FOODS_IMAGE[(int)(Math.random()*FOODS_IMAGE.length)]);
            break;
        }
    }

    private void drawFood(GraphicsContext gc)
    {
        gc.drawImage(foodImage,foodX*SQUARE_SIZE,foodY*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE);
    }

    private void drawSnake(GraphicsContext gc)
    {
        gc.setFill(Color.web("4674E9"));
        gc.fillRoundRect(snakeHead.getX()*SQUARE_SIZE,snakeHead.getY()*SQUARE_SIZE,SQUARE_SIZE-1,SQUARE_SIZE-1,35,35);
        for(int i=1;i<snakeBody.size();i++)
        {
            gc.fillRoundRect(snakeBody.get(i).getX()*SQUARE_SIZE,snakeBody.get(i).getY()*SQUARE_SIZE,SQUARE_SIZE-1,SQUARE_SIZE-1,20,20);
        }
    }

    private void moveRight()
    {
        snakeHead.x++;
    }
    private void moveLeft()
    {
        snakeHead.x--;
    }
    private void moveUp()
    {
        snakeHead.y--;
    }
    private void moveDown()
    {
        snakeHead.y++;
    }

    public void gameOver(){
        //collision detection with walls
        if(snakeHead.x < 0 || snakeHead.y <0 || snakeHead.x*SQUARE_SIZE >= WIDTH || snakeHead.y*SQUARE_SIZE >= HEIGHT)
        {
            gameOver = true;
        }
        //collision detection with its body
        for(int i=1;i<snakeBody.size();i++)
        {
            if(snakeBody.get(i).x == snakeHead.x && snakeBody.get(i).y == snakeHead.y )
            {
                gameOver = true;
                break;
            }
        }
    }

    private void eatFood(){
        if(snakeHead.getX() == foodX && snakeHead.getY() == foodY){
            snakeBody.add(new Point(-1,-1));
            generateFood();
            score++;
            currentSpeed -= speedIncrease;
            timeline.stop();
            timeline = new Timeline(new KeyFrame(Duration.millis(currentSpeed),e->run(gc)));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }

    private void drawScore()
    {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial",35));
        gc.fillText("Score: "+score,10,35);
    }
}
