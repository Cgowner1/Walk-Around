import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class GameWindow extends JPanel implements MouseListener {
	public int width = 600;
	public int length = 600;
	private int squareSize = 50;
	private boolean isRunning = true;
	private Timer timer;
	private int sqLon = 100;
	private int sqLat = 300;
	private Random rand = new Random();
	private int cLon = rand.nextInt(width - 25);
	private int cLat = rand.nextInt(length - 25);
	
	public GameWindow(){
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(width, length);
		this.addMouseListener(this);
	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(sqLon, sqLat, squareSize, squareSize);
		g2d.fillOval(cLon, cLat, 25, 25);
	}
	
	public int largestDiv(int num){
		for(int i = 10; i > 1; i--){
			if(num % i == 0){
				return i;
			}
		}
		return 1;
	}
	
	public void doGameUpdates(){
		//test
		int xdiff = Math.abs(cLon - sqLon);
		int ydiff = Math.abs(cLat - sqLat);
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
		if(sqLon > cLon){
			sqLon -= changex;
		}
		if(sqLon < cLon){
			sqLon += changex;
		}
		if(sqLat > cLat){
			sqLat -= changey;
		}
		if(sqLat < cLat){
			sqLat += changey;
		}
		if(sqLon == cLon && sqLat == cLat){
			cLon = rand.nextInt(width - 25);
			cLat = rand.nextInt(length - 25);
		}
	}
	
	public void render(){
		repaint();
	}
	
	public void gameLoop(){
		timer = new Timer();
		timer.schedule(new LoopTask(), 0, 1000 / 500);
	}
	
	private class LoopTask extends TimerTask {
		public void run(){
			if(isRunning){
				doGameUpdates();
				render();
			}
			else{
				timer.cancel();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
