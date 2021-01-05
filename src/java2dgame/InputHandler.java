package java2dgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;



public class InputHandler implements KeyListener{

    public InputHandler(Game game){
        game.addKeyListener(this);
    }
    
    public class Key{
        public int numTimesPressed=0;
        private boolean pressed = false;
        
        public int getNumTimesPressed(){
            return numTimesPressed;
        }
        public boolean isPressed(){
            return pressed;
        }
        
        public void toggle(boolean isPressed){
            pressed = isPressed;
            if (isPressed) numTimesPressed++;
        }
    }
    
    public List<Key> keys = new ArrayList<Key>();
  
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    
    public Key dash = new Key();   //on définit la variable pour la touche du dash
    
    public Key interactionkey = new Key();
    public Key skip = new Key();
    public Key attack = new Key();   //on définit la variable pour la l'attaque
    
    public Key ability1 = new Key();
    
    public Key ability2 = new Key();
    
    
    

    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(),true);
    }

  
    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(),false);
    }
    
    public void keyTyped(KeyEvent e) {
        
    }
     
    public void toggleKey (int keyCode,boolean isPressed){
        if (keyCode == KeyEvent.VK_Z || keyCode == KeyEvent.VK_UP){ 
            up.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){ 
            down.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_Q || keyCode == KeyEvent.VK_LEFT){ 
            left.toggle(isPressed);
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){ 
            right.toggle(isPressed);
        }
        
        if (keyCode == KeyEvent.VK_F) {   //On définit la touche qui correspond au dash
        	dash.toggle(isPressed);
        }
          
        if (keyCode == KeyEvent.VK_A) {   //On définit la touche qui correspond à l'attaque
        	attack.toggle(isPressed);
        }
        
        if (keyCode == KeyEvent.VK_W) {   //On définit la touche qui correspond à l'abilité 1
        	ability1.toggle(isPressed);
        }
        
        if (keyCode == KeyEvent.VK_X) {   //On définit la touche qui correspond à l'abilité 2
        	ability2.toggle(isPressed);
        }
        
         if (keyCode == KeyEvent.VK_E) {   //On définit la touche qui correspond à l'interaction
        	interactionkey.toggle(isPressed);
        }
         
         if (keyCode == KeyEvent.VK_ENTER) {   //On définit la touche qui correspond au skip
        	skip.toggle(isPressed);
        }
            
            
    }
    
    
}
                                      