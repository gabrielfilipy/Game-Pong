package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import UI.UI;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = 120;
	private static final int SCALE = 3;
	private boolean isRunnig = true;
	private Thread thread;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	public static JFrame frame;
	public UI ui;
	public static double scoreEnemy = 0;
	public static double scorePlayer = 0;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public Game() { 
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		addKeyListener(this);
		ui = new UI();
		//initFrames();
		player = new Player(114, HEIGHT - 60);
		enemy = new Enemy(60, 0);
		ball = new Ball(100, HEIGHT/2-1);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		frame = new JFrame("Pong");
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	public void update() { 
		player.update();
		enemy.update();
		ball.update();
	}
	
	public void render() { 
		
		JLabel JlPontoPlayer = new JLabel();
		JlPontoPlayer.setText(ball.PONTO_ENIMY+"");
		JlPontoPlayer.setBounds(10, 11, 99, 14);
		frame.getContentPane().add(JlPontoPlayer);
		
		JLabel JlPontoEnemy = new JLabel("Inimigo: " + ball.PONTO_ENIMY);
		JlPontoEnemy.setBounds(400, 330, 68, 14);
		frame.getContentPane().add(JlPontoEnemy);
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(g); 
		enemy.render(g);
		ball.render(g);
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		ui.render(g);
		
		bs.show();
		
	}

	@Override
	public void run() { 
		long lastTime = System.nanoTime();
		double amountOfUpdate = 60.0;
		double ns = 1000000000 / amountOfUpdate;
		float d = 0; 
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunnig) {
			try {
				long now = System.nanoTime();
				d += (now - lastTime) / ns;
				lastTime = now;
				
				if(d >= 1) {
					update();
					render();
					frames++;
					d--;
				}
				
				if((System.currentTimeMillis() - timer) >= 100) {
					System.out.println(">> FPS: " + frames);
					frames = 0;
					timer += 1000;
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunnig = true;
		thread.start();
	}
	
	public void initFrames() {
		JFrame frame = new JFrame("Jogo Pong");
		frame.add(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			player.setRight(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		
	}
	
	public synchronized void stop() {
		isRunnig = false;
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
