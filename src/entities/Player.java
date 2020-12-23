package entities;

import Level.Level;
import gfx.Font;

import gfx.Screen;
import java2dgame.InputHandler;



public class Player extends Mob {

    private InputHandler input;
    private int pv;
    private int scale= 1;
    protected boolean isSwimming =false;
    private int tickCount=0;
    private int cooldownDash=41;  //cooldown du dash
    private int cooldownAttack=0; 
    private int dashLength=0;    //gérer le dash ( la forme et vitesse )
    int vitesseDash = 4;         //maximum de vitesse dans dash ( parabolique )
 
    protected boolean isDashing = false;
    
    public Player(Level level, int x , int y, InputHandler input, int speed){
        super(level,"Player",x,y,speed);
        this.input=input;
        this.pv=20;
        
    }

    public void tick() {
        int xa=0;
        int ya=0;
      
        
        
       
        //Le player peut dash dans une direction s'il presse la direction et la touche du dash
        //Il ne peut pas dash tout le temps ( sinon c'est comme augmenter sa move speed )
        //Donc on met un cooldown
        
        if (input.up.isPressed()) {
            if(input.dash.isPressed() && cooldownDash>60 && !isSwimming ) {
            	cooldownDash=0;
            	dashLength=9; 
            }
            if(dashLength>0){
            	ya -= (int) (-(vitesseDash-1)/16)*(dashLength-1)*(dashLength-1) + ((vitesseDash-1)/2)*(dashLength-1) + 1 ;
            	dashLength-=1;
            }
            else {
            	ya -= 1;
            }
        }
        if (input.down.isPressed()) {
            if(input.dash.isPressed() && cooldownDash>60 && !isSwimming  ) {
            cooldownDash=0;
            dashLength=9; 
            }
            if(dashLength>0){
            	ya += (int) (-(vitesseDash-1)/16)*(dashLength-1)*(dashLength-1) + ((vitesseDash-1)/2)*(dashLength-1) + 1 ;
            	dashLength-=1;
            }
            else {
            	ya += 1;
            }
        }
        if (input.left.isPressed()) {
        	if(input.dash.isPressed() && cooldownDash>60 && !isSwimming  ) {
            	cooldownDash=0;
            	dashLength=9; 
            }
            if(dashLength>0){
            	xa -= (int) (-(vitesseDash-1)/16)*(dashLength-1)*(dashLength-1) + ((vitesseDash-1)/2)*(dashLength-1) + 1 ;
            	dashLength-=1;
            }
            else {
            	xa -= 1;
            }
        }
        if (input.right.isPressed()) {
        	if(input.dash.isPressed() && cooldownDash>60  && !isSwimming  ) {
            	cooldownDash=0;
            	dashLength=9; 
            }
            if(dashLength>0){
            	xa += (int) (-(vitesseDash-1)/16)*(dashLength-1)*(dashLength-1) + ((vitesseDash-1)/2)*(dashLength-1) + 1 ;
            	dashLength-=1;
            }
            else {
            	xa += 1;
            }
        }
        
        
        
        if (xa !=0 || ya !=0){
            move(xa,ya);
            isMoving =true; 
        } else {
            isMoving =false;        
        }
        
        if (cooldownDash>0 && cooldownDash<=60){
            isDashing=true;
        } else {
            isDashing=false;
        }
       
        
        if((level.getTile(this.x+16>>5,this.y+16>>5).getId() ==3) || (level.getTile(this.x+16>>5,this.y+16>>5).getId() <=144 && level.getTile(this.x+16>>5,this.y+16>>5).getId() >=130)){
            isSwimming = true;
        }
     
        if((level.getTile(this.x+16>>5,this.y+16>>5).getId() !=3) && (level.getTile(this.x+16>>5,this.y+16>>5).getId() >144 || level.getTile(this.x+16>>5,this.y+16>>5).getId() <130)){
            isSwimming = false;
        }
        

     
        tickCount++;
        cooldownDash++; //on incrémente le cooldown du dash
    }
    
 
    public void tickattack(Screen screen) {
        if (input.attack.isPressed()&& cooldownAttack> 40){
            cooldownAttack=0;
            if (movingDir==0){
                 level.attackEntities( screen, name, this.x -32,   this.x +32, this.y -64,  this.y - 16, 1);
            }
            else if (movingDir==1){
                  level.attackEntities( screen, name, this.x -32,   this.x +32, this.y +16,  this.y +64, 1);
            }
            else if (movingDir==2){
                 level.attackEntities( screen, name, this.x -64,   this.x -16, this.y -32,  this.y + 32, 1);
            }
            else if (movingDir==3){
                 level.attackEntities( screen, name, this.x +16,   this.x +64, this.y -32,  this.y +32, 1);
            }
        
          
            
        }
        cooldownAttack++;
    }
 
