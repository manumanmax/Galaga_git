
import java.awt.event.KeyEvent;

import key.KeyManager;
import key.KeyObserver;
import logic.LogicManager;
import status.*;
import view.*;

/**
 * The main class, handles events related to the application status. Does not handle the game logic
 * <p>
 * [singleton]
 * <p>
 */
public class Game implements ClosedListener, KeyObserver {
	private static Game instance = null;

	public final static Game getInstance() {
		if (Game.instance == null) {
			synchronized (Game.class) {
				if (Game.instance == null) {
					Game.instance = new Game();
				}
			}
		}
		return Game.instance;
	}

	/**
	 * Main game configuration
	 */
	private static final int SCREEN_WIDTH = 800;

	private static final int SCREEN_HEIGHT = 600;

	private static final String WINDOW_NAME = "Galaga IDS";

	private static final int FRAMERATE_LIMIT = 50;

	/**
	 * Calculated utility values
	 */
	private static final int MIN_LOOP_DURATION = 1 / FRAMERATE_LIMIT;

	/**
	 * Managing the pause status
	 */
	private Status oldStatus;
	private Status status;
	private boolean pause=false;

	/**
	 * Initializes the game logic and starts the game loop Initializes the status Performs the main game loop relying on
	 * the Status
	 */
	Game() {
		// Initialize the screen and register the observer for window closed event
		ScreenManager.init(SCREEN_WIDTH, SCREEN_HEIGHT, WINDOW_NAME).registerClosedListener(this);
		// Register the observer for pause key
		KeyManager.getInstance().register(this, KeyEvent.VK_P);
		// Set the initial status
		this.status = new DemoStatus();
		//
		LogicManager.getInstance().startGame();
	}

	public void runGameLoop() {
	    Thread loop = new Thread() {
	        public void run() {
	            gameLoop();
	        }
	    };
	    loop.start();
	}
	
	public void gameLoop(){
		long lastLoopTime = System.currentTimeMillis();
		while (!pause) {
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			this.status.doLoop(delta);
			// Apply frame limit
			if (System.currentTimeMillis() - lastLoopTime < MIN_LOOP_DURATION)
				try {
					Thread.sleep(MIN_LOOP_DURATION - (System.currentTimeMillis() - lastLoopTime));
				}
				catch (InterruptedException e) {
			}
		}
	}
	
	@Override
	public void onWindowClosed() {
		System.exit(0);
	}

	public void setStatus(Status status) {
		oldStatus = this.status;
		this.status = status;
	}
	
	public void resetStatus() {
		this.status = oldStatus;
	}

	@Override
	public void onKeyPressed(int k) {
	}

	@Override
	public void onKeyReleased(int k) {
		if(k==KeyEvent.VK_P){
			if(status.getClass()!=PauseStatus.class){
				setStatus(new PauseStatus()); // ?? Empty loop? This is not very efficient
				// Take in consider to implement pause with a boolean value in status class
				pause=true;
				// pause=true; -> Now the thread is stopped and terminated; no empty loops are done.
				// We will take a look on the PauseStatus logic.
				System.out.println("Went pause.");
			} else {
				resetStatus();
				runGameLoop();
				pause=false;
				System.out.println("Reset pause.");
			}
		}
	}

	@Override
	public void onKeyTyped(int k) {
	}

	public static void main(String[] args) {
		Game.getInstance().runGameLoop();
	}

}
