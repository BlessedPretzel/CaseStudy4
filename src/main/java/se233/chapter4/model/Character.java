package se233.chapter4.model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.chapter4.Launcher;
import se233.chapter4.view.Platform;

public class Character extends Pane {
    Logger logger = LoggerFactory.getLogger(Character.class);
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    private Image characterImg;
    private AnimatedSprite imageView;
    private int x;
    private int y;
    private KeyCode leftKey, rightKey, upKey;
    //EXERCISE 2
    int speedModifier;
    int xVelocity = 0;
    int yVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 7;
    int yMaxVelocity = 17;
    boolean canJump = false;
    boolean isJumping = false;
    boolean isFalling = true;
    private boolean isMoveLeft = false;
    private boolean isMoveRight = false;
    //EXERCISE 2
    public void moveY() {
        setTranslateY(y);
        if (isFalling) {
            yVelocity = yVelocity >= yMaxVelocity? yMaxVelocity : yVelocity+yAcceleration;
            y = y + yVelocity + speedModifier;
        } else if (isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity-yAcceleration;
            y = y - yVelocity - speedModifier;
        }
    }

    public void jump() {
        if (canJump) {
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling =false;
        }
    }

    public void checkReachHighest() {
        if (isJumping && yVelocity<=0) {
            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }
    public void moveLeft() {
        isMoveLeft = true;
        isMoveRight = false;
    }
    public void moveRight() {
        isMoveLeft = false;
        isMoveRight = true;
    }
    //EXERCISE 2
    public void moveX() {
        setTranslateX(x);
        if (isMoveLeft) {
            xVelocity = xVelocity >= xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x -xVelocity - speedModifier;
        }
        if (isMoveRight) {
            xVelocity = xVelocity >= xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x +xVelocity + speedModifier;
        }
    }
    public void stop() {
        isMoveLeft = false;
        isMoveRight = false;
    }

    public void checkReachFloor() {
        if (isFalling && y>= Platform.GROUND - CHARACTER_HEIGHT) {
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }

    public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
        } else if (x+getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int)getWidth();
        }
    }
    public void repaint() {
        moveX();
        moveY();
    }
    //EXERCISE 2
    public Character(int x, int y, int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey, int speedModifier, String assets) {
        this.x = x;
        this.y = y;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.speedModifier = speedModifier;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.characterImg = new Image(Launcher.class.getResourceAsStream("assets/"+assets));
        this.imageView = new AnimatedSprite(characterImg, 4,4,1,offsetX,offsetY,16,32);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.getChildren().addAll(this.imageView);
    }

    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}",x,y,xVelocity,yVelocity);
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }
}
