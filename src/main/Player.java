package main;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	private boolean right;
	private boolean left;
	private int y;
	private int x;
	private int width;
	private int height;
	
	public Player(int y, int x) {
		this.y = y;
		this.x = x;
		this.width = 40;
		this.height = 5;
	}
	
	public void update() {
		if(right) {
			x++;
		}
		else if(left) {
			x--;
		}
		
		if((x + width) > Game.WIDTH) {
			x = Game.WIDTH - width;
		}
		
		else if(x < 0) {
			x = 0;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	} 
	
	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
