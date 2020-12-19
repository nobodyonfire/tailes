package tiles;

import Level.Level;
import gfx.Screen;


public abstract class Tile {
 
	public static final Tile[] tiles = new Tile[512];
	public static final Tile VOID = new BasicSolidTile(0, 0, 0,0xFF000000);
	public static final Tile STONE = new BasicSolidTile(1, 1, 0,0xFF555555);
	public static final Tile GRASS = new BasicTile(2, 2, 0,0xFF00FF00);
        
        
        public static final Tile WATER1 = new AnimatedTile(3,new int[][]{{0,5},{0,5},{0,5},{1,5},{2,5},{3,5},{2,5},{1,5}},0xFF0000FF,200);
        public static final Tile WATER = new BasicTile(4,0,5,0xFF0000FE);
        public static final Tile Grass1 = new BasicTile(5,4,0,0xFF00FE00);
                
        
        public static final Tile lamptop = new AnimatedSolidTile(10,new int[][]{{2,7},{3,7},{4,7}},0xFF0A0000,300); /*  [10/0/0]  */
        public static final Tile lampbottom = new BasicSolidTile(11, 2, 8,0xFF0B0000); /*  [11/0/0]  */
        
        public static final Tile Brazier = new AnimatedSolidTile(12,new int[][]{{0,7},{1,7},{0,8},{1,8}},0xFF0C0000,300); /*  [12/0/0]  */
        
        
        public static final Tile benchrighttop = new BasicSolidTile(13,5,7,0xFF0D0000); /*  [13/0/0]  */
        public static final Tile benchrightbottom = new BasicSolidTile(14,5,8,0xFF0E0000); /*  [14/0/0]  */
        
        public static final Tile benchlefttop = new BasicSolidTile(15,8,7,0xFF0F0000); /*  [15/0/0]  */
        public static final Tile benchleftbottom = new BasicSolidTile(16,8,8,0xFF100000); /*  [15/0/0]  */
        
        public static final Tile benchltoptop = new BasicSolidTile(17,6,7,0xFF110000); /*  [16/0/0]  */
        public static final Tile benchtopbottom = new BasicSolidTile(18,7,7,0xFF120000); /*  [17/0/0]  */
        
        public static final Tile benchlbottomtop = new BasicSolidTile(19,6,8,0xFF130000); /*  [18/0/0]  */
        public static final Tile benchbottombottom = new BasicSolidTile(20,7,8,0xFF140000); /*  [19/0/0]  */
        
        public static final Tile tree11 = new BasicSolidTile (21,2,9,0xFF150000); 
        public static final Tile tree21 = new BasicSolidTile (22,3,9,0xFF160000);
        public static final Tile tree31 = new BasicSolidTile (23,2,10,0xFF170000);
        public static final Tile tree41 = new BasicSolidTile (24,3,10,0xFF180000);
        public static final Tile tree51 = new BasicSolidTile (25,2,11,0xFF190000);
        public static final Tile tree61 = new BasicSolidTile (26,3,11,0xFF1A0000);  
        public static final Tile tree71 = new BasicSolidTile (27,2,12,0xFF1B0000);
        public static final Tile tree81 = new BasicSolidTile (28,3,12,0xFF1C0000);  
        
        
        
        
        public static final Tile Cliff1 = new BasicSolidTile(100,3,0,0xFF010000);
        public static final Tile Cliff2 = new BasicSolidTile(101,5,0,0xFF010001);
        public static final Tile Cliffendup = new BasicSolidTile(102,3,1,0xFF020000);
        public static final Tile Cliffendleft = new BasicSolidTile(103,4,1,0xFF030000);
        public static final Tile Cliffend= new BasicSolidTile(104,2,1,0xFF040000);
        
        public static final Tile roadhorizontal1 = new BasicTile(64,0,2,0xFF400000); /*  [64/0/0]  */
        public static final Tile roadhorizontal2 = new BasicTile(65,1,2,0xFF410000); /*  [65/0/0]  */
        
        
        public static final Tile roadvertical1 = new BasicTile(66,2,2,0xFF420000); /*  [66/0/0]  */
        public static final Tile roadvertical2 = new BasicTile(67,3,2,0xFF430000); /*  [67/0/0]  */
        
