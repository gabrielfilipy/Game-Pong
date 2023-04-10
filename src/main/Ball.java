package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JOptionPane;

public class Ball {

	public static int PONTO_PLAYER = 0;
	public static int PONTO_ENIMY = 0;
	
	public double x, y;
	public int width, height;
	public double directX, directY;
	public double speed = 1.8;
	
	public Ball() { }
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		int angulo = new Random().nextInt(160);
		this.directX = Math.cos(Math.toRadians(angulo));
		this.directY = Math.sin(Math.toRadians(angulo));
	}
	
	public void update() {
		
		//VERIFICANDO COLISÃO (LIMITAÇÕES).
		if(x+(directX * speed) + width >= Game.WIDTH) {
			directX *=-1;
		} else if(x + (directX * speed) < 0) {
			directX *=-1;
		}
		
		if(y >= Game.HEIGHT) {
			Game.scoreEnemy++;
			new Game();
		} else if(y < 0) {
			Game.scorePlayer++;
			new Game();
		}
		
		//CLASSE QUE PERMITE TESTES DE COLISÕES.
		Rectangle rec = new Rectangle((int) (x+(directX*speed)), (int) (y+(directY*speed)),  width, height);
		Rectangle recPlayer = new Rectangle(Game.player.getX(), Game.player.getY(), Game.player.getWidth(), Game.player.getHeight());
		Rectangle recEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width, Game.enemy.height);

		if(rec.intersects(recPlayer)) {
			
			int angulo = new Random().nextInt(160);
			this.directX = Math.cos(Math.toRadians(angulo));
			this.directY = Math.sin(Math.toRadians(angulo));
			
			if(this.directY > 0)
				directY*=-1;
			
		} else if(rec.intersects(recEnemy)) {
			int angulo = new Random().nextInt(160);
			this.directX = Math.cos(Math.toRadians(angulo));
			this.directY = Math.sin(Math.toRadians(angulo));
			
			if(this.directY < 0)
				directY*=-1;
		}
		
		x+=directX*speed;
		y+=directY*speed;
	}
	
	public void render(Graphics g) { 
		g.setColor(Color.BLUE);
		g.fillRect((int) x, (int) y, width, height);
	}
	
}
