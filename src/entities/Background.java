package entities;

import java.awt.Color;
import java.awt.Graphics;

public class Background extends Entity {
	private static final int TOP_LEFT_SCREEN_X = 0;
	private static final int TOP_LEFT_SCREEN_Y = 0;
	private static final int BOTTOM_RIGHT_SCREEN_X = 800;
	private static final int BOTTOM_RIGHT_SCREEN_Y = 600;
	private static final int CENTER_SCREEN_X = 400;
	private static final int CENTER_SCREEN_Y = 300;

	public Background() {
		super("images/stelle.jpg", CENTER_SCREEN_X, CENTER_SCREEN_Y, true);
	}
	
	/*
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(TOP_LEFT_SCREEN_X,TOP_LEFT_SCREEN_Y,BOTTOM_RIGHT_SCREEN_X,BOTTOM_RIGHT_SCREEN_Y);
	}*/
}
