import javax.swing.JFrame;

public class GameFrame extends JFrame{
	public int width = 1280;
	public int length = 720;
	
	public GameFrame(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(width, length);
	}
	
}
