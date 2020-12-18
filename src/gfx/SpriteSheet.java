package gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
    
    public String path;
    public int width;
    public int height;
    
    public int[] pixels;
    public int[] pixelsColour;
      
    public SpriteSheet(String path){
        BufferedImage image = null;
        try{
           image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path)); 
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
        if (image == null ){
            return;
        }
        
        this.path=path;
        this.width =image.getWidth();
        this.height=image.getHeight();
       
       
        
        pixels= image.getRGB(0, 0, width, width, null , 0, width);
        

    
       for(int i =0; i<pixels.length;i++){   
          pixels[i]=(image.getRGB(i%(32*32),i/(32*32)));
       
        }    

       
        int blue;
        int green;
        int red;
        int alpha;
        
        for( int i=0; i<165;i++){
            red = pixels[i] & 0xff;
            green = (pixels[i] & 0xff00) >> 8;
            blue = (pixels[i] & 0xff0000) >> 16;
            alpha = (pixels[i] & 0xff000000) >> 24;
        }
        
 
    }
}
