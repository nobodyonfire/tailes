package tiles;

import Level.Level;
import gfx.Screen;


public class AnimatedSolidTile extends BasicTile {

    private int[][] animationTileCoords;
    private int currentAnimationIndex;
    private long lastIterationTime;
    private int animationSwitchDelay;

    public AnimatedSolidTile(int id, int[][] animationCoords, int levelColour, int animationSwitchDelay,boolean emitter,int solidType) {
        super(id, animationCoords[0][0], animationCoords[0][1], levelColour,emitter);
        this.animationTileCoords = animationCoords;
        this.currentAnimationIndex = 0;
        this.lastIterationTime = System.currentTimeMillis();
        this.animationSwitchDelay = animationSwitchDelay;
        this.solid= true;
        this.solidType = solidType; 
    }

    
    public void tick() {
        if ((System.currentTimeMillis() - lastIterationTime) >= (animationSwitchDelay)) {
            
            lastIterationTime = System.currentTimeMillis();
            currentAnimationIndex = (currentAnimationIndex + 1) % animationTileCoords.length;
            this.tileId = (animationTileCoords[currentAnimationIndex][0] + (animationTileCoords[currentAnimationIndex][1] * 32));

          
        }
    }
}
