package burger;

import burger.GameStage;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import burger.GameTimer;

public class GameStage {
	protected Scene scene;
	protected Stage stage;
	protected Group root;
	protected Canvas canvas;
	protected GraphicsContext gc;
	public final static int WINDOW_WIDTH = 1000;
	public final static int WINDOW_HEIGHT =500;
	public final Image back= new Image("images/background.jpg", GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
	public static Scene mainScene;
	//public final AudioClip aud= new AudioClip();
	
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);	
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
	}
	public void setDim(ImageView a, double x, double y) {
		a.setX(x);
		a.setY(y);
	}
	public void setStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
		this.gc.drawImage(this.back,0,0);
		
		Image s=new Image("images/start.png");
		ImageView start= new ImageView(s);
		Image a=new Image("images/about.png");
		ImageView about= new ImageView(a);
		Image i=new Image("images/instruction.png");
		ImageView instruct= new ImageView(i);
		Image e=new Image("images/exit.png");
		ImageView exit= new ImageView(e);
		setDim(start,400,300);
		setDim(about,400,340);
		setDim(instruct,400,380);
		setDim(exit,400,420);
		
		//set stage elements here
		this.root.getChildren().add(canvas);
		this.root.getChildren().add(start);
		this.root.getChildren().add(about);
		this.root.getChildren().add(instruct);
		this.root.getChildren().add(exit);
		this.stage.setTitle("Burger Game");
		this.stage.setScene(this.scene);
		this.stage.setResizable(false);
		this.stage.show();
		
		this.addEventHandler(start);
		this.addEventHandler(about);
		this.addEventHandler(instruct);
		this.addEventHandler(exit);
		
		start.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Start clicked.");
				Start start= new Start();
				start.setStage(stage);
			}
		});
		
		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		about.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("About clicked.");
				About about= new About();
				about.setStage(stage);
			}
		});
		
		instruct.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Instructions clicked.");
				Instruction instruction= new Instruction();
				instruction.setStage(stage);
			}
		});
	
	}
	
	private void addEventHandler(ImageView btn) {
		// TODO Auto-generated method stub
		btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				btn.setScaleX(1.2);
				btn.setScaleY(1.2);
			}
		});
		btn.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				btn.setScaleX(1);
				btn.setScaleY(1);
			}
		});
		
	}
	
}