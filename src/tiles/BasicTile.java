package tiles;

import Level.Level;
import gfx.Screen;

public class BasicTile extends Tile {
 
	protected int tileId;
 
	public BasicTile(int id, int x, int y,int levelColour) {
		super(id, false, false, levelColour);
		this.tileId = x + y * 32;
	}
        
        public void tick(){
    
        }
 
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, tileId,0x00,1);
	}
 
}