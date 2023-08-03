package burger;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
	protected ImageView imv;
	protected Image img;
	protected int x, y, dx, dy;
	protected boolean visible;
	protected double width;
	protected double height;
	
	public Sprite(int xPos, int yPos){
		this.x = xPos;
		this.y = yPos;
		this.dx = 0;
		this.dy = 0;
		this.visible = true;
	}
	protected void makeImageView(Image img) {
			this.imv= new ImageView(img);
		
	}
	
	protected ImageView getImageView() {
		return this.imv;
	}
	//method to set the object's image
	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}
	
	//method to set the image to the image view node
	void render(GraphicsContext gc){
		gc.drawImage(this.img, this.x, this.y);
        
    }
	
	void put(ImageView iv){	
		iv.setImage(this.img);
		//set x and y position of the image view
		iv.setLayoutX(this.x);
		iv.setLayoutY(this.y);
        
    }
	
	//method to set the object's width and height properties
	void setSize(){
		this.width = this.img.getWidth();
	    this.height = this.img.getHeight();
	}
	//method that will check for collision of two Entitys
	public boolean collidesWith(Sprite rect2)	{
		Rectangle2D rectangle1 = this.getBounds();
		Rectangle2D rectangle2 = rect2.getBounds();

		return rectangle1.intersects(rectangle2);
	}
	//method that will return the bounds of an image
	public Rectangle2D getBounds(){
		return new Rectangle2D(this.x, this.y, this.width, this.height);
	}
	
	//method to return the image
	Image getImage(){
		return this.img;
	}
	//getters
	public int getX() {
    	return this.x;
	}

	public int getY() {
    	return this.y;
	}
	
	public int getDX(){
		return this.dx;
	}
	public int getDY(){
		return this.dy;
	}
	
	public boolean getVisible(){
		return visible;	
	}
	public boolean isVisible(){
		if(visible) return true;
		return false;
	}
	
	public double getHeight(){
		return this.height;
	}
	
	//setters
	public void setDX(int dx){
		this.dx = dx;
	}
	
	public void setDY(int dy){
		this.dy = dy;
	}
	public void setX(int x) {
    	this.x+=x;
	}

	public void setY(int y) {
    	this.y+=y;
	}
	
	public void setWidth(double val){
		this.width = val;
	}

	public void setHeight(double val){
		this.height = val;
	}
		
	public void setVisible(boolean value){
		this.visible = value;
	}
	
	

}