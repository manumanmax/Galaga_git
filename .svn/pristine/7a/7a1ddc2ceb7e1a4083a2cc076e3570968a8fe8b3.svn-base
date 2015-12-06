package logic;

import java.util.ArrayList;

import entities.*;

public class LogicManager {

	private static final int CENTER_SCREEN_X = 400;
	private static final int CENTER_SCREEN_Y = 300;
	private static LogicManager instance = null;
	
	public final static LogicManager getInstance(){
		if (LogicManager.instance == null) {
            synchronized(LogicManager.class) {
              if (LogicManager.instance == null) {
            	  LogicManager.instance = new LogicManager();
              }
            }
         }	
		return LogicManager.instance;
	}

	private LogicManager() {
	}

	private ArrayList<Entity> objects=new ArrayList<Entity>();

	/**
	 * Register a new drawable object to be drawn by the update function
	 */
	public void register(Entity o){
		objects.add(o);
	}
	public void remove(Entity o){
		objects.remove(o);
	}

	/**
	 * Iterates all the registered entities and makes them do their logic 
	 */
	public void doLogic(long delta) {
		for (Entity o : objects) {
			o.move(delta, playerShip.getSpeedX(), playerShip.getSpeedY());
			o.doLogic();
		}
	}
	
	/**
	 * 
	 */
	private boolean gameStarted=false;
	private PlayerShip playerShip;
	
	public void startGame(){
		new Background();
		playerShip=new PlayerShip(CENTER_SCREEN_X, CENTER_SCREEN_Y);
		gameStarted=true;
	}
}
