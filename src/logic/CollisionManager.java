package logic;

import java.util.ArrayList;

import entities.Entity;
import view.Drawable;

public class CollisionManager {

	private static CollisionManager instance = null;
	
	public final static CollisionManager getInstance(){
		if (CollisionManager.instance == null) {
            synchronized(CollisionManager.class) {
              if (CollisionManager.instance == null) {
            	  CollisionManager.instance = new CollisionManager();
              }
            }
         }	
		return CollisionManager.instance;
	}

	private ArrayList<Entity> objects=new ArrayList<Entity>();
	
	private CollisionManager() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Iterates all the registered entities and makes them do their logic 
	 */
	public void doCollisions() {
		// TODO Auto-generated method stub	
	}
	/**
	 * Register a new drawable object to be drawn by the update function
	 */
	public void register(Entity o){
		objects.add(o);
	}
	public void remove(Drawable o){
		objects.remove(o);
	}
	
}
