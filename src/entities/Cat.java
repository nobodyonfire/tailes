package entities;

import Level.Level;
import gfx.Font;

import gfx.Screen;
import java.util.Random;



public class Cat extends Mob {

    private int scale= 1;
    protected boolean isSwimming =false;
    private int tickCount=0;
    int random;
    private int pv;
 
    
    public Cat(Level level,String name, int x , int y, int speed){
        super(level,name,x,y,speed);
        this.pv=5;
    }

    public void tick() {
        int xa=0;
        int ya=0;
        Random rn = new Random();
        

        if (tickCount%20==0)
            random =rn.nextInt(8);

        /*
    
        if (random ==0)
            xa+=1;
        if (random ==1)
            xa-=1;
        if (random ==2)
            ya+=1;
        if (random ==3)
            ya-=1;
  
        */
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

 
    
  
    public void render(Screen screen) {
        
        int xTile = 0;
        int yTile = 17;
        int walkingSpeed =4;
        
        
        int modifier =32*scale;
    
        
        
        
        screen.render(this.x, this.y, 200, 0x00, 1);
        
        
        String Pv = String.valueOf(this.pv);
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

   
        

      
    }
    
    public void isAttacked( String name, int xMin , int xMax, int yMin, int yMax, int damage ){
        if (this.x<xMax && this.x>xMin && this.y<yMax && this.y>yMin ){   
            this.pv=pv-damage;
        }
        System.out.println("chat coordonées " + this.x + " " + this.y);
        System.out.println("INFOS : " + "NOM :"+ name+ " xMin :"+ xMin + " xMax :"+ xMax + " yMin :"+ yMin+ " yMax :"+ yMax + " damage :"+ damage );
         
    }
    
    public boolean hasCollided(int xa, int ya) {

      int xMin = 36;
      int xMax = 60;
      int yMin = 36;
      int yMax = 60;

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
