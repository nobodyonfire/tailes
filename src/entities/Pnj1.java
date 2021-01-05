package entities;

import Level.Level;
import gfx.Font;

import gfx.Screen;
import java.util.Random;
import java.util.logging.Logger;
import static java2dgame.Game.sleep;
import java2dgame.InputHandler;


public class Pnj1 extends Mob {

    private InputHandler input;
    
    private int scale= 1;
    protected boolean isSwimming =false;
    private int tickCount=0;
    int random;

    private int idxSpeak=0;
    private String sentence;
    private int tickSpeak=0;
    
    private int xplayer;
    private int yplayer;
    
    private int count =0;
    
    public Pnj1(Level level,String name, int x , int y,InputHandler input, int speed){
        super(level,name,x,y,speed);
        this.pv=500;
        this.input=input;
    }

    public void tick() {
        int xa=0;
        int ya=0;

        
        if (tickCount%400<200){
            xa=1;
        }else{
            xa=-1;
        }     
        
        xa=0;

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
        
        
        if (input.skip.isPressed() && tickSpeak>50){
            idxSpeak+=1;
            tickSpeak=0;
        }
      
        if (count>10000){
            count=0;
        }
        if(idxSpeak==0){
            this.endTalking=false;
        }
        
        
        tickSpeak++;
        tickCount++;
        count++;
    }
    
     public void tickattack(Screen screen) {
       
    }

 
    
  
   
    public void render(Screen screen) {
       
        
        int xTile = 0;
        int yTile = 17+28;
        int walkingSpeed =4;
        
        boolean test= false;
        int modifier =32*scale;
    
        int idx =0;
        
        
    
        if (this.interaction){
            
    
            screen.render(xplayer-320, yplayer-162, 896, 0x00, 1);
            screen.render(xplayer-288, yplayer-162, 897, 0x00, 1);
            screen.render(xplayer-256, yplayer-162, 898, 0x00, 1);
            screen.render(xplayer-224, yplayer-162, 899, 0x00, 1);
            screen.render(xplayer-192, yplayer-162, 900, 0x00, 1);    
            screen.render(xplayer-160, yplayer-162, 901, 0x00, 1);  
            screen.render(xplayer-128, yplayer-162, 902, 0x00, 1);  
            screen.render(xplayer-96, yplayer-162, 903, 0x00, 1);    
            screen.render(xplayer-64, yplayer-162, 904, 0x00, 1);  
            screen.render(xplayer-32, yplayer-162, 905, 0x00, 1);  
            screen.render(xplayer, yplayer-162, 906, 0x00, 1); 
            screen.render(xplayer+32, yplayer-162, 907, 0x00, 1);  
            screen.render(xplayer+64, yplayer-162, 908, 0x00, 1);  
            screen.render(xplayer+96, yplayer-162, 909, 0x00, 1);  
            screen.render(xplayer+128, yplayer-162, 910, 0x00, 1);  
            screen.render(xplayer+160, yplayer-162, 911, 0x00, 1);  
            screen.render(xplayer+192, yplayer-162, 912, 0x00, 1);  
            screen.render(xplayer+224, yplayer-162, 913, 0x00, 1);  
            screen.render(xplayer+256, yplayer-162, 914, 0x00, 1);  
            
            screen.render(xplayer-320, yplayer-130, 928, 0x00, 1);
            screen.render(xplayer-288, yplayer-130, 929, 0x00, 1);
            screen.render(xplayer-256, yplayer-130, 930, 0x00, 1);
            screen.render(xplayer-224, yplayer-130, 931, 0x00, 1);
            screen.render(xplayer-192, yplayer-130, 932, 0x00, 1);    
            screen.render(xplayer-160, yplayer-130, 933, 0x00, 1);  
            screen.render(xplayer-128, yplayer-130, 934, 0x00, 1);  
            screen.render(xplayer-96, yplayer-130, 935, 0x00, 1);    
            screen.render(xplayer-64, yplayer-130, 936, 0x00, 1);  
            screen.render(xplayer-32, yplayer-130, 937, 0x00, 1);  
            screen.render(xplayer, yplayer-130, 938, 0x00, 1); 
            screen.render(xplayer+32, yplayer-130, 939, 0x00, 1);  
            screen.render(xplayer+64, yplayer-130, 940, 0x00, 1);  
            screen.render(xplayer+96, yplayer-130, 941, 0x00, 1);  
            screen.render(xplayer+128, yplayer-130, 942, 0x00, 1);  
            screen.render(xplayer+160, yplayer-130, 943, 0x00, 1);  
            screen.render(xplayer+192, yplayer-130, 944, 0x00, 1);  
            screen.render(xplayer+224, yplayer-130, 945, 0x00, 1);  
            screen.render(xplayer+256, yplayer-130, 946, 0x00, 1);  
            
            Font.render(sentence, screen, this.xplayer-285, this.yplayer-145, 1);
         
        }
               
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
        
      
    
        

      
    }
    
    public boolean interaction(int x, int y) {
        if (x<this.x+32 && x>this.x-32 && y<this.y+32 && y>this.y-32  ){
           
            if(x<this.x+32 && x>=this.x){
                this.xplayer=x-x%32;
            }
            if(x>this.x-32 && x<this.x){    
                this.xplayer=x-x%32;
            }
            if(x<this.y+32 && y>=this.y){
                this.yplayer=y-y%32;
            }
            if(x>this.y-32 && y<this.y){
                this.yplayer=y-y%32;
            }
            this.xplayer=x;
            this.yplayer=y;

            return true;
            
        }
        return false;
        
        
    }
    
    public boolean interactiondialogue(Entity e) {
        if(idxSpeak==0){
             sentence="salut";
        }
        if(idxSpeak==1){
             sentence="deuxieme";
        }
        if(idxSpeak==2){
            sentence="";
            idxSpeak=0;
            this.endTalking=true;
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
