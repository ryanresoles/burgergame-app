package burger;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import burger.GameStage;

public class Start1 extends GameStage{
	//private GameTimer gametimer;
	private int[][] maze_1 = {{0,0,0,0,0,0,0,0,0,0},{2,2,2,2,2,2,2,2,2,2},{1,0,1,0,0,0,0,1,0,1},{1,2,1,2,2,2,2,1,2,1},{1,0,0,0,1,1,0,0,0,1},{1,2,2,2,1,1,2,2,2,1},{1,0,1,0,0,0,0,1,0,1},{1,2,1,2,3,2,2,1,2,1},{0,0,0,0,0,0,0,0,0,0},{0,2,2,2,0,0,2,2,2,0}};
	private int[][] maze;
	private ArrayList<ImageView> cells;
	private Sprite ladder;
	private Sprite plank;
	public int playerXpos;
	public int playerYpos;
	
	public static GridPane map;
	public static final int MAP_NUM_ROWS= 10;
	public static final int MAP_NUM_COLS= 10;
	public final static int MAX_CELLS = (10*10);		
	public final static int MAP_WIDTH = 500;
	public final static int MAP_HEIGHT = 500;
	public final static int CELL_WIDTH = 50;
	public final static int CELL_HEIGHT = 50;
	public final static int WINDOW_WIDTH = 700;
	public final static int WINDOW_HEIGHT = 900;
	
	private Player player;
	
	public Start1() {
		this.root = new Group();
		map= new GridPane();
		this.maze = new int[Start.MAP_NUM_ROWS][Start.MAP_NUM_COLS];
		this.scene = new Scene(root, Start.WINDOW_WIDTH,Start.WINDOW_HEIGHT);	
		this.canvas = new Canvas(Start.WINDOW_WIDTH,Start.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		//this.gametimer= new GameTimer(this.gc,this.scene);
		this.cells = new ArrayList<ImageView>();
		this.ladder= new Sprite(0,0);
		this.ladder.loadImage(new Image("images/ladder.png"));
		this.plank= new Sprite(0,0);
		this.plank.loadImage(new Image("images/plank.png"));
		this.player = new Player(0, 0, Player.PLAYER_IMAGE);	
		this.handleKeyPressEvent();
		this.playerXpos=7;
		this.playerYpos=4;
	}
	public void setStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
		this.root.getChildren().add(this.canvas);
		this.initMaze();
		
		this.createMap();
		this.setGridPaneProperties();
		this.addGridPaneConstraints();
		for(ImageView cells: cells){
	    	 map.getChildren().add(cells);
	     }
		this.root.getChildren().add(map);
		this.stage.setTitle("Burger Game");
		this.stage.setScene(this.scene);
		this.stage.setResizable(false);
		//this.gametimer.start();
		this.stage.show();
		
		
	}
	
	private void initMaze(){
		for(int i=0;i<Start.MAP_NUM_ROWS;i++){
			System.out.println(Arrays.toString(this.maze_1[i]));//print final board content	
		}
	}
	
	private void createMap(){
		for(int i=0;i<Start.MAP_NUM_ROWS;i++){
			for(int j=0;j<Start.MAP_NUM_COLS;j++){
				
				ImageView iv = new ImageView();
				if(this.maze_1[i][j]==1) {
					addToStage(this.ladder,iv);
				}else if(this.maze_1[i][j]==2){
					addToStage(this.plank,iv);
				}else if(this.maze_1[i][j]==3){
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
		map.setLayoutX(Start.WINDOW_WIDTH*0.15);
	    map.setLayoutY(Start.WINDOW_HEIGHT*0.15);
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
	



	private void handleKeyPressEvent() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
	        	KeyCode code = e.getCode();
	            movePlayer(code);
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
	        public void handle(KeyEvent e){
	        	KeyCode code = e.getCode();
	            stopPlayer(code);
	        }
	    });
	}
	
	
	//method that will move the ship depending on the key pressed
	private void movePlayer(KeyCode ke) {
		if(ke==KeyCode.UP) {
			this.maze_1[this.playerXpos][this.playerYpos]=2;
			this.maze_1[--this.playerXpos][this.playerYpos]=3;
			this.cells.clear();
			this.createMap();
			map.getChildren().clear();
			for(ImageView cells: cells){
		    	 map.getChildren().add(cells);
		     }
			this.setGridPaneProperties();
			this.addGridPaneConstraints();
			//this.root.getChildren().add(map);
//			this.stage.setScene(this.scene);

		}
		if(ke==KeyCode.LEFT) {
			this.maze_1[this.playerXpos][this.playerYpos]=2;
			this.maze_1[this.playerXpos][--this.playerYpos]=3;
			this.cells.clear();
			this.createMap();
			map.getChildren().clear();
			for(ImageView cells: cells){
		    	 map.getChildren().add(cells);
		     }
			this.setGridPaneProperties();
			this.addGridPaneConstraints();
			
		}
		if(ke==KeyCode.DOWN){
			this.maze_1[this.playerXpos][this.playerYpos]=2;
			this.maze_1[++this.playerXpos][this.playerYpos]=3;
			this.cells.clear();
			this.createMap();
			map.getChildren().clear();
			for(ImageView cells: cells){
		    	 map.getChildren().add(cells);
		     }
			this.setGridPaneProperties();
			this.addGridPaneConstraints();
	
		}
		if(ke==KeyCode.RIGHT) {
			this.maze_1[this.playerXpos][this.playerYpos]=2;
			this.maze_1[this.playerXpos][++this.playerYpos]=3;
			this.cells.clear();
			this.createMap();
			map.getChildren().clear();
			for(ImageView cells: cells){
		    	 map.getChildren().add(cells);
		     }
			this.setGridPaneProperties();
			this.addGridPaneConstraints();
			
		}
		if(ke==KeyCode.SPACE){
			//this.player.attack();
		}
		System.out.println(ke+" key pressed.");
		}
	
	//method that will stop the ship's movement; set the ship's DX and DY to 0
	private void stopPlayer(KeyCode ke){
		this.player.setDX(0);
		this.player.setDY(0);
	}

	
}

