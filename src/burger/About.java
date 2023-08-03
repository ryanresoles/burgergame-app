package burger;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class About extends GameStage {
	public final Image about= new Image("images/aboutPage.jpg", GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
	public About() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);	
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);//create a 400x400screen canvas for "drawing" elements
		this.gc = canvas.getGraphicsContext2D();
		
	}
	public void setStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
		
		this.gc.drawImage(this.about,0,0);
		Image a=new Image("images/back.png");
		ImageView back= new ImageView(a);
		setDim(back,750,400);
		this.addEventHandler(back);
		this.root.getChildren().add(this.canvas);
		this.root.getChildren().add(back);
		this.stage.setTitle("Burger Game");
		this.stage.setScene(this.scene);
		this.stage.setResizable(false);
		this.stage.show();
		
		
		
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				GameStage theGameStage = new GameStage();
				theGameStage.setStage(stage);
			}
		});
		
		
	}
	private void addEventHandler(ImageView btn) {
		// TODO Auto-generated method stub
		btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("Cursor hovered at button.");
				btn.setScaleX(1.2);
				btn.setScaleY(1.2);
				
			}
		});
		btn.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("Cursor hovered at button.");
				btn.setScaleX(1);
				btn.setScaleY(1);
				
			}
		});
	}
	
}