        public static final Tile roadcrossings = new BasicTile(68,4,2,0xFF440000); /*  [68/0/0]  */
        public static final Tile roadcrossingn = new BasicTile(69,5,2,0xFF450000); /*  [69/0/0]  */
        public static final Tile roadcrossingo = new BasicTile(70,6,2,0xFF460000); /*  [70/0/0]  */
        public static final Tile roadcrossinge = new BasicTile(71,7,2,0xFF470000); /*  [71/0/0]  */
        
        public static final Tile pavedroadhorizontal1 = new BasicTile(96,0,3,0xFF600000); /*  [96/0/0]  */
        public static final Tile pavedroadhorizontal2 = new BasicTile(97,1,3,0xFF610000); /*  [97/0/0]  */
        
        public static final Tile pavedroadvertical1 = new BasicTile(98,2,3,0xFF620000); /*  [98/0/0]  */
        public static final Tile pavedroadvertical2 = new BasicTile(99,3,3,0xFF630000); /*  [99/0/0]  */
        
        public static final Tile pavedroadhorizontalup1 = new BasicTile(110,0,4,0xFF800000); /*  [128/0/0]  */
        public static final Tile pavedroadhorizontalup2 = new BasicTile(111,1,4,0xFF810000); /*  [129/0/0]  */
        
        public static final Tile pavedroadhorizontaldown1 = new BasicTile(112,2,4,0xFF820000); /*  [130/0/0]  */
        public static final Tile pavedroadhorizontaldown2 = new BasicTile(113,3,4,0xFF830000); /*  [131/0/0]  */
        
        public static final Tile pavedroadverticalleft1 = new BasicTile(114,4,4,0xFF840000); /*  [132/0/0]  */
        public static final Tile pavedroadverticalleft2 = new BasicTile(115,5,4,0xFF850000); /*  [133/0/0]  */
        
        public static final Tile pavedroadverticalright1 = new BasicTile(116,6,4,0xFF860000); /*  [134/0/0]  */
        public static final Tile pavedroadverticalright2 = new BasicTile(117,7,4,0xFF870000); /*  [135/0/0]  */
        
        public static final Tile pavedroadcoin1 = new BasicTile(118,4,3,0xFF880000); /*  [136/0/0]  */
        public static final Tile pavedroadcoin2 = new BasicTile(119,7,3,0xFF890000); /*  [137/0/0]  */
        public static final Tile pavedroadcoin3 = new BasicTile(120,5,3,0xFF8A0000); /*  [138/0/0]  */
        public static final Tile pavedroadcoin4 = new BasicTile(121,6,3,0xFF8B0000); /*  [139/0/0]  */
        
        public static final Tile pavedroadcoininv1 = new BasicTile(122,8,4,0xFF8C0000); /*  [140/0/0]  */
        public static final Tile pavedroadcoininv2 = new BasicTile(123,9,4,0xFF8D0000); /*  [141/0/0]  */
        public static final Tile pavedroadcoininv3 = new BasicTile(124,10,4,0xFF8E0000); /*  [142/0/0]  */
        public static final Tile pavedroadcoininv4 = new BasicTile(125,11,4,0xFF8F0000); /*  [143/0/0]  */
        
        
        
  
        
        
        
        
	protected int id;
	protected boolean solid;
    	protected boolean emitter;
        private int levelColour;
        
	public Tile(int id, boolean isSolid, boolean isEmitter,int levelColour) {
		this.id = id;
                
		if (tiles[id] != null) {
			throw new RuntimeException("Duplicate tile id on" + id);
		}
		this.solid = isSolid;
		this.emitter = isEmitter;
                this.levelColour = levelColour;
		tiles[id] = this;
   
	}
 
	public int getId() {
		return id;
	}
 
	public boolean isSolid() {
		return solid;
	}
 
	public boolean isEmitter() {
		return emitter;
	}
        
        public int getlevelColour(){
            return levelColour;
        }
 
        public abstract void tick();
        
	public abstract void render(Screen screen, Level level, int x, int y);
        
}