import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Color;

public class GameWindow extends JPanel implements MouseListener {
	public int width = 600;
	public int length = 600;
	PointerInfo info;
	private int cursorX;
	private int cursorY;
	private Point cursorPoint;
	private int obsWidth = 25;
	private int obsLength = 25;
	private boolean isRunning = true;
	private Timer timer;
	private Random rand = new Random();
	private int oCount = 0;
	private ArrayList oArray;
	private Obstacle[] obstacles;
	int changex;
	int changey;
	private int points = 0;
	private int pointsAdd = 1;
	private boolean gameOver = false;
	private int speed = 5;
	Rectangle rect;
	private int targetNum = 0;
	private int counterNum = 0;

	public GameWindow() {
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(width, length);
		this.addMouseListener(this);
		targetNum = rand.nextInt(30);
	}
	
	public void fillObstacleArray(int amount){
		oArray = new ArrayList(amount);
		for(int i = 0; i < amount; i++){
			oArray.add(i, new Obstacle(rand.nextInt(575), 0, obsWidth, obsLength, i));
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(cursorX, cursorY, 1, 1);
		g2d.setColor(Color.red);
		//g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
		g2d.setColor(Color.black);
		for(int i = 0; i < oArray.size(); i++){
			Obstacle current = (Obstacle) oArray.get(i);
			g2d.fillRect(current.getX(), current.getY(), current.getWidth(), current.getLength());
			//g2d.drawLine(10, current.getY(), 300, current.getY());
			//g2d.drawLine(10, current.getY() + 25, 300, current.getY() + 25);
		}
		g2d.drawString(Integer.toString(points), 500, 20);
	}

	public void doGameUpdates() {
		counterNum += 1;
		rand = new Random();
		if(gameOver == false){
			points += 1;
		}
		if(counterNum == targetNum && gameOver == false){
			counterNum = 0;
			targetNum = rand.nextInt(30);
			oArray.add(new Obstacle(rand.nextInt(width), 0, obsWidth, obsLength, 8));
		}
		info = MouseInfo.getPointerInfo();
		Point point = info.getLocation();
		SwingUtilities.convertPointFromScreen(point, this);
		cursorX = (int) point.getX();
		cursorY = (int) point.getY();
		cursorPoint = new Point(cursorX, cursorY);
		for(int i = 0; i < oArray.size(); i++){
			Obstacle current = (Obstacle) oArray.get(i);
			current.setY(current.getY() + speed);
			//current.setX(current.getX() + speed);
			rect = new Rectangle(current.getX(), current.getY(), current.getWidth(), current.getLength());
			if(rect.contains(cursorX, cursorY)){
				gameOver = true;
				speed = 0;
			}
			if(current.getY() == length - 10){
				current.setY(0);
				current.setX(rand.nextInt(width - obsWidth));
			}
		}
	}

	public void render() {
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
	public void mouseClicked(MouseEvent e) {
		
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