    public void render(Screen screen) {
        int xTile = 0;
        int yTile = 21;

        int animationdelay =50;
        
     
  
        
        int modifier =32*scale;
        int xOffset = x- modifier/2;
        int yOffset = y- modifier/2;
        
        int modifdash=0;
        
        
        screen.render(this.x, this.y, 200, 0x00, 1);
        
        
     
        
        
        
        if (cooldownDash>40 && !isSwimming){
            screen.render(xOffset-20, yOffset +35, 320, 0x00, 1);
        }
        
        String Pv = String.valueOf(this.pv);
        Font.render(Pv, screen, xOffset +20 + Pv.length(),yOffset -12, 1);
    
        
        if (isDashing && !isSwimming){

            
         
           
        }
            
        
        if (isSwimming){
            yOffset+=8;
            if(tickCount %60 <15){
                screen.render(xOffset+18, yOffset , 192, 0x00, 1);
                screen.render(x   , yOffset - 10 , xTile + 6 + (yTile+(movingDir)+1) * 32, 0,scale);
            } else if (15 <= tickCount%60 && tickCount % 60 <30){
                yOffset -=1;
                screen.render(xOffset+18, yOffset, 193, 0x00, 1);
                screen.render(x  , yOffset  - 10, xTile + 6+ (yTile+(movingDir)+1) * 32, 0,scale);
            } else if (30 <= tickCount%60 && tickCount % 60 <45){
                screen.render(xOffset+18, yOffset, 194, 0x00, 1);
                screen.render(x  , yOffset - 10, xTile + 6+ (yTile+(movingDir)+1) * 32, 0,scale);
            } else {
                yOffset -=1;
                screen.render(xOffset+18, yOffset , 193, 0x00, 1);
                screen.render(x  , yOffset - 10 , xTile + 6+ (yTile+(movingDir)+1) * 32, 0,scale);
            }
        
            
        }

     
        
        if(!isSwimming && !isDashing  && isMoving){
    
            if(tickCount% animationdelay <(animationdelay/6)){
                screen.render(x  , y -modifier, xTile + (yTile+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y , xTile + (yTile+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(animationdelay/6)) &&  (tickCount% animationdelay <(2*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 1 + (yTile+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + 1 + (yTile+(movingDir *2)+2) * 32, 0,scale);
            }   
            else if ( (tickCount% animationdelay >(2*animationdelay/6)) &&  (tickCount% animationdelay <(3*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 2 + (yTile+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + 2 + (yTile+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(3*animationdelay/6)) &&  (tickCount% animationdelay <(4*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 3 + (yTile+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + 3 + (yTile+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(4*animationdelay/6)) &&  (tickCount% animationdelay <(5*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 4 + (yTile+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + 4 + (yTile+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(5*animationdelay/6)) &&  (tickCount% animationdelay <(6*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 5 + (yTile+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + 5 + (yTile+(movingDir *2)+2) * 32, 0,scale);
            }
            else {
                screen.render(x , y -modifier, xTile + (yTile+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile  + (yTile+(movingDir *2)+2) * 32, 0,scale);
            }
        }
        
        if (!isSwimming && !isDashing  && !isMoving){
                screen.render(x , y -modifier, xTile + (yTile+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y , xTile  + (yTile+(movingDir *2)+2) * 32, 0,scale);
            }
        
    }
        
    public void isAttacked( String name, int xMin , int xMax, int yMin, int yMax, int damage ){
        if (this.name !=name ){
            this.pv=pv-damage;
        }
        
    }

    public boolean hasCollided(int xa, int ya) {

      int xMin =12;
      int xMax = 20;
      int yMin = 15;
      int yMax = 32;

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
