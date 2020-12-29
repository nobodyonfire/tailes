package entities;

import Level.Level;
import gfx.Font;

import gfx.Screen;
import java.util.Random;
import java.util.logging.Logger;
import static java2dgame.Game.sleep;
import java2dgame.InputHandler;


public class Pnj1 extends Mob {

    private int scale= 1;
    protected boolean isSwimming =false;
    private int tickCount=0;
    int random;
    private InputHandler input;
    private boolean isInteracting=false;
 
    
    public Pnj1(Level level,String name, int x , int y, int speed){
        super(level,name,x,y,speed);
        this.pv=500;
    }

    public void tick() {
        int xa=0;
        int ya=0;

        
        if (tickCount%400<200){
            xa=1;
        }else{
            xa=-1;
        }
        
        


        if (xa !=0 || ya !=0){
            move(xa,ya);
            isMoving =true; 
        } else {
            isMoving =false;
        }
        
        
        
        if((level.getTile(this.x+16>>5,this.y+16>>5).getId() ==3) || (level.getTile(this.x+16>>5,this.y+16>>5).getId() <=144 && level.getTile(this.x+16>>5,this.y+16>>5).getId() >=130)){
            isSwimming = true;
        }
     
        if((level.getTile(this.x+16>>5,this.y+16>>5).getId() !=3) && (level.getTile(this.x+16>>5,this.y+16>>5).getId() >144 || level.getTile(this.x+16>>5,this.y+16>>5).getId() <130)){
            isSwimming = false;
        }
     
        tickCount++;
    }
    
     public void tickattack(Screen screen) {
       
    }

 
    
  
    @SuppressWarnings("empty-statement")
    public void render(Screen screen) {
        
        int xTile = 0;
        int yTile = 17+28;
        int walkingSpeed =4;
        
        boolean test= false;
        int modifier =32*scale;
    
        
        
        
        /*screen.render(this.x, this.y, 200, 0x00, 1);*/
        
        
        String Pv = this.name;
        Font.render(Pv, screen, x+ 6 +  Pv.length(),y -5  , 1);
        if (random > 3){
            screen.render(x, y, xTile + 1 + (yTile+2) * 32, 0,scale);   
        }
        else{
            if(tickCount%20 <6){
                screen.render(x , y, xTile + (yTile+movingDir+1) * 32, 0,scale);
            } else if (6 <= tickCount%18 && tickCount % 18 <12){
                screen.render(x , y, xTile + (yTile+movingDir+1) * 32 +1, 0,scale);
            } else {
                screen.render(x, y, xTile + (yTile+movingDir+1) * 32 +2 , 0,scale);
            }
        }
        
        if(isInteracting){
            Font.render("SALUT C UN TEST", screen, x-100, y-100, 1); 
        }
        

   
        

      
    }
    
    public boolean interaction(int x, int y) {
        if (x<this.x+32 && x>this.x-32 && y<this.y+32 && y>this.y-32  ){
            isInteracting=true;
            System.out.println("salut "+ "x " + x + " y " + y ); 
            return true;
        }
        return false;
        
        
    }
    
    public boolean isAttacked( String name, int xMin , int xMax, int yMin, int yMax, int damage ){
        return false;
    }
    
    public boolean hasCollided(int xa, int ya) {
        
      int xMin = 12;
      int xMax = 20;
      int yMin = 15;
      int yMax = 28;

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
