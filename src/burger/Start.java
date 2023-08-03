package burger;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import burger.GameStage;

public class Start extends GameStage{
	private GameTimer gametimer;
	private int[][] maze;
	private ArrayList<ImageView> cells;
	private Sprite ladder;
	private Sprite plank;
	
	public static GridPane map;
	public static final int MAP_NUM_ROWS= 10;
	public static final int MAP_NUM_COLS= 12;
	public final static int MAX_CELLS = 120;		
	public final static int MAP_WIDTH = 500;
	public final static int MAP_HEIGHT = 500;
	public final static int CELL_WIDTH = 50;
	public final static int CELL_HEIGHT = 50;
	public final static int MAP_BOUND_UP = 50;
	public final static int MAP_BOUND_DOWN = 400;
	public final static int MAP_BOUND_LEFT = 100;
	public final static int MAP_BOUND_RIGHT = 600;
	public final static int WINDOW_WIDTH = 1000;
	public final static int WINDOW_HEIGHT = 500;
	
	private Player player;
	
	public Start() {
		this.root = new Group();
//		map = new GridPane();
//		this.maze = new int[Start.MAP_NUM_ROWS][Start.MAP_NUM_COLS];
//		this.maze = MazeGenerator.getRandomMaze();
		this.scene = new Scene(root, Start.WINDOW_WIDTH,Start.WINDOW_HEIGHT);	
		this.canvas = new Canvas(Start.WINDOW_WIDTH,Start.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.gametimer= new GameTimer(this.gc,this.scene);
//		this.cells = new ArrayList<ImageView>();
		this.ladder= new Sprite(0,0);
		this.ladder.loadImage(new Image("images/ladder.png"));
		this.plank= new Sprite(0,0);
		this.plank.loadImage(new Image("images/plank.png"));
		this.player = new Player(0, 0, Player.PLAYER_IMAGE);	
	}
	public void setStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
		
//		this.initMaze();
//		
//		this.createMap();
//		this.setGridPaneProperties();
//		this.addGridPaneConstraints();
//		for(ImageView cells: cells){
//	    	 map.getChildren().add(cells);
//	     }
		this.root.getChildren().add(this.canvas);
//		this.root.getChildren().add(map);
		
		this.stage.setTitle("Burger Game");
		this.stage.setScene(this.scene);
		this.stage.setResizable(false);
		this.gametimer.start();
		this.stage.show();
		
	}
	
	private void initMaze(){
		for(int i=0;i<Start.MAP_NUM_ROWS;i++){
			System.out.println(Arrays.toString(this.maze[i])); //print final board content	
		}
	}
	
	private void createMap(){
		for(int i=0;i<Start.MAP_NUM_ROWS;i++){
			for(int j=0;j<Start.MAP_NUM_COLS;j++){
				
				ImageView iv = new ImageView();
				if(this.maze[i][j]==1) {
					addToStage(this.ladder,iv);
				}else if(this.maze[i][j]==2){
					addToStage(this.plank,iv);
				}else if(this.maze[i][j]==3){
					addToStage(this.player,iv);
				}else{
					
				}
				

				iv.setPreserveRatio(true);
				iv.setFitWidth(Start.CELL_WIDTH);
				iv.setFitHeight(Start.CELL_HEIGHT);
					
				
				iv.setId(Integer.toString(i)+"-"+Integer.toString(j));
				

				this.cells.add(iv);
			}		
		}
		
	}
	private void addToStage(Sprite elem, ImageView iv) {
		elem.put(iv);
		//add a mouse event handler to each element
		//this.addMouseEventHandler(elem,iv);
	}
	private void setGridPaneProperties(){
		map.setPrefSize(Start.MAP_WIDTH, Start.MAP_HEIGHT);
		//set the map to x and y location; add border color to see the size of the gridpane/map  
	    //this.map.setStyle("-fx-border-color: red ;");
		map.setLayoutX(Start.WINDOW_WIDTH*0);
	    map.setLayoutY(Start.WINDOW_HEIGHT*0);
	}
	private void addGridPaneConstraints(){
		
		//set number of rows
		for(int i=0;i<Start.MAP_NUM_ROWS;i++){
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(30);
			map.getRowConstraints().add(row);
		}
	    
	     //set the number of columns and width of each column (in %); 3 cols, width = 30%;
		for(int i=0;i<Start.MAP_NUM_COLS;i++){
			ColumnConstraints col = new ColumnConstraints();
			col.setPercentWidth(30);
			map.getColumnConstraints().add(col);
		}	  
	          
		 //loop that will add the image views / land images to the gridpane
	     int counter=0;
	     for(int row=0;row<Start.MAP_NUM_ROWS;row++){
	    	 for(int col=0;col<Start.MAP_NUM_COLS;col++){
	    		 //set each landCells arraylist element (imageview) to the gridpane/map's constraints
	    		 GridPane.setConstraints(cells.get(counter),col,row); 
	    		 counter++;
	    	 }
	     }   
	}

	

	
}
