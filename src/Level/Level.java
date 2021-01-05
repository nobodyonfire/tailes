package Level;

import entities.Entity;
import entities.Player;
import gfx.Screen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import tiles.Tile;

public class Level {
 
    private int[] tiles;
    public int width;
    public int height;

    public List<Entity> entities = new ArrayList<Entity>();
    private String imagePath;
    private BufferedImage image;
    
    protected boolean attacked = false;
    protected boolean destroy = false;



    public Level(String imagePath) {
        
        if (imagePath != null){
            this.imagePath=imagePath;
            this.loadLevelFromFile();
        } else {
            tiles = new int[width * height];
            this.width = 64;
            this.height = 64;
            this.generateLevel();
            
        }
   
    }
    
    private void loadLevelFromFile(){
        try{
            this.image = ImageIO.read(Level.class.getResource(this.imagePath));
            this.width= image.getWidth();
            this.height=image.getHeight();
            tiles = new int [width * height];
            this.loadTiles();
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void loadTiles(){
        int[] tileColours = this.image.getRGB(0 ,0 ,width , height, null , 0 ,width);
        for (int y = 0 ; y < height ; y ++){
            for (int x = 0; x < width ; x ++) {
                tileCheck : for (Tile t : Tile.tiles){
                    if(t!=null && t.getlevelColour() == tileColours[x + y*width]){
                        this.tiles[x + y * width]= t.getId();
                        break tileCheck;
                    }
                }
            }
        }
    }
    
    private void saveLeveltoFile(){
        try {
            ImageIO.write(image,"png",new File(Level.class.getResource(this.imagePath).getFile()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public void alterTile(int x , int y , Tile newTile){
        this.tiles[x + y*width] = newTile.getId();
        image.setRGB(x, y, newTile.getlevelColour());
        
    }

    public void generateLevel() {
            for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                            if (x * y % 10 < 7) {
                                    tiles[x + y * width] = Tile.GRASS.getId();
                            } else {
                                    tiles[x + y * width] = Tile.STONE.getId();
                            }
                    }
            }
    }

    public void tick() {
        
   
        for (Iterator<Entity> iterator = entities.iterator(); iterator.hasNext();) { 
            
            Entity e= iterator.next();
            e.tick();
      
            if(e.getPv()<=0) {     // Ici on veut voir si les unitées sont mortes
                iterator.remove();
            }
            
            
                  
            if(e.player==true && e.wantToTalk==true){
                for (Entity einteract: entities){  
                    if (einteract.interaction(e.x, e.y)){
                        einteract.interactiondialogue(e);
                        e.interaction=true;
                        einteract.interaction=true;   
                    }  
                    else {
                        e.interaction=false;
                        einteract.interaction=false;   

                    }
                    
                    if (einteract.endTalking==true){
                        e.interaction=false;
                        einteract.interaction=false;  
                        e.wantToTalk=false;
                         
                    }   
                } 
            }
            
            
            
            
            /*
            if(e.interaction==true && e.canInteract==1){
               for (Entity einteract: entities){   
                   if(e.interaction==true && einteract.canInteract==2){
                           einteract.interaction=false;
                           e.interaction=false;
                           
                   }
                   if(einteract.interaction(e.x, e.y)){
                       einteract.interaction=true;
                   } 
                   
                    
                }
            }
             */
        }
           
        

        for (Tile t : Tile.tiles){

            if(t != null){
                 t.tick();
            } 
        }
    }
    
    public void tickattack(Screen screen){
        
        for (Entity e : entities){
            e.tickattack(screen);     
            
        }
    }

    public void renderTiles(Screen screen, int xOffset, int yOffset) {
            if (xOffset < 0)
                    xOffset = 0;
            if (xOffset > ((width << 5) - screen.width))       /*  BORDURES */
                    xOffset = ((width << 5) - screen.width);    /*  BORDURES */
            if (yOffset < 0)
                    yOffset = 0;
            if (yOffset > ((height << 5) - screen.height))      /*  BORDURES */
                    yOffset = ((height << 5) - screen.height);      /*  BORDURES */

            screen.setOffset(xOffset, yOffset);

            for (int y = (yOffset >> 5); y < (yOffset + screen.height >> 5) + 1; y++) {  /* TAILLE DU RENDU EN LONGUEUR */
                    for (int x = (xOffset >> 5); x < (xOffset + screen.width >> 5) + 1; x++) {  /* TAILLE DU RENDU EN LARGEUR */
                            getTile(x, y).render(screen, this, x << 5, y << 5);
                    }
            }
    }
    
    
    public void renderTilesUP(Screen screen, int xOffset, int yOffset) {
            if (xOffset < 0)
                    xOffset = 0;
            if (xOffset > ((width << 5) - screen.width))       /*  BORDURES */
                    xOffset = ((width << 5) - screen.width);    /*  BORDURES */
            if (yOffset < 0)
                    yOffset = 0;
            if (yOffset > ((height << 5) - screen.height))      /*  BORDURES */
                    yOffset = ((height << 5) - screen.height);      /*  BORDURES */

            screen.setOffset(xOffset, yOffset);

            for (int y = (yOffset >> 5); y < (yOffset + screen.height >> 5) + 1; y++) {  /* TAILLE DU RENDU EN LONGUEUR */
                    for (int x = (xOffset >> 5); x < (xOffset + screen.width >> 5) + 1; x++) {  /* TAILLE DU RENDU EN LARGEUR */
                            getTileUP(x, y).render(screen, this, x << 5, y << 5);
                    }
            }
            
            
    }
        
    public void renderEntities(Screen screen){
        for (Entity e : entities){
            e.render(screen);
            
      
        }
    }
    
    public boolean attackEntities(Screen screen,String name, int xMin , int xMax, int yMin, int yMax, int damage) {
        
        attacked = false;
        for (Entity e : entities){
            
            destroy = e.isAttacked( name,  xMin ,  xMax, yMin,yMax, damage );
            if (attacked== false && destroy==false ){
                attacked= false;
            }
            else {
                attacked = true;
            }
        }
        if (entities.isEmpty()){
            return false;
        }
        return attacked;
        
    }
 
    public Tile getTile(int x, int y) {
        if (0 > x || x >= width || 0 > y || y >= height  )
            return Tile.VOID;
        if(Tile.tiles[tiles[x + y * width]].isEmitter()== true){
            if (Tile.tiles[tiles[x + y * width]].getId()>235 || Tile.tiles[tiles[x + y * width]].getId()<200 ){
                return Tile.Grass1;
            } else {
                return Tile.pavedroadhorizontal1;
            }     
        } 
            
        return Tile.tiles[tiles[x + y * width]];
    }
    
    public Tile getTileUP(int x, int y) {
        if ((0 > x || x >= width || 0 > y || y >= height) )
            return Tile.VOID;
        if (Tile.tiles[tiles[x + y * width]].isEmitter()== false)
            return Tile.VOID;
        return Tile.tiles[tiles[x + y * width]];
    }
    
    
    public void addEntity(Entity entity){
        this.entities.add(entity);
    }

  
}