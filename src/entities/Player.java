package entities;

import Level.Level;
import gfx.Font;

import gfx.Screen;
import java.util.ArrayList;
import java.util.List;
import java2dgame.InputHandler;



public class Player extends Mob {

    private InputHandler input;
 
    private int scale= 1;
    protected boolean isSwimming =false;
    private int tickCount=0;
    private int tickInteraction=0;
    private int cooldownDash=41;  //cooldown du dash
    private int cooldownAttack=100; 
    private int dashLength=0;    //gérer le dash ( la forme et vitesse )
    int vitesseDash = 4;         //maximum de vitesse dans dash ( parabolique )
 
    private boolean cooldownAbility1activated=false;
    private boolean cooldownAbility2activated=false;
    private int cooldownAbility1=1000; 
    private int cooldownAbility2=1000; 
    
    protected boolean isDashing = false;
    
    private String arme;
    private List<List<Integer>> arrow = new ArrayList<List<Integer>>();
    List<Integer> arrowused = new ArrayList<>();
    
    public Player(Level level, int x , int y, InputHandler input, int speed,String arme){
        super(level,"Player",x,y,speed);
        this.input=input;
        this.pv=20;
        this.arme=arme;
        
    }

    public void tick() {
        int xa=0;
        int ya=0;
        interaction=false;
        
        
       
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
        
        
        if (input.interactionkey.isPressed() && tickInteraction>100){
            interaction=true;
            tickInteraction=0;
        }
        
        if ((xa !=0 || ya !=0) && (cooldownAttack>30) && !this.interaction){
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
       
        if((level.getTile(this.x+16>>5,this.y+16>>5).getId() ==73)){
            this.arme="bow";
        }
        if((level.getTile(this.x+16>>5,this.y+16>>5).getId() ==72)){
            this.arme="sword";
        }
         
        if((level.getTile(this.x+16>>5,this.y+16>>5).getId() ==3) || (level.getTile(this.x+16>>5,this.y+16>>5).getId() <=144 && level.getTile(this.x+16>>5,this.y+16>>5).getId() >=130)){
            isSwimming = true;
        }
     
        if((level.getTile(this.x+16>>5,this.y+16>>5).getId() !=3) && (level.getTile(this.x+16>>5,this.y+16>>5).getId() >144 || level.getTile(this.x+16>>5,this.y+16>>5).getId() <130)){
            isSwimming = false;
        }
        

        tickInteraction++;
        tickCount++;
        cooldownDash++; //on incrémente le cooldown du dash
    }
    
 
    public void tickattack(Screen screen) {
        
        int damage=1;
        List<Integer> newarrow = new ArrayList<Integer>();
        int speedarrow=3;
        
        
        if (cooldownAbility1>300){
            cooldownAbility1activated=false;
        }
        
        if (cooldownAbility2>300){
            cooldownAbility2activated=false;
        }
        
        if (input.ability1.isPressed()&& cooldownAbility1> 500){
            cooldownAbility1activated=true;
            cooldownAbility1=0;
            
        }
        
         if (input.ability2.isPressed()&& cooldownAbility2> 500){
            cooldownAbility2activated=true;
            cooldownAbility2=0;
            
        }
         
        if ( cooldownAbility1activated==true){
            damage=3;
        }
        
         if (input.attack.isPressed()&& cooldownAttack> 40 && "bow".equals(this.arme)){
            
            newarrow.add(this.x);
            newarrow.add(this.y);
            newarrow.add(this.movingDir); 
            newarrow.add(damage);
            List<Integer> copy = new ArrayList<>(newarrow);
            
            arrow.add(copy);  
            newarrow.clear();
            cooldownAttack=0; 
        }
         
         
        if ("bow".equals(this.arme)){
            
            int idx=0;
            arrowused.clear();
            for (List<Integer> coordonate: arrow) {

                if (level.attackEntities( screen, name, coordonate.get(0) -16,   coordonate.get(0) +16, coordonate.get(1) -16,  coordonate.get(1) + 16, damage)){   /* On effectue les dégats sur les fleches les fleches */
                    arrowused.add(idx);
                    if ( cooldownAbility2activated){
                        this.pv+=damage;
                    }

                }
                if (coordonate.get(0)>level.width*32 || coordonate.get(0)<0 || coordonate.get(1)>level.height*32 || coordonate.get(1) <0){
                    arrowused.add(idx);
        
                }
                
                idx+=1;
            }
            
            for (int i=0;i<arrowused.size();i++) {  /* On actualise les fleches */
                int removedarrow = arrowused.get(i);
                arrow.remove( removedarrow);
            }
          

            


            for (List<Integer> coordonate: arrow) {
                switch (coordonate.get(2)) {
                    case 0:
                        coordonate.set(1, coordonate.get(1)-speedarrow);
                        break;
                    case 1:
                        coordonate.set(1, coordonate.get(1)+speedarrow);
                        break;
                    case 2:
                        coordonate.set(0, coordonate.get(0)-speedarrow);
                        break;
                    case 3:
                        coordonate.set(0, coordonate.get(0)+speedarrow);
                        break;
                    default:
                        break;
                    } 
            }
        }
        
        
        
        if (input.attack.isPressed()&& cooldownAttack> 40 && "sword".equals(this.arme)){
            
            cooldownAttack=0;
            switch (movingDir) {
                case 0:
                    if (level.attackEntities( screen, name, this.x -32,   this.x +32, this.y -64,  this.y - 16, damage)) {
                        if ( cooldownAbility2activated){
                            this.pv+=damage;
                        }
                    }
                    break;
                case 1:
                    if (level.attackEntities( screen, name, this.x -32,   this.x +32, this.y +16,  this.y +64, damage)){
                        if ( cooldownAbility2activated){
                            this.pv+=damage;
                        }
                    }
                    break;
                case 2:
                    if(level.attackEntities( screen, name, this.x -64,   this.x -16, this.y -32,  this.y + 32, damage)){
                        if ( cooldownAbility2activated){
                            this.pv+=damage;
                        }
                    }
                    break;
                case 3:
                    if (level.attackEntities( screen, name, this.x +16,   this.x +64, this.y -32,  this.y +32, damage)){
                        if ( cooldownAbility2activated){
                            this.pv+=damage;
                        }
                    }
                    break;
                default:
                    break;
            } 
        }
        
        
   
       
        
        
        cooldownAttack++;
        cooldownAbility1++;
        cooldownAbility2++;
     
    }
 
    public void render(Screen screen) {
        int xTile = 0;
        int yTile = 31;

        int animationdelay =40;
        
     
  
        
        int modifier =32*scale;
        int xOffset = x- modifier/2;
        int yOffset = y- modifier/2;
        int modifdash=0;
        int xModif=0;
        int yModif=0;
        
        int xAbility=xOffset;
        int yAbility=yOffset;
        
        int modificationAttack=yTile;
        int modifierarrow=0;
        int modifierweapon=0;
       /* screen.render(this.x, this.y, 200, 0x00, 1);*/
        
         
        if (cooldownAbility1activated){    /* ABILITY 1 : augmentation) dégats  */
            modifierarrow=1;
        } 
        
        if ("bow".equals(this.arme)){
            modifierweapon=1;
        }
       
       for (List<Integer> coordonate: arrow) {
                switch (coordonate.get(2)) {
                    case 0:
                        screen.render(coordonate.get(0), coordonate.get(1) , 512 +coordonate.get(2) + modifierarrow *32, 0x00, 1);
                        break;
                    case 1:
                        screen.render(coordonate.get(0), coordonate.get(1) ,512 +coordonate.get(2) +   modifierarrow *32, 0x00, 1);
                        break;
                    case 2:
                        screen.render(coordonate.get(0), coordonate.get(1) , 512 +coordonate.get(2) + modifierarrow *32 , 0x00, 1);
                        break;
                    case 3:
                        screen.render(coordonate.get(0), coordonate.get(1) , 512 +coordonate.get(2) + modifierarrow *32 , 0x00, 1);
                        break;
                    default:
                        break;
                    } 
            }
 
        
        
        if (cooldownDash>40 && !isSwimming){        /* Affichage du DASH  */
            screen.render(xOffset-20, yOffset +35, 320, 0x00, 1);
        }
        
        
        String Pv = String.valueOf(this.pv);    /* Affichage des PV  */
        Font.render(Pv, screen, xOffset +20 + Pv.length(),yOffset -12, 1); 

        if ( xOffset<290){
                xAbility=290;
        }
        if ( yOffset<143){
                yAbility=143;
        }
        
    
        if (cooldownAbility1> 500){    /* Affichage ability1  */
            
            screen.render(xAbility-100, yAbility +130, 352, 0x00, 2);
        }else {
             screen.render(xAbility-100,yAbility +130, 384, 0x00, 2);
        }
        
         if (cooldownAbility2> 500){    /* Affichage ability1  */
            
            screen.render(xAbility-50, yAbility +130, 353, 0x00, 2);
        }else {
             screen.render(xAbility-50,yAbility +130, 385, 0x00, 2);
        }
         
         if (cooldownAbility2activated){    /* ABILITY 2 :  */
             
                 if(tickCount %40 <10){                     
                     screen.render(xOffset, yOffset  , 293, 0x00, 1);
                    screen.render(xOffset+32, yOffset , 294, 0x00, 1);
                    screen.render(xOffset, yOffset+32, 325, 0x00, 1);
                    screen.render(xOffset+32, yOffset+32, 326, 0x00, 1);

                } else if (10 <= tickCount%40 && tickCount % 40 <20){
                    screen.render(xOffset, yOffset +2, 293, 0x00, 1);
                    screen.render(xOffset+32, yOffset +2 , 294, 0x00, 1);
                    screen.render(xOffset, yOffset+32+ +2, 325, 0x00, 1);
                    screen.render(xOffset+32, yOffset+32 +2, 326, 0x00, 1);

                } else if (20 <= tickCount%40 && tickCount % 40 <30){
                    screen.render(xOffset, yOffset +4, 293, 0x00, 1);
                    screen.render(xOffset+32, yOffset+4 , 294, 0x00, 1);
                    screen.render(xOffset, yOffset+32+ +4, 325, 0x00, 1);
                    screen.render(xOffset+32, yOffset+32 +4 , 326, 0x00, 1);

                } else {
                    screen.render(xOffset, yOffset +2, 293, 0x00, 1);
                    screen.render(xOffset+32, yOffset +2, 294, 0x00, 1);
                    screen.render(xOffset, yOffset+32+ 2, 325, 0x00, 1);
                    screen.render(xOffset+32, yOffset+32 +2 , 326, 0x00, 1);
                }
            }
              
               
            
         
        if (cooldownAttack<=20 && !isSwimming){ /* ATTAQUE  */
                
            
            if (cooldownAbility1activated){    /* ABILITY 1 : augmentation) dégats  */
                xTile = 8;
            } 
            
                if (movingDir==2){
                   xModif=32;
                }
                if (movingDir==1){
                   yModif=31;
                }

                 if(tickCount %40 <10){                     
                    screen.render(x - xModif  , y -32, xTile + 7 + (yTile+(movingDir*3)+ 1+ (modifierweapon*20))  * 32, 0,scale);
                    screen.render(x - xModif  +32  , y -32, xTile + 8 + (yTile+(movingDir*3)+ 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif    , y , xTile + 7 + (yTile+(movingDir*3)+ 1 + 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif  +32  , y , xTile + 8 + (yTile+(movingDir*3)+ 1 + 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif    , y +32, xTile + 7 + (yTile+(movingDir*3)+ 1 + 2+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif  +32  , y +32 , xTile + 8 + (yTile+(movingDir*3)+ 1 + 2+ (modifierweapon*20)) * 32, 0,scale);

                } else if (10 <= tickCount%40 && tickCount % 40 <20){
                    screen.render(x - xModif    , y -32, xTile + 9 + (yTile+(movingDir*3)+ 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif  +32  , y -32, xTile + 10 + (yTile+(movingDir*3)+ 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif    , y , xTile + 9 + (yTile+(movingDir*3) + 1 + 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif  +32  , y , xTile + 10 + (yTile+(movingDir*3) + 1 + 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif    , y +32, xTile + 9 + (yTile+(movingDir*3)+ 1 + 2+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif  +32  , y +32 , xTile + 10 + (yTile+(movingDir*3)+ 1 + 2+ (modifierweapon*20)) * 32, 0,scale);

                } else if (20 <= tickCount%40 && tickCount % 40 <30){
                    screen.render(x - xModif    , y -32, xTile + 11 + (yTile+(movingDir*3)+ 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif  +32  , y -32, xTile + 12 + (yTile+(movingDir*3)+ 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif    , y , xTile + 11 + (yTile+(movingDir*3)+ 1 + 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif  +32  , y , xTile + 12 + (yTile+(movingDir*3)+ 1 + 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif    , y +32, xTile + 11 + (yTile+(movingDir*3)+ 1 + 2+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif  +32  , y +32 , xTile + 12 + (yTile+(movingDir*3)+ 1 + 2+ (modifierweapon*20)) * 32, 0,scale);

                } else {
                    screen.render(x - xModif - yModif    , y -32, xTile + 13 + (yTile+(movingDir*3)+ 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif - yModif +32  , y -32, xTile + 14 + (yTile+(movingDir*3)+ 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif - yModif    , y , xTile + 13 + (yTile+(movingDir*3)+ 1 + 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif - yModif  +32  , y , xTile + 14 + (yTile+(movingDir*3)+ 1 + 1+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif - yModif    , y +32, xTile + 13 + (yTile+(movingDir*3)+ 1 + 2+ (modifierweapon*20)) * 32, 0,scale);
                    screen.render(x - xModif - yModif  +32  , y +32 , xTile + 14 + (yTile+(movingDir*3)+ 1 + 2+ (modifierweapon*20)) * 32, 0,scale);
                }
                 return;
            
        }
        
        if (isDashing ){
              
              if(tickCount% animationdelay <(animationdelay/6)){
                screen.render(x  , y -modifier, xTile + modifierarrow *  6 + 7 + (yTile+ 12 +(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y , xTile + modifierarrow *  6 + 7 + (yTile + 12+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(animationdelay/6)) &&  (tickCount% animationdelay <(2*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + modifierarrow *  6 + 7 + 1 + (yTile + 12+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile +  modifierarrow *  6 +7 + 1 + (yTile + 12+(movingDir *2)+2) * 32, 0,scale);
            }   
            else if ( (tickCount% animationdelay >(2*animationdelay/6)) &&  (tickCount% animationdelay <(3*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + modifierarrow *  6 + 7 + 2 + (yTile + 12+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile +  modifierarrow *  6 +7 + 2 + (yTile + 12+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(3*animationdelay/6)) &&  (tickCount% animationdelay <(4*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile +  modifierarrow *  6 +7 + 3 + (yTile + 12+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile +  modifierarrow *  6 +7 + 3 + (yTile + 12+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(4*animationdelay/6)) &&  (tickCount% animationdelay <(5*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 7 + 4 + (yTile + 12+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + modifierarrow *  6 + 7 + 4 + (yTile + 12+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(5*animationdelay/6)) &&  (tickCount% animationdelay <(6*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + modifierarrow *  6 + 7 + 5 + (yTile + 12+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + modifierarrow *  6 + 7 + 5 + (yTile + 12+(movingDir *2)+2) * 32, 0,scale);
            }
            else {
                screen.render(x , y -modifier, xTile + modifierarrow *  6 + 7 + (yTile + 12+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + modifierarrow *  6 + 7  + (yTile + 12+(movingDir *2)+2) * 32, 0,scale);
            }
         
         return;
           
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
            return;
        
            
        }

         if( cooldownAbility1activated){
                modificationAttack=yTile + 8;
            }
        
        if( isMoving){
            
           
            if(tickCount% animationdelay <(animationdelay/6)){
                screen.render(x  , y -modifier, xTile + (modificationAttack+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y , xTile + (modificationAttack+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(animationdelay/6)) &&  (tickCount% animationdelay <(2*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 1 + (modificationAttack+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + 1 + (modificationAttack+(movingDir *2)+2) * 32, 0,scale);
            }   
            else if ( (tickCount% animationdelay >(2*animationdelay/6)) &&  (tickCount% animationdelay <(3*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 2 + (modificationAttack+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + 2 + (modificationAttack+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(3*animationdelay/6)) &&  (tickCount% animationdelay <(4*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 3 + (modificationAttack+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + 3 + (modificationAttack+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(4*animationdelay/6)) &&  (tickCount% animationdelay <(5*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 4 + (modificationAttack+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + 4 + (modificationAttack+(movingDir *2)+2) * 32, 0,scale);
            }
            else if ( (tickCount% animationdelay >(5*animationdelay/6)) &&  (tickCount% animationdelay <(6*animationdelay/6)) ) {
                screen.render(x , y -modifier, xTile + 5 + (modificationAttack+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile + 5 + (modificationAttack+(movingDir *2)+2) * 32, 0,scale);
            }
            else {
                screen.render(x , y -modifier, xTile + (modificationAttack+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y, xTile  + (modificationAttack+(movingDir *2)+2) * 32, 0,scale);
            }
            return;
        }
        
        if (!isSwimming && !isDashing  && !isMoving){
                screen.render(x , y -modifier, xTile + (modificationAttack+(movingDir *2)+1) * 32, 0,scale);
                screen.render(x , y , xTile  + (modificationAttack+(movingDir *2)+2) * 32, 0,scale);
            }
        
    }
        
    public boolean isAttacked( String name, int xMin , int xMax, int yMin, int yMax, int damage ){
        if (this.name !=name ){
            this.pv=pv-damage;
            return true;
        }
        return false;
        
    }
    


    public boolean hasCollided(int xa, int ya) {

      int xMin =12;
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

    public boolean interaction(int x, int y) {
        return false;
    }


    
    
}
