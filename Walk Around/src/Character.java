import java.awt.image.BufferedImage;

public class Character {
	int x;
	int y;
	BufferedImage currentImage;
	
	public Character(){
		
	}
	
	public Character(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Character(int x, int y, BufferedImage currentImage){
		this.x = x;
		this.y = y;
		this.currentImage = currentImage;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getCurrentImage() {
		return currentImage;
	}

	public void setCurrentImage(BufferedImage currentImage) {
		this.currentImage = currentImage;
	}
}
