import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JPanel implements KeyListener {
	private Timer timer;
	private boolean isRunning = true;
	private BufferedImage[][] walkFrames; //first array is down, second left, third is up, fourth is right 
	private Character player;
	Scanner input = new Scanner(System.in);
	private char currentChar;
	
	public GameWindow(){
		//System.out.println(input.next());
		this.setFocusable(true);
		addKeyListener(this);
		walkFrames = new BufferedImage[4][4];
		File[] imageFiles = {
			new File("/Users/IvanWood/Desktop/Down.png"),
			new File("/Users/IvanWood/Desktop/DownL.png"),
			new File("/Users/IvanWood/Desktop/Down.png"),
			new File("/Users/IvanWood/Desktop/DownR.png"), //end of down
			new File("/Users/IvanWood/Desktop/Down.png"),
			new File("/Users/IvanWood/Desktop/DownL.png"),
			new File("/Users/IvanWood/Desktop/Down.png"),
			new File("/Users/IvanWood/Desktop/DownR.png"), //end of left
			new File("/Users/IvanWood/Desktop/Down.png"),
			new File("/Users/IvanWood/Desktop/DownL.png"),
			new File("/Users/IvanWood/Desktop/Down.png"),
			new File("/Users/IvanWood/Desktop/DownR.png"), //end of up
			new File("/Users/IvanWood/Desktop/Down.png"),
			new File("/Users/IvanWood/Desktop/DownL.png"),
			new File("/Users/IvanWood/Desktop/Down.png"),
			new File("/Users/IvanWood/Desktop/DownR.png") //end of right
		};
		int y = 0;
		for(int i = 0; i < 4; i++){
			for(int x = 0; x < 4; x++)
			if(imageFiles[y] != null){
				try {
					walkFrames[i][x] = ImageIO.read(imageFiles[y]);
					y++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		player = new Character(100, 100, walkFrames[0][0]);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(player.getCurrentImage(), null, player.getX(), player.getY());
	}
	
	public void doGameUpdates() {
		//player.setY(player.getY() + 3);
		//System.out.println(input.next());
		
		if(currentChar == 's'){
			player.setY(player.getY() + 3);
		}
		if(currentChar == 'a'){
			player.setX(player.getX() - 3);
		}
		if(currentChar == 'w'){
			player.setY(player.getY() - 3);
		}
		if(currentChar == 'd'){
			player.setX(player.getX() + 3);
		}
	}
	
	private void render() {
		repaint();
	}
	
	public void gameLoop() {
		timer = new Timer();
		timer.schedule(new LoopTask(), 0, 1000 / 60);
	}

	private class LoopTask extends TimerTask {
		public void run() {
			if (isRunning) {
				doGameUpdates();
				render();
			} else {
				timer.cancel();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//player.setY(player.getY() + 3);
		if(e.getKeyChar() == 's'){
			//player.setY(player.getY() + 3);
			currentChar = 's';
		}
		if(e.getKeyChar() == 'a'){
			//player.setX(player.getX() - 3);
			currentChar = 'a';
		}
		if(e.getKeyChar() == 'w'){
			//player.setY(player.getY() - 3);
			currentChar = 'w';
		}
		if(e.getKeyChar() == 'd'){
			//player.setX(player.getX() + 3);
			currentChar = 'd';
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		currentChar = ' ';
	}
}
