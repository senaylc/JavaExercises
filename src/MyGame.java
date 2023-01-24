import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MyGame extends Application {

    final int GAME_WIDTH = 1000;
    final int GAME_HEIGHT = 400;
    final double GRAVITY = 0.4;
    final int JUMP_SPEED = -14;

    Group rootViewContainer;
    Canvas gameCanvas;
    GraphicsContext graphicsContext;
    EventHandler<KeyEvent> keyPressed;
    EventHandler<KeyEvent> keyReleased;
    AnimationTimer timer;
    // game objects
    GameObject player;
    GameObject star;
    ArrayList<GameObject> obstacles;
    GameObject platform;

    AudioClip sound = new AudioClip("file:jump.mp3");
    AudioClip overSound = new AudioClip("file:over.mp3");
    AudioClip plusLevel = new AudioClip("file:plus.mp3");

    // game state variables
    int score = 0;
    int level = 1;
    boolean gameOver = false;
    boolean collectedStar = false;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Init game objects and parameters like key event listeners, timers etc.
     */
    public void initGame() {

        collectedStar = false;
        rootViewContainer.getChildren().clear();
        gameCanvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        graphicsContext = gameCanvas.getGraphicsContext2D();
        rootViewContainer.getChildren().add(gameCanvas);


        // Initialize player, obstacles, and platform objects here
        player = new GameObject();
        player.setX(100);
        player.setY(100);
        player.setWidth(50);
        player.setHeight(50);
        player.setImage(new Image("res/player.png", true)); // load player image from file

        star = new GameObject();
        star.setX(GAME_WIDTH / 2);
        star.setY(GAME_HEIGHT / 3);
        star.setWidth(50);
        star.setHeight(50);
        star.setImage(new Image("res/stt.png", false)); // load player image from file


        obstacles = new ArrayList<>();
        ArrayList<Integer> coord = new ArrayList<>();
        for (int j = 500; j <= GAME_WIDTH; j += 475) {
            coord.add(j);
        }
        for (int j = 0; j < 1; j++) {

            GameObject obstacle = new GameObject();
            Random r = new Random();
            int a = (r.nextInt(coord.size()));
            obstacle.setX(coord.get(a));
            coord.remove(coord.get(a));
            obstacle.setY(GAME_HEIGHT - 100);
            obstacle.setWidth(30);
            obstacle.setHeight(50);
            obstacle.setImage(new Image("res/cactus.png", false));
            obstacles.add(obstacle);

            GameObject obstacle1 = new GameObject();
            obstacle1.setX(coord.get(r.nextInt(coord.size())));
            obstacle1.setY(GAME_HEIGHT - 120);
            obstacle1.setWidth(50);
            obstacle1.setHeight(75);
            obstacle1.setImage(new Image("res/cactus_big.png", false));
            obstacles.add(obstacle1);

        }

        int i = 0;
        while (i < 1) {
            platform = new GameObject();
            platform.setX(0);
            platform.setY(GAME_HEIGHT - 50);
            platform.setWidth(GAME_WIDTH);
            platform.setHeight(50);
            platform.setImage(new Image("res/platform.png", false));
            i++;
        }

        initKeyEventListeners();
        initTimer();

        timer.start();
    }

    /**
     * keyPressed and keyReleased are the two main keyboard event listener objects. You can check which keyboard
     * keys are pressed or released by means of this two objects and make appropriate changes in your game.
     */
    void initKeyEventListeners() {
        keyPressed = event -> {
            switch (event.getCode()) {
                case LEFT:
                    // Update player position to move left
                    player.setX(player.getX() - 15 - ((3 * level) / 4));
                    break;
                case RIGHT:
                    // Update player position to move right
                    player.setX(player.getX() + 15 + ((3 * level) / 4));

                    break;
                case UP:
                    // Update player position and velocity to jump
                    if (player.getY() == GAME_HEIGHT - 100) {
                        player.setY(player.getY() + JUMP_SPEED);
                        player.setVelocityY(JUMP_SPEED - ((3 * level) / 8));
                        sound.play();

                    }
                    break;
                case ENTER:
                    // Start a new game when Enter is pressed
                    if (gameOver) {
                        gameOver = false;
                        score = 0;
                        level = 1;
                        initGame();
                    }
                    break;
            }
        };

        keyReleased = event -> {
            switch (event.getCode()) {
                case LEFT:
                case RIGHT:
                    // Stop horizontal movement when key is released
                    player.setVelocityX(0);
                    break;
            }
        };

        gameCanvas.setOnKeyPressed(keyPressed);
        gameCanvas.setOnKeyReleased(keyReleased);
    }

    void updateGame() {
        // Update player position based on velocity
        player.setY(player.getY() + player.getVelocityY());

        // Apply gravity to player velocity
        player.setVelocityY(player.getVelocityY() + GRAVITY);

        star.setX(star.getX() - 3 - ((3 * level) / 4));

        // Update obstacle positions based on level
        for (GameObject obstacle : obstacles) {
            obstacle.setX(obstacle.getX() - 3 - ((3 * level) / 4));

            // If obstacle is offscreen, move it back to the right side of the screen and increment score
            if (obstacle.getX() + obstacle.getWidth() < 0) {
                obstacle.setX(GAME_WIDTH);
                score++;
                // Increment level every time the score reaches a multiple of 10
                if (score % 10 == 0 && score != 0) {
                    level++;

                    plusLevel.play();
                }

            }

            // Check for collision between player and obstacle
            if (player.collidesWith(obstacle)) {
                gameOver = true;
                overSound.play();
            }
        }

        // If platform is offscreen, move it back to the right side of the screen
        if (platform.getX() + platform.getWidth() < 0) {
            platform.setX(0);
        }


        // Check for collision between player and platform
        if (player.collidesWith(platform)) {
            // Stop player's downward movement when it touches the platform
            player.setVelocityY(0);
            player.setY(GAME_HEIGHT - platform.getHeight() - player.getHeight());
        }
        if (player.collidesWith(star)) {
            collectedStar = true;
        }


    }

    /**
     * This timer object will call the handle() method at every frame. So, in this method's body, you can
     * redraw your objects to make a movement effect, check whether any of your objects collided or not,
     * and update your game score etc. This is the main lifecycle of your game.
     */
    void initTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (gameOver) {
                    // Render game over message and stop timer
                    graphicsContext.strokeText("Game Over! Your Score is: " + score, GAME_WIDTH / 2 - 170, GAME_HEIGHT / 2);
                    if (collectedStar)
                        graphicsContext.strokeText("with Bonus Star: " + (score + 20), GAME_WIDTH / 2 + 30, GAME_HEIGHT / 2);
                    else
                        graphicsContext.strokeText("with Bonus Star: " + (score), GAME_WIDTH / 2 + 30, GAME_HEIGHT / 2);
                    graphicsContext.strokeText("Press ENTER to Start Again", GAME_WIDTH / 2 - 85, GAME_HEIGHT / 2 + 20);
                    timer.stop();
                    return;
                }
                updateGame();
                // Keep player within the game screen
                if (player.getX() < 0) {
                    player.setX(0);
                } else if (player.getX() + player.getWidth() > GAME_WIDTH) {
                    player.setX(GAME_WIDTH - player.getWidth());
                }
                if (player.getY() < 0) {
                    player.setY(0);
                } else if (player.getY() + player.getHeight() > GAME_HEIGHT) {
                    player.setY(GAME_HEIGHT - player.getHeight());
                }

                // Render game objects
                graphicsContext.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
                player.render(graphicsContext);

                for (GameObject obstacle : obstacles) {
                    obstacle.render(graphicsContext);
                }

                platform.render(graphicsContext);
                if (!collectedStar) star.render(graphicsContext);


                // Render score and level
                graphicsContext.strokeText("Score: " + score, 10, 20);
                graphicsContext.strokeText("Level: " + level, 10, 40);
            }
        };
    }

    @Override
    public void start(Stage primaryStage) {
        rootViewContainer = new Group();
        rootViewContainer.getChildren().removeAll();
        initGame();
        Scene jScene = new Scene(rootViewContainer, GAME_WIDTH, GAME_HEIGHT);
        jScene.setFill(Color.SKYBLUE);
        jScene.addEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
        jScene.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        primaryStage.setTitle("HUBBM-Dino");
        primaryStage.setScene(jScene);
        primaryStage.show();
    }
}
