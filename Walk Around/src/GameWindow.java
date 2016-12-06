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
	private char currentDirection;
	private Timer animationTimer;
	private int frameCounter;
	private int currentFrame;
	private boolean changeDirection = false;
	private byte recentAxis; // 1 is x axis, 2 is y axis
	private BufferedImage[] currentImageArray;
	private int followerTargetX;
	private int followerTargetY;
	private boolean followerAtTarget;
	private Character follower;
	private boolean w;
	private boolean a;
	private boolean s;
	private boolean d;
	
	
	
	public GameWindow(){
		//System.out.println(input.next());
		this.setFocusable(true);
		addKeyListener(this);
		walkFrames = new BufferedImage[4][4];
		currentImageArray = walkFrames[0];
		File[] imageFiles = {
			new File("/Users/IvanWood/Desktop/Down.png"),
			new File("/Users/IvanWood/Desktop/DownL.png"),
			new File("/Users/IvanWood/Desktop/Down.png"),
			new File("/Users/IvanWood/Desktop/DownR.png"), //end of down
			new File("/Users/IvanWood/Desktop/Left.png"),
			new File("/Users/IvanWood/Desktop/LeftL.png"),
			new File("/Users/IvanWood/Desktop/Left.png"),
			new File("/Users/IvanWood/Desktop/LeftR.png"), //end of left
			new File("/Users/IvanWood/Desktop/Up.png"),
			new File("/Users/IvanWood/Desktop/UpL.png"),
			new File("/Users/IvanWood/Desktop/Up.png"),
			new File("/Users/IvanWood/Desktop/UpR.png"), //end of up
			new File("/Users/IvanWood/Desktop/Right.png"),
			new File("/Users/IvanWood/Desktop/RightL.png"),
			new File("/Users/IvanWood/Desktop/Right.png"),
			new File("/Users/IvanWood/Desktop/RightR.png") //end of right
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
		follower = new Character(50, 50);
		followerTargetX = 300;
		followerTargetX = 300;
		currentFrame = 0;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(player.getCurrentImage(), null, player.getX(), player.getY());
		g2d.drawRect(follower.getX(), follower.getY(), 20, 20);
		g2d.drawString("w   " + w, 500, 100);
		g2d.drawString("s   " + s, 500, 200);
		g2d.drawString("d   " + d, 500, 300);
		g2d.drawString("a   " + a, 500, 400);
		g2d.drawString("" + frameCounter, 1000, 100);
	}
	
	public void doGameUpdates() {
		
		
		
		//************************PLAYER CONTROL**************************
		
		frameCounter++;
		
		//y axis movement
		if(w){
			player.setVelY(-3);
		}
		else if(s){
			player.setVelY(3);
		}
		else{
			player.setVelY(0);
		}
		
		//x axis movement
		if(d){
			player.setVelX(3);
		}
		else if(a){
			player.setVelX(-3);
		}
		else{
			player.setVelX(0);
		}
		
		
		
		
		if(player.getVelX() == 0 && player.getVelY() == 0){
			player.setCurrentImage(currentImageArray[0]);
		}
	
		if(frameCounter == 60 || changeDirection == true){
			frameCounter = 0;
			currentFrame = 2;
		}
		
		player.setX(player.getX() + player.getVelX());
		player.setY(player.getY() + player.getVelY());
		
		if(frameCounter % 15 == 0 && (player.velX != 0 || player.velY != 0) || changeDirection == true){
			currentFrame = changeFrame(currentFrame);
			changeDirection = false;
			if(currentDirection == 's'){
				player.setCurrentImage(walkFrames[0][currentFrame]);
				currentImageArray = walkFrames[0];
			} else if(currentDirection == 'a'){
				player.setCurrentImage(walkFrames[1][currentFrame]);
				currentImageArray = walkFrames[1];
			} else if(currentDirection == 'w'){
				player.setCurrentImage(walkFrames[2][currentFrame]);
				currentImageArray = walkFrames[2];
			} else if(currentDirection == 'd'){
				player.setCurrentImage(walkFrames[3][currentFrame]);
				currentImageArray = walkFrames[3];
			}
		}
		
		//************************END OF PLAYER CONTROL*************************
		
		if(frameCounter % 15 == 0){
			
			followerTargetX = player.getX();
			followerTargetY = player.getY();
		}
		if(follower.getX() == followerTargetX && follower.getY() == followerTargetY){
			followerAtTarget = true;
		}
		else{
			followerAtTarget = false;
		}
		
		if(!followerAtTarget){
			int xdiff = Math.abs(followerTargetX - follower.getX());
			int ydiff = Math.abs(followerTargetY - follower.getY());
			System.out.println(ydiff);
			int changex = 1;
			int changey = 1;
			if(xdiff > ydiff){
				changex = largestDiv(xdiff);
				changey = (ydiff)/(xdiff/changex);
						//largestDiv(ydiff);
			}
			if(xdiff <= ydiff){
				changey = largestDiv(ydiff);
				changex = (xdiff)/(ydiff/changey);
			}
			//end test
			if(follower.getX() > followerTargetX){
				follower.x -= changex;
			}
			if(follower.getX() < followerTargetX){
				follower.x += changex;
			}
			if(follower.getY() > followerTargetY){
				follower.y -= changey;
			}
			if(follower.getY() < followerTargetY){
				follower.y += changey;
			}
		}
		
		//***********************FOLLOWER PATH FINDER*****************************

	}
	
	private void render() {
		repaint();
	}
	
	private int changeFrame(int current){
		if(player.getVelX() > 0 && player.getVelY() == 0){
			currentDirection = 'd';
		}
		if(player.getVelX() < 0 && player.getVelY() == 0){
			currentDirection = 'a';
		}
		if(player.getVelY() > 0 && player.getVelX() == 0){
			currentDirection = 's';
		}
		if(player.getVelY() < 0 && player.getVelX() == 0){
			currentDirection = 'w';
		}
		
		if(changeDirection == true){
			return 1;
		}
		if (current < 3){
			return current + 1;
		}
		else{
			return 0;
		}
		
	}
	
	public int largestDiv(int num){
		for(int i = 4; i > 1; i--){
			if(num % i == 0){
				return i;
			}
		}
		return 1;
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
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_W){
				w = true;
				
				
//				player.setVelY(player.getVelY() - 3);
				currentDirection = 'w';
				changeDirection = true;
				recentAxis = 2;
				currentImageArray = walkFrames[2];
				
			} else if(key == KeyEvent.VK_S){
				s = true;
//				player.setVelY(player.getVelY() + 3);
				currentDirection = 's';
				changeDirection = true;
				recentAxis = 2;
				currentImageArray = walkFrames[0];
			} else if(key == KeyEvent.VK_A){
				a = true;
//				player.setVelX(player.getVelX() - 3);
				currentDirection = 'a';
				changeDirection = true;
				recentAxis = 1;
				currentImageArray = walkFrames[1];
			} else if(key == KeyEvent.VK_D){
				d = true;
//				player.setVelX(player.getVelX() + 3);
				currentDirection = 'd';
				changeDirection = true;
				recentAxis = 1;
				currentImageArray = walkFrames[3];
			}
			
			
			
	/*
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
	*/
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W){
			w = false;
			//player.setVelY(player.getVelY() + 3);
		} else if(key == KeyEvent.VK_S){
			s = false;
			//player.setVelY(player.getVelY() - 3);
		} else if(key == KeyEvent.VK_A){
			a = false;
			//player.setVelX(player.getVelX() + 3);
		} else if(key == KeyEvent.VK_D){
			d = false;
			//player.setVelX(player.getVelX() - 3);
		}
	}
}
