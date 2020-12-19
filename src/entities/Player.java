package entities;

import Level.Level;
import gfx.Font;

import gfx.Screen;
import java2dgame.InputHandler;



public class Player extends Mob {

    private InputHandler input;
    private int scale= 1;
    protected boolean isSwimming =false;
    private int tickCount=0;
    private int cooldownDash=41;  //cooldown du dash
    private int dashLength=0;    //gérer le dash ( la forme et vitesse )
    int vitesseDash = 4;         //maximum de vitesse dans dash ( parabolique )
 
    protected boolean isDashing = false;
    
    public Player(Level level, int x , int y, InputHandler input, int speed){
        super(level,"Player",x,y,speed);
        this.input=input;
        
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
        
        if(level.getTile(this.x>>5,this.y>>5).getId() ==3){
            isSwimming = true;
        }
        if (isSwimming && level.getTile(this.x>>5,this.y>>5).getId() !=3){
            isSwimming = false;
        }
        
     
        tickCount++;
        cooldownDash++; //on incrémente le cooldown du dash
    }

 
    public void render(Screen screen) {
        int xTile = 0;
        int yTile = 28;
        int walkingSpeed =4;
        int flipTop=(numSteps >> walkingSpeed)& 1;
        int flipBottom=(numSteps >> walkingSpeed)& 1;
        
        if(movingDir ==1 ) {
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
        
        int modifdash=0;
        
        if (cooldownDash>40 && !isSwimming){
            screen.render(xOffset-20, yOffset +35, 320, 0x00, 1);
        }
        
        Font.render("Julien", screen, xOffset +5,yOffset -18, 1);
     
        
        if (isDashing && !isSwimming){
            System.out.println(" xTile  + (yTile) * 32  "+ ( xTile  + (yTile) * 32));
            
            if (cooldownDash <20){
                 yTile -=2;
            }
            if (cooldownDash <30 && cooldownDash >= 20){
                 yTile -=4;
                 modifdash =2;
                 
            }
            if (cooldownDash <40 && cooldownDash >=30){
                 yTile -=2;
            }
            
            screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, flipTop,scale);
            screen.render(xOffset  + modifier -(modifier * flipTop) , yOffset, xTile + 1 + yTile * 32,flipTop,scale);
            
            
            
            screen.render(xOffset +(modifier * flipBottom) , yOffset + modifier , xTile + (yTile+1) * 32,flipBottom,scale);
            screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, xTile + 1 + (yTile+1) * 32,flipBottom,scale);
           
        }
            
        
        if (isSwimming){
            yOffset+=8;
            if(tickCount %60 <15){
                screen.render(xOffset+22, yOffset +11, 192, 0x00, 2);
            } else if (15 <= tickCount%60 && tickCount % 60 <30){
                yOffset -=1;
                screen.render(xOffset+22, yOffset +11, 193, 0x00, 2);
            } else if (30 <= tickCount%60 && tickCount % 60 <45){
                screen.render(xOffset+ 22, yOffset +11, 194, 0x00, 2);
            } else {
                yOffset -=1;
                screen.render(xOffset+22, yOffset +11, 193, 0x00, 2);
            }
            
            screen.render(xOffset +(modifier * flipBottom), yOffset + modifier , xTile  + 8 + (yTile+1) * 32,flipBottom,scale);
            screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, xTile+ 8 + 1 + (yTile+1) * 32,flipBottom,scale);
            screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, flipTop,scale);
            screen.render(xOffset  + modifier -(modifier * flipTop) , yOffset, xTile + 1 + yTile * 32,flipTop,scale);
            
        }

     
        
        if(!isSwimming && !isDashing ){
            screen.render(xOffset +(modifier * flipBottom), yOffset + modifier , xTile + (yTile+1) * 32,flipBottom,scale);
            screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, xTile + 1 + (yTile+1) * 32,flipBottom,scale);
            screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, flipTop,scale);
            screen.render(xOffset  + modifier -(modifier * flipTop) , yOffset, xTile + 1 + yTile * 32,flipTop,scale);

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
