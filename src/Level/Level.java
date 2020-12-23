package Level;

import entities.Entity;
import gfx.Screen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        for (Entity e : entities){
            e.tick();
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
    
    public void attackEntities(Screen screen,String name, int xMin , int xMax, int yMin, int yMax, int damage) {
        for (Entity e : entities){
            e.isAttacked( name,  xMin ,  xMax, yMin,yMax, damage );
        }
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