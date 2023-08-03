package burger;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import burger.GameStage;
import burger.Sprite;

public class Enemy extends Sprite{
	private boolean stunned;
	private int type;
	
	public static final int ENEMY_SPEED = 2; 
	public static final int ENEMY_WIDTH = 50;
	public static final int ENEMY_HEIGHT = 50;
	public static final Image ENEMY_IMAGE = new Image("images/spongeLeft.png", ENEMY_WIDTH, ENEMY_HEIGHT, false, false);
	
//	public static final Image ENEMY_IMAGE_1 = new Image("images/spongeLeft.png", 100, 100, false, false);
//	public static final Image ENEMY_IMAGE_2 = new Image("images/spongeLeft.png", 100, 100, false, false);
//	public static final Image ENEMY_IMAGE_3 = new Image("images/spongeLeft.png", 100, 100, false, false);

	public Enemy(int x, int y, Image image, int type){
		super(x,y);
		this.dx = ENEMY_SPEED;
//		this.dy = ENEMY_SPEED;
		this.type = type;
		this.stunned = false;
		this.loadImage(image);
	}

	public boolean isStunned(){
		if(this.stunned) return true;
		return false;
	}

	public void respawn(int seconds){
		System.out.println("Enemy is dead");
    	this.stunned = true;
    	this.setVisible(false);
		PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
		pause.delayProperty();
		this.setVisible(true);
		this.stunned=false;
		pause.play();
    }

	public void move() {
    	this.x += this.dx;
    	this.y += this.dy;
	}
}
