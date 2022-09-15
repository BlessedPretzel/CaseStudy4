package se233.chapter4.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import se233.chapter4.Launcher;
import se233.chapter4.model.Character;
import se233.chapter4.model.Keys;

public class Platform extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int GROUND = 300;
    private Keys keys;
    private Image platformImg;
    private Character[] character = new Character[2];

    public Platform() {
        keys = new Keys();
        platformImg = new Image(Launcher.class.getResourceAsStream("assets/Background.png"));
        ImageView backgroundImg = new ImageView(platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        // EXERCISE 1
        this.character[0] = new Character("Mario",30,30,0,0, KeyCode.A, KeyCode.D, KeyCode.W, 0, "MarioSheet.png", 16,32,32,64);
        this.character[1] = new Character("Megaman",30,30,0,0, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, 3, "MegamanSheet.png",541,456,69,69);
        getChildren().addAll(backgroundImg, character[0], character[1]);
    }

    public Character[] getCharacter() {
        return character;
    }

    public Keys getKeys() {
        return keys;
    }
}