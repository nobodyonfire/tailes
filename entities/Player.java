package entities;

import Level.Level;
import gfx.Colours;
import gfx.Screen;
import java2dgame.InputHandler;



public class Player extends Mob {

    private InputHandler input;
    private int colour = Colours.get(-1,111,145,543);
    
    public Player(Level level, int x , int y, InputHandler input){
        super(level,"Player",x,y,1);
        this.input=input;
        
    }

    public void tick() {
        int xa=0;
        int ya=0;
        
        if (input.up.isPressed()) {
                ya -= 1;
        }
        if (input.down.isPressed()) {
                ya += 1;
        }
        if (input.left.isPressed()) {
                xa -= 1;
        }
        if (input.right.isPressed()) {
                xa += 1;
        }
        
        if (xa !=0 || ya !=0){
            move(xa,ya);
            isMoving =true; 
        } else {
            isMoving =false;
        }
    }

 
    public void render(Screen screen) {
        int xTile = 0;
        int yTile = 28;
        
        int modifier = 8*scale;
        int xOffset = x- modifier/2;
        int yOffset = y- modifier/4-4;
        
        screen.render(xOffset, yOffset, xTile + yTile * 32,colour);
        screen.render(xOffset  + modifier , yOffset, xTile + 1 + yTile * 32,colour);
        screen.render(xOffset , yOffset + modifier , xTile + (yTile+1) * 32,colour);
        screen.render(xOffset + modifier, yOffset + modifier, xTile + 1 + (yTile+1) * 32,colour);
        
    }
    
      public boolean hasCollided(int xa, int ya) {
        return false;
    }
    
}
