package entities;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import Level.Level;
import tiles.Tile;


public abstract class Mob extends Entity {
    
    protected String name;
    protected int speed;
    protected int numSteps = 0;
    protected boolean isMoving;
    protected int movingDir = 1;
    protected int scale =1;
    protected int xCentreTile=0;
	protected int yCentreTile=0;
	protected boolean inTile=false;  
	protected int lastMove=0;
	protected int tileType=0;
    
    public Mob(Level level, String name,int x,int y, int speed){
        super(level); 
        this.name=name;
        this.speed=speed;
        this.x=x;
        this.y=y;
    }
    
    public void move(int xa,int ya){
        
        
        if (xa !=0 && ya!=0){
            
            move(0,ya); 
            move(xa,0);
            
            numSteps--;
            return; 
        }
        
        numSteps++;
        if (!hasCollided(xa,ya)){
            if (ya <0) 
                movingDir =0;
            if (ya >0)
                movingDir =1;
            if (xa <0)
                movingDir =2;
            if (xa >0)
                movingDir =3;
            
            x+= xa*speed;
            y+= ya*speed;
            
        }
        
    }
 
    
    public abstract boolean hasCollided(int xa,int ya);
    
    protected boolean isSolidTile(int xa,int ya, int x , int y){
        
        if (level ==null){
            return false;
        }
        
        Tile lastTile = level.getTile((this.x +x) >>5, (this.y +y) >>5);
        Tile newTile = level.getTile((this.x +x +xa) >>5, (this.y +y +ya) >>5);
        
        int xinter= this.x +x +xa;
		int yinter= this.y +y +ya;
        
        if (!lastTile.equals(newTile) && newTile.isSolid()){ 	
        	//set up
    		if(!inTile) {
	    		if(xa==1) {
	    			xCentreTile=xinter;
	    			yCentreTile=yinter-(yinter%32);
	    		}
	    		else if(ya==1) {
	    			yCentreTile=yinter;
	    			xCentreTile=xinter-(xinter%32);
	    		}
	    		else if(xa==-1) {
	    			xCentreTile=xinter-32+1;
	    			yCentreTile=yinter-(yinter%32);
	    		}
	    		else if(ya==-1) {
	    			yCentreTile=yinter-32+1;
	    			xCentreTile=xinter-(xinter%32);
	    		}
    		}
    		
    		//Si c'est juste un carré
        	if(newTile.getSolidType()==0) {
        		setLastMove(xa,ya);
        		return true;
        	}
        	
        	//Si c'est un solid particulier 
        	if(newTile.getSolidType()!=0) { 
        		tileType=newTile.getSolidType();
        		inTile=true; 
        	}
            
        }
        
        if(inTile) {
        	//débloquer si on peut plus bouger
        	if(xa==-1 && lastMove==1) {
        		inTile=false;
        		setLastMove(xa,ya);
    			return false;
    		}
    		else if(ya==-1 && lastMove==2) {
    			inTile=false;
    			setLastMove(xa,ya);
    			return false;
    		}
    		else if(xa==1 && lastMove==3) {
    			inTile=false;
    			setLastMove(xa,ya);
    			return false;
    		}
    		else if(ya==1 && lastMove==4) {
    			inTile=false;
    			setLastMove(xa,ya);
    			return false;
    		}
        	//Dans le Tile
    		if(xinter>=xCentreTile && xinter<=xCentreTile+32 && yinter>=yCentreTile && yinter<=yCentreTile+31 ) {  //va savoir pourquoi 31 mais 32 on peut pas ressortir
    			if(tileType==1) {
    				if(yinter<yCentreTile+xinter-xCentreTile ) {
        				setLastMove(xa,ya);
        				return true;
        			}
        			else {
        				setLastMove(xa,ya);
                		return false; 
        			}
    			}
    			else if(tileType==2) {
    				if(yinter<yCentreTile-xinter+xCentreTile+32 ) {
        				setLastMove(xa,ya);
        				return true;
        			}
        			else {
        				setLastMove(xa,ya);
                		return false; 
        			}
    			}
    			else if(tileType==3) {
    				if(yinter>yCentreTile+xinter-xCentreTile ) {
        				setLastMove(xa,ya);
        				return true;
        			}
        			else {
        				setLastMove(xa,ya);
                		return false; 
        			}
    			}
    			else if(tileType==4) {
    				if(yinter>yCentreTile-xinter+xCentreTile+32 ) {
        				setLastMove(xa,ya);
        				return true;
        			}
        			else {
        				setLastMove(xa,ya);
                		return false; 
        			}
    			}
        	}
        }
        
        setLastMove(0,0);
        inTile= false; 
        return false;
        
    }
    
    //Savoir quelle est le dernier mouvement pour pouvoir sortir
    private void setLastMove(int xa,int ya) {
    	if(xa==1) {
			lastMove=1;
		}
		else if(ya==1) {
			lastMove=2;
		}
		else if(xa==-1) {
			lastMove=3;
		}
		else if(ya==-1) {
			lastMove=4;
		}
		else{
			lastMove=0;
		}
    }
    public String getName(){
        return name;
    }
    
}
