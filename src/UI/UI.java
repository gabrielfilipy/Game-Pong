package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game;

public class UI {

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.setFont(new Font("arial",Font.BOLD,15));
		g.drawString("Score Enemy: " + (int)Game.scoreEnemy,20,30);
		g.drawString("Score Player: " + (int)Game.scorePlayer,330,330);
	}
	
}
