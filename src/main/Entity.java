package main;

import java.awt.Color;
import java.awt.Graphics;

public class Entity {

	public double x, y;
	public int width, height;
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.BLUE);
		g.fillRect((int) x, (int) y, width, height);
	}
	
}
