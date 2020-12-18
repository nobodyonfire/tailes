package java2dgame;


import Level.Level;
import entities.Player;
import gfx.Font;
import gfx.Screen;
import gfx.SpriteSheet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;



public class Game extends Canvas implements Runnable {
 
	private static final long serialVersionUID = 1L;
 
	public static final int WIDTH = 600;
	public static final int HEIGHT = WIDTH / 12 * 7;
	public static final int SCALE = 3;
	public static final String NAME = "Game";
 
	private JFrame frame;
 
	public boolean running = false;
	public int tickCount = 0;
 
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[256*256*256];
 
	private Screen screen;
	public InputHandler input;
        public Player player;
	public Level level;
 
	public Game() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
 
		frame = new JFrame(NAME);
 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
 
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
 
	public void init() {
		int index = 0;
		for (int r = 0; r < 256; r++) {
			for (int g = 0; g < 256; g++) {
				for (int b = 0; b < 256; b++) {
					colours[index++] = r << 16 | g << 8 | b;
				}
			}
		}
 
		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet64.png"));
		input = new InputHandler(this);
		level = new Level("/Level/level1.png");
                player = new Player(level,300,300,input,2);
                level.addEntity(player);
	} 
 
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
 
	public synchronized void stop() {
		running = false;
	}
 
	public void run() {
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		double nsPerTick = 1000000000D / 60D;
		double delta = 0;
		int ticks = 0;
		int frames = 0;
 
		init();
 
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
 
			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				render();
			}
 
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks , " + frames
						+ " frames per second");
				frames = 0;
				ticks = 0;
			}
		}
	}
 
	public void tick() {
		tickCount++;
		level.tick();
	}
 
	public void render() {
            
            
            int blue;
            int green;
            int red;
            int alpha;
            BufferStrategy bs = getBufferStrategy();
            if (bs == null) {
                    createBufferStrategy(3);
                    return;
            }

            int xOffset = player.x - (screen.width / 2);
            int yOffset = player.y - (screen.height / 2);

            level.renderTiles(screen, xOffset, yOffset);



            level.renderEntities(screen);

            for (int y = 0; y < screen.height; y++) {
                    for (int x = 0; x < screen.width; x++) {
                            int ColourCode = screen.pixels[x + y * screen.width];
                            if (ColourCode < 256*256*256) {


                            red = ColourCode & 0xff;
                            green = (ColourCode & 0xff00) >> 8;
                            blue = (ColourCode & 0xff0000) >> 16;
                            alpha = (byte)256;            
                                
                            pixels[x + y * WIDTH] = ( alpha & 0xff) << 24 | (blue & 0xff) << 16 | (green & 0xff) << 8 | (red & 0xff);
                              
                            }
                    }
            }

            Graphics g = bs.getDrawGraphics();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            g.dispose();
            bs.show();
	}
 
	public static void main(String[] args) {
		new Game().start();
	}
 
}
