import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
	
	public static void main(String[] args){
		GameFrame window = new GameFrame();
		GameWindow game = new GameWindow();
		window.add(game);
		window.setVisible(true);
		game.gameLoop();
	}
	public void createWindow(){
		GameWindow window = new GameWindow();
		window.setVisible(true);
		
	}
}
