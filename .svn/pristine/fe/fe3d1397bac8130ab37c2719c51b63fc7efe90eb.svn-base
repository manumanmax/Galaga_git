package view;

import java.awt.Graphics;
import java.awt.Image;

public class Sprite {
	private Image image;
	
	public Sprite(Image image) {
		this.image = image;
	}
	
	public void draw(Graphics g, double x, double y){
		// TODO resize image
		// https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html
		g.drawImage(image, ScreenManager.getInstance().getAbsoluteX(x),
						   ScreenManager.getInstance().getAbsoluteY(y),
						   ScreenManager.getInstance().getAbsoluteX(image.getWidth(null)),
						   ScreenManager.getInstance().getAbsoluteY(image.getHeight(null)),
						   null);
	}
	public void drawCentered(Graphics g, double x, double y){
		draw(g, x-image.getWidth(null)/2, y-image.getHeight(null)/2);
	}

}
