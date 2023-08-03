package burger;

import javafx.scene.image.Image;

public class Player extends Sprite{
	private boolean alive;

	public static final int PLAYER_WIDTH = 50;
	public static final int PLAYER_HEIGHT = 50;
	public static final int PLAYER_SPEED = 3;
	public static final Image PLAYER_IMAGE = new Image("images/spongeLeft.png", PLAYER_WIDTH, PLAYER_HEIGHT, false, false);

	public Player(int x, int y, Image image){
		super(x,y);
		this.alive = true;
		this.loadImage(image);
	}

	public boolean isAlive(){
		if(this.alive) return true;
		return false;
	}

	public void die(){
		System.out.println("Player is dead");
    	this.alive = false;
    }

	public void move() {
    	this.x += this.dx;
    	this.y += this.dy;
	}

}
