package entities;

import Level.Level;

import gfx.Screen;
import java2dgame.InputHandler;



public class Player extends Mob {

    private InputHandler input;
    private int scale= 1;
    protected boolean isSwimming =false;
    private int tickCount=0;
    
    public Player(Level level, int x , int y, InputHandler input){
        super(level,"Player",x,y,2);
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
        
        if(level.getTile(this.x>>5,this.y>>5).getId() ==3){
            isSwimming = true;
        }
        if (isSwimming && level.getTile(this.x>>5,this.y>>5).getId() !=3){
            isSwimming = false;
        }
        
     
        tickCount++;
    }

 
    public void render(Screen screen) {
        int xTile = 0;
        int yTile = 28;
        int walkingSpeed =4;
        int flipTop=(numSteps >> walkingSpeed)& 1;
        int flipBottom=(numSteps >> walkingSpeed)& 1;
        
        if(movingDir ==1) {
            xTile +=2;
        } else if (movingDir >1){
            xTile +=4 +((numSteps >> walkingSpeed)&1) *2;
            flipTop=(movingDir - 1)%2;
            flipBottom=(movingDir - 1)%2;
        }
        
        if (!isMoving && (movingDir ==2 || movingDir ==3 )){
            xTile =4;
        }
        
        
        
        
        int modifier =32*scale;
        int xOffset = x- modifier/2;
        int yOffset = y- modifier/4-20;
        
        if (isSwimming){
            yOffset+=8;
            if(tickCount %60 <15){
                screen.render(xOffset, yOffset +15, 0 + 27*32, 0x00, 1);
                screen.render(xOffset + 32, yOffset +15, 0 + 27*32, 0x01, 1);
            } else if (15 <= tickCount%60 && tickCount % 60 <30){
                yOffset -=1;
                screen.render(xOffset, yOffset +15, 1 + 27*32, 0x00, 1);
                screen.render(xOffset + 32, yOffset +15, 1 + 27*32, 0x01, 1);
            } else if (30 <= tickCount%60 && tickCount % 60 <45){
                screen.render(xOffset, yOffset +15, 2 + 27*32, 0x00, 1);
                screen.render(xOffset + 32, yOffset +15, 2 + 27*32, 0x01, 1);
            } else {
                yOffset -=1;
                screen.render(xOffset, yOffset +15, 3 + 27*32, 0x00, 1);
                screen.render(xOffset + 32, yOffset +15, 3 + 27*32, 0x01, 1);
            }
            
        }
        
        System.out.println("y" + yOffset);
        screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, flipTop,scale);
        screen.render(xOffset  + modifier -(modifier * flipTop) , yOffset, xTile + 1 + yTile * 32,flipTop,scale);
        
        if(!isSwimming){
            screen.render(xOffset +(modifier * flipBottom), yOffset + modifier , xTile + (yTile+1) * 32,flipBottom,scale);
            screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, xTile + 1 + (yTile+1) * 32,flipBottom,scale);

        }
        
    }
    
      public boolean hasCollided(int xa, int ya) {
          
        int xMin = 8;
        int xMax = 24;
        int yMin = 12;
        int yMax = 34;
        
        for (int x = xMin ; x <xMax ; x++){
            if (isSolidTile(xa,ya,x,yMin)) {
                return true;
            }
        }
        for (int x = xMin ; x <xMax ; x++){
            if (isSolidTile(xa,ya,x,yMax)) {
                return true;
            }
        }
        for (int y = yMin ; y <yMax ; y++){
            if (isSolidTile(xa,ya,xMin,y)) {
                return true;
            }
        }
        for (int y = yMin ; y <yMax ; y++){
            if (isSolidTile(xa,ya,xMax,y)) {
                return true;
            }
        }
        
        
        return false;
    }
    
}
