package entities;
import java.awt.Graphics;

import logic.CollisionManager;
import logic.LogicManager;
import view.Drawable;
import view.ScreenManager;
import view.Sprite;
import view.SpriteManager;

public class Entity implements Drawable {
	/**
	 * The sprite that represents the graphics of this entity
	 */
	private Sprite sprite;
	/**
	 * The position of this entity on the screen (apparent position)
	 */
	private double x;
	private double y;
	/**
	 * The velocity of this entity on the screen (apparent velocity)
	 */
	protected double speedX=0;
	protected double speedY=0;
	
	private boolean pivotCenter;
	/**
	 * Construct a entity based on a sprite image and a location.
	 * 
	 * @param ref The reference to the image to be displayed for this entity
 	 * @param x The initial x location of this entity
	 * @param y The initial y location of this entity
	 */
	public Entity(String ref,int x,int y) {
		this(ref, x, y, false);
	}
	public Entity(String ref,int x,int y, boolean center) {
		this.pivotCenter=center;
		this.sprite = SpriteManager.getInstance().getSprite(ref);
		this.x = x;
		this.y = y;
		ScreenManager.getInstance().register(this);
		LogicManager.getInstance().register(this);
		CollisionManager.getInstance().register(this);
	}
	
	@Override
	public void draw(Graphics g) {
		if(pivotCenter)
			sprite.drawCentered(g, x, y);
		else
			sprite.draw(g, x, y);
	}

	public void move(long delta, double screenSpeedX, double screenSpeedY) {
		x+=(delta * (speedX-screenSpeedX)) / 1000;
		y+=(delta * (speedY-screenSpeedY)) / 1000;
	}
	
	public void doLogic(){
		// Default logic: no logic
	}
	public void kill(){
		CollisionManager.getInstance().remove(this);
		LogicManager.getInstance().remove(this);
		ScreenManager.getInstance().remove(this);
	}
}
