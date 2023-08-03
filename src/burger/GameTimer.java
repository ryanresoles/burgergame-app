package burger;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;

import java.awt.Canvas;
import java.util.ArrayList;
import java.util.Random;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import burger.GameStage;

public class GameTimer extends AnimationTimer{
	private GraphicsContext gc;
	private Scene theScene;
	private Player player;
//	private Enemy enemy;
//	private ArrayList<Enemy> enemies;
	private Enemy[] enemies;

	
	public GameTimer(GraphicsContext gc, Scene theScene){
		this.gc = gc;
		this.theScene = theScene;
		this.player = new Player(Start.MAP_BOUND_LEFT, Start.MAP_BOUND_UP, Player.PLAYER_IMAGE);
//		this.enemy = new Enemy(100,100,Player.PLAYER_IMAGE);
		//this.enemies = new ArrayList<Enemy>();
		this.enemies = new Enemy[5];
		Random r = new Random();
		for(int i=0; i<5; i++) {
			int y = r.nextInt(Start.MAP_HEIGHT-Enemy.ENEMY_HEIGHT)+Start.MAP_BOUND_UP;
			this.enemies[i] = new Enemy(Start.MAP_BOUND_RIGHT-Enemy.ENEMY_WIDTH,y,Enemy.ENEMY_IMAGE,0);
		}
		
		this.handleKeyPressEvent(); // handle mouse click event
	}

	@Override
	public void handle(long currentNanoTime) {
//		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc.drawImage(new Image("images/stage_1.jpg", Start.WINDOW_WIDTH,Start.WINDOW_HEIGHT, false, false), 0, 0);
		//this.gc.fillRect((Start.WINDOW_WIDTH*0.15)-25, (Start.WINDOW_HEIGHT*0.15)-25, Start.MAP_WIDTH+50, Start.MAP_HEIGHT+50);
		
		// move the player
		this.player.move();

		// set bounds for player
		if(this.player.getY()<=Start.MAP_BOUND_UP) { this.stopPlayer(); this.player.y=Start.MAP_BOUND_UP+1;}
		if(this.player.getX()<=Start.MAP_BOUND_LEFT) { this.stopPlayer(); this.player.x=Start.MAP_BOUND_LEFT+1;}
		if(this.player.getX()>=(Start.MAP_BOUND_RIGHT-Player.PLAYER_HEIGHT)) { 
			this.stopPlayer(); this.player.x=Start.MAP_BOUND_RIGHT-Player.PLAYER_HEIGHT-1;}
		if(this.player.getY()>=(Start.MAP_BOUND_DOWN-Player.PLAYER_WIDTH)) { 
			this.stopPlayer(); this.player.y=Start.MAP_BOUND_DOWN-Player.PLAYER_WIDTH-1;}

		// render the player
		this.player.render(this.gc);

		for(Enemy enemy : this.enemies) {
			if(!enemy.isStunned()) {
				//enemy collides with player
				if(enemy.collidesWith(this.player)) {
					System.out.println("Enemy collides with player");
//					this.stopPlayer();
//					this.stopAllEnemies();
//					this.player.die();
					//GAME OVER
//					this.stop();
//					this.setGameOver(0);
				}
				Random r = new Random();
				if(enemy.getX()<=Start.MAP_BOUND_LEFT || enemy.getX()>=(Start.MAP_BOUND_RIGHT-Enemy.ENEMY_WIDTH)) {
					enemy.dx *= (-1);
				}
//				if(enemy.getX()<=0 || enemy.getX()>=(GameStage.WINDOW_WIDTH-Enemy.ENEMY_WIDTH)) {
//					int direction = r.nextInt(2);
//					if(direction==0) {enemy.dy *= (-1);}
//					else {}
//				}
//				else if(enemy.getY()<=0 || enemy.getY()>=(GameStage.WINDOW_HEIGHT-Enemy.ENEMY_HEIGHT)) {
//					enemy.dx *= (-1);
//				}
				enemy.move();
				enemy.render(this.gc);
			}
		}
//		if(!this.enemy.isStunned()) {
//			this.enemy.move();
//			this.enemy.render(this.gc);
//		}
//		PauseTransition pause = new PauseTransition(Duration.seconds(3));
//		pause.delayProperty();
//		this.enemy.respawn(2);
//		pause.play();
	}

	
	public void setGameOver(boolean win){
		
	}
	
	
	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                movePlayer(code);
			}
		});
		
		theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                stopPlayer(code);
            }
        });
    }
	
	private boolean checkYPos(Sprite sprite) {
		if(sprite.getY()==51 || (sprite.getY()<=154 && sprite.getY()>=146) || (sprite.getY()<=254 && sprite.getY()>=246) || sprite.getY()==349) {
			return true;
		}
		return false;
	}
	
	private boolean checkXPos(Sprite sprite) {
		if(sprite.getX()<=104 && sprite.getX()>=96) {//ladder 1
			return true;
		}else if(sprite.getX()<=204 && sprite.getX()>=196 && (sprite.getY()>=49 && sprite.getY()<=146)) {//ladder 2
			return true;
		}
		return false;
	}
	
	//method that will move the ship depending on the key pressed
	private void movePlayer(KeyCode ke) {
		if(ke==KeyCode.UP) {
//			System.out.println(Start.map.getChildren().get(1));
			this.player.setDY(-Player.PLAYER_SPEED);
			this.player.setDX(0);
		}
		else if(ke==KeyCode.LEFT) {
			if(this.checkYPos(this.player)) {
//			if(this.player.getY()==51 || this.player.getY()==150 || this.player.getY()==250 || this.player.getY()==349) {
				this.player.setDX(-Player.PLAYER_SPEED);
				this.player.setDY(0);
			}
		}
		else if(ke==KeyCode.DOWN){
			System.out.println(this.player.getY());
			
			if(this.checkXPos(this.player)){
				this.player.setDY(Player.PLAYER_SPEED);
				this.player.setDX(0);
				
			}
		}
		else if(ke==KeyCode.RIGHT) {
			System.out.println(this.player.getY());
			if(this.checkYPos(this.player)) {
				this.player.setDX(Player.PLAYER_SPEED);
				this.player.setDY(0);
			}
		}
		else if(ke==KeyCode.SPACE){
			//this.player.attack();
		}
		System.out.println(ke+" key pressed.");
   	}
	
	//method that will stop the ship's movement; set the ship's DX and DY to 0
	private void stopPlayer(KeyCode ke){
		this.player.setDX(0);
		this.player.setDY(0);
	}

	private void stopPlayer(){
		this.player.setDX(0);
		this.player.setDY(0);
	}
	
	private void stopAllEnemies(){
		for(Enemy enemy : this.enemies) {
			enemy.setDX(0);
		}
	}
}
