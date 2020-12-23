package tiles;


public class BasicSolidTile extends BasicTile {
    
    public BasicSolidTile(int id , int x , int y ,  int levelColour,boolean emitter){
        super(id,x,y,levelColour,emitter);
        this.solid= true;
    }
    
}
