package entities;

import Level.Level;
import gfx.Screen;


public abstract class Entity {
    
    public int x,y;
    protected Level level;
   protected int pv;
    
    public Entity(Level level){
        init(level);
    }
    
    public final void init(Level level){
        this.level=level;
    }
    
    public abstract void tick();
    
    public abstract void tickattack(Screen screen);
    
  
    public abstract void render(Screen screen);

    public abstract void isAttacked(String name, int xMin, int xMax, int yMin, int yMax, int damage);

    public int getPv() {   //récupère les PVs
       return pv;
   } 
}
