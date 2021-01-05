package tiles;


public class BasicSolidTile extends BasicTile {
    
    public BasicSolidTile(int id , int x , int y ,  int levelColour,boolean emitter, int solidType){
        super(id,x,y,levelColour,emitter);
        this.solid= true;
        this.solidType= solidType;
    }
    
}
