package view;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


/**
 * All sprites should be obtained through this module.
 * Act as a resource manager, caching the images.
 * <p>
 * [singleton]
 * <p>
 */
public class SpriteManager {
	private static volatile SpriteManager instance = null;
	private Map<String, Sprite> sprites = new HashMap<String, Sprite>();

	private SpriteManager(){
		super();
	}

	/**
	 * Returns an instance of the sprite with desired reference
	 * @param reference of the desired sprite
	 * @return instance of the sprite
	 * @throws FileNotFoundException 
	 */
	public Sprite getSprite(String ref) {
		//Check for cache
		if (sprites.get(ref) != null)
			return (Sprite) sprites.get(ref);
		// If cache miss load new
		BufferedImage sourceImage = null;
		try {
			// Check file existence
			URL url = this.getClass().getClassLoader().getResource(ref);
			if (url == null)
				throw new RuntimeException("Can't find ref: "+ref);
			// Read the image
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load: "+ref);
		}
		// Create an accelerated image of the right size to store the sprite in
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
										getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(),sourceImage.getHeight(),Transparency.BITMASK);
		// Draw the image into the accelerated image
		image.getGraphics().drawImage(sourceImage,0,0,null);
		// Finally create the sprite, add it the cache then return it
		Sprite sprite = new Sprite(image);
		sprites.put(ref,sprite);
		return sprite;
	}
	
	/**
	 * Singleton method, gets the instance.
	 * @return Unique instance of the object
	 */
	public final static SpriteManager getInstance(){
		if (SpriteManager.instance == null) {
			// per principe I define the class synchronised because it avoids corruption in threads
			// even if we don't use it is good to remember about that
            synchronized(SpriteManager.class) {
              if (SpriteManager.instance == null) {
            	  SpriteManager.instance = new SpriteManager();
              }
            }
         }
		
		return SpriteManager.instance;
	}
	
	// TODO getSprite logic
	
}
