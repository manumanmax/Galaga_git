package key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import view.ScreenManager;


/**
 * Notifies observers when a keyboard event occurs
 * <p>
 * [singleton]
 * <p>
 */
public class KeyManager extends KeyAdapter {
	private static final int NUM_KEYS = 256;

	private static KeyManager instance = null;

	private List<ArrayList<KeyObserver>> observers;
	
	private KeyManager()  {
		super();
		observers=new ArrayList<ArrayList<KeyObserver>>(NUM_KEYS);
		for(int i=0; i<NUM_KEYS; i++){
			observers.add(null);
		}
	}

	/**
	 * Singleton method, gets the instance.
	 * Binds the instance to get the key events
	 * @return Unique instance of the object
	 */
	public final static KeyManager getInstance(){
		if (KeyManager.instance == null) {
            synchronized(KeyManager.class) {
              if (KeyManager.instance == null) {
            	  KeyManager.instance = new KeyManager();
            	  ScreenManager.getInstance().addKeyListener(instance);
              }
            }
         }
		return KeyManager.instance;
	}
	/**
	 * Register a new key observer
	 * @param o the observer
	 * @param e the event to be notified of
	 */
	public void register(KeyObserver o, int e){
		if(observers.get(e) == null)
			observers.add(e,new ArrayList<KeyObserver>());
		observers.get(e).add(o);
	}
	public void remove(KeyObserver o, int e){
		if(observers.get(e) != null)
			observers.get(e).remove(o);
	}

	/**
	 * Called on key press, notifies the observers for the key
	 * @param e the key event
	 */
	public void keyPressed(KeyEvent e) {
		if(observers.get(e.getKeyCode())!=null)
			for(KeyObserver o : observers.get(e.getKeyCode())){
				o.onKeyPressed(e.getKeyCode());
			}
	}
	/**
	 * Called on key release, notifies the observers for the key
	 * @param e the key event
	 */
	public void keyReleased(KeyEvent e) {
		if(observers.get(e.getKeyCode())!=null)
			for(KeyObserver o : observers.get(e.getKeyCode())){
				o.onKeyReleased(e.getKeyCode());
			}
	}
	/**
	 * Called on key press and release, notifies the observers for the key
	 * @param e the key event
	 */
	public void keyTyped(KeyEvent e) {
		if(observers.get(e.getKeyCode())!=null)
			for(KeyObserver o : observers.get(e.getKeyCode())){
				o.onKeyTyped(e.getKeyCode());
			}
	}
	
}
