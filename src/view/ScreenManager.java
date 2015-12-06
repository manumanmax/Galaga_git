package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScreenManager extends Canvas{

	private static ScreenManager instance = null;
	
	public final static ScreenManager getInstance(){
		if (ScreenManager.instance == null) {
            synchronized(ScreenManager.class) {
              if (ScreenManager.instance == null) {
            	  ScreenManager.instance = new ScreenManager();
              }
            }
         }	
		return ScreenManager.instance;
	}
	
	/**
	 * Reference values for absolute calculation
	 */
	private static final double REFERENCE_SCREEN_WIDTH=800;
	private static final double REFERENCE_SCREEN_HEIGHT=600;
	/**
	 * Default values
	 */
	private static final int DEFAULT_SCREEN_WIDTH = 800;
	private static final int DEFAULT_SCREEN_HEIGHT = 600;
	private static final String DEFAULT_WINDOW_NAME = "Galaga IDS";
	
	/**
	 * 
	 */
	private int screenWidth;
	private int screenHeight;
	/**
	 * 
	 */
	private BufferStrategy buffer;
	JFrame container;
	/**
	 * 
	 */
	private List<Drawable> objects=new ArrayList<Drawable>();
	/**
	 * Initializes the ScreenManager with preferred screen width/height and window name parameters
	 * If the ScreenManager is already initialized, it returns the already existing instance
	 */
	public static ScreenManager init(int screenWidth, int screenHeight, String windowName){
		if (ScreenManager.instance == null) {
			ScreenManager.instance = new ScreenManager(screenWidth, screenHeight, windowName);
		}
		return ScreenManager.instance;
	}
	
	private ScreenManager(){
		this(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT, DEFAULT_WINDOW_NAME);
	}
	
	private ScreenManager(int screenWidth, int screenHeight, String windowName) {
		this.screenWidth=screenWidth;
		this.screenHeight=screenHeight;
		// Initialize window
		container = new JFrame(windowName);
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(screenWidth,screenHeight));
		panel.setLayout(null);
		
		setBounds(0,0,screenWidth,screenHeight);
		panel.add(this);
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		requestFocus();
		// Initialize buffer strategy
		createBufferStrategy(2);
		buffer = getBufferStrategy();
		// Draw the background
		buffer.show();
	}
	public void registerClosedListener(final ClosedListener c){
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				c.onWindowClosed();
			}
		});
	}
	
	public void update(long delta){
		Graphics2D drawingCanvas = (Graphics2D) buffer.getDrawGraphics();
		for (Drawable o : objects) {
			o.draw(drawingCanvas);
		}
		drawingCanvas.dispose();
		buffer.show();
	}
	/**
	 * Register a new drawable object to be drawn by the update function
	 */
	public void register(Drawable o){
		objects.add(o);
	}
	public void remove(Drawable o){
		objects.remove(o);
	}

	public int getAbsoluteX(double x){
		return (int)(x*screenWidth/REFERENCE_SCREEN_WIDTH);
	}
	public int getAbsoluteY(double y){
		return (int)(y*screenHeight/REFERENCE_SCREEN_HEIGHT);
	}
	
}
