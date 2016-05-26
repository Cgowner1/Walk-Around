
public class Obstacle {
	private int width;
	private int length;
	private int Y;
	private int X;
	private int id;
	
	public Obstacle(){
		
	}
	
	public Obstacle(int lon, int lat, int id){
		this.Y = lat;
		this.X = lon;
		this.id = id;
	}
	
	public Obstacle(int lon, int lat, int width, int length, int id){
		this.Y = lat;
		this.X = lon;
		this.width = width;
		this.length = length;
		this.id = id;
	}
	
	
	public int getY(){
		return this.Y;
	}
	
	public int getX(){
		return this.X;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setX(int X) {
		this.X = X;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLength(){
		return this.length;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setY(int lat){
		this.Y = lat;
	}
	
	public void getX(int lon){
		this.X = lon;
	}
	
	public void getId(int id){
		this.id = id;
	}
}
