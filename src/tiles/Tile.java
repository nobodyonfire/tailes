package tiles;

import Level.Level;
import gfx.Screen;


public abstract class Tile {
 
	public static final Tile[] tiles = new Tile[512];
	public static final Tile VOID = new BasicTile(0, 0, 0,0xFF000000,false);
	public static final Tile STONE = new BasicSolidTile(1, 1, 0,0xFF555555,false);
	public static final Tile GRASS = new BasicTile(2, 2, 0,0xFF00FF00,false);
        
        
        public static final Tile WATER1 = new AnimatedTile(3,new int[][]{{0,5},{0,5},{0,5},{1,5},{2,5},{3,5},{2,5},{1,5}},0xFF0000FF,200,false);
        public static final Tile WATER = new BasicTile(4,0,5,0xFF0000FE,false);
        public static final Tile Grass1 = new BasicTile(5,4,0,0xFF00FE00,false);
                
        
        public static final Tile lamptop = new AnimatedSolidTile(10,new int[][]{{2,7},{3,7},{4,7}},0xFF0A0000,300,true); /*  [10/0/0]  */
        public static final Tile lampbottom = new BasicSolidTile(11, 2, 8,0xFF0B0000,false); /*  [11/0/0]  */
        
        public static final Tile Brazier = new AnimatedSolidTile(12,new int[][]{{0,7},{1,7},{0,8},{1,8}},0xFF0C0000,300,false); /*  [12/0/0]  */
        
        
        public static final Tile benchrighttop = new BasicSolidTile(13,5,7,0xFF0D0000,false); /*  [13/0/0]  */
        public static final Tile benchrightbottom = new BasicSolidTile(14,5,8,0xFF0E0000,false); /*  [14/0/0]  */
        
        public static final Tile benchlefttop = new BasicSolidTile(15,8,7,0xFF0F0000,false); /*  [15/0/0]  */
        public static final Tile benchleftbottom = new BasicSolidTile(16,8,8,0xFF100000,false); /*  [16/0/0]  */
        
        public static final Tile benchltoptop = new BasicSolidTile(17,6,7,0xFF110000,false); /*  [17/0/0]  */
        public static final Tile benchtopbottom = new BasicSolidTile(18,7,7,0xFF120000,false); /*  [18/0/0]  */
        
        public static final Tile benchlbottomtop = new BasicSolidTile(19,6,8,0xFF130000,false); /*  [19/0/0]  */
        public static final Tile benchbottombottom = new BasicSolidTile(20,7,8,0xFF140000,false); /*  [20/0/0]  */
        
        public static final Tile tree11 = new BasicSolidTile (21,2,9,0xFF150000,true); 
        public static final Tile tree21 = new BasicSolidTile (22,3,9,0xFF160000,true);
        public static final Tile tree31 = new BasicSolidTile (23,2,10,0xFF170000,true);
        public static final Tile tree41 = new BasicSolidTile (24,3,10,0xFF180000,true);
        public static final Tile tree51 = new BasicSolidTile (25,2,11,0xFF190000,false);
        public static final Tile tree61 = new BasicSolidTile (26,3,11,0xFF1A0000,false);  
        public static final Tile tree71 = new BasicTile (27,2,12,0xFF1B0000,false);
        public static final Tile tree81 = new BasicTile (28,3,12,0xFF1C0000,false);  
        
        public static final Tile flowerpot1 = new BasicSolidTile (29,9,7,0xFF1D0000,false);
        public static final Tile flowerpot2 = new BasicSolidTile (30,9,8,0xFF1E0000,false);
        public static final Tile flowerpot3 = new BasicSolidTile (31,10,7,0xFF1F0000,false);
        
        public static final Tile muralwall = new BasicTile (39,8,2,0xFF270000,false);
        public static final Tile muralmiddle = new BasicTile (40,9,0,0xFF280000,false);
        public static final Tile muralLeft = new BasicTile (41,9,1,0xFF290000,false);
        public static final Tile muralRight = new BasicTile (42,10,1,0xFF2A0000,false);
        public static final Tile muralGrass = new BasicTile (43,11,1,0xFF2B0000,false);
        public static final Tile muralTL = new BasicTile (44,9,2,0xFF2C0000,false);
        public static final Tile muralTR = new BasicTile (45,10,2,0xFF2D0000,false);
        public static final Tile muralBL = new BasicTile (46,9,3,0xFF2E0000,false);
        public static final Tile muralBR = new BasicTile (47,10,3,0xFF2F0000,false);

        public static final Tile muralITL = new BasicTile (48,11,2,0xFF300000,false);
        public static final Tile muralITR = new BasicTile (49,12,2,0xFF310000,false);
        public static final Tile muralIBL = new BasicTile (50,11,3,0xFF320000,false);
        public static final Tile muralIBR = new BasicTile (51,12,3,0xFF330000,false);
        public static final Tile muralstairtop = new BasicTile (52,13,2,0xFF340000,false);
        public static final Tile muralstairbottom = new BasicTile (53,13,3,0xFF350000,false);

        
        
        public static final Tile Cliff1 = new BasicSolidTile(100,3,0,0xFF010000,false);
        public static final Tile Cliff2 = new BasicSolidTile(101,5,0,0xFF010001,false);
        public static final Tile Cliffendup = new BasicSolidTile(102,3,1,0xFF020000,false);
        public static final Tile Cliffendleft = new BasicSolidTile(103,4,1,0xFF030000,false);
        public static final Tile Cliffend= new BasicSolidTile(104,2,1,0xFF040000,false);
        
        public static final Tile roadhorizontal1 = new BasicTile(64,0,2,0xFF400000,false); /*  [64/0/0]  */
        public static final Tile roadhorizontal2 = new BasicTile(65,1,2,0xFF410000,false); /*  [65/0/0]  */
        
        
        public static final Tile roadvertical1 = new BasicTile(66,2,2,0xFF420000,false); /*  [66/0/0]  */
        public static final Tile roadvertical2 = new BasicTile(67,3,2,0xFF430000,false); /*  [67/0/0]  */
        
        public static final Tile roadcrossings = new BasicTile(68,4,2,0xFF440000,false); /*  [68/0/0]  */
        public static final Tile roadcrossingn = new BasicTile(69,5,2,0xFF450000,false); /*  [69/0/0]  */
        public static final Tile roadcrossingo = new BasicTile(70,6,2,0xFF460000,false); /*  [70/0/0]  */
        public static final Tile roadcrossinge = new BasicTile(71,7,2,0xFF470000,false); /*  [71/0/0]  */
        
        public static final Tile sword = new BasicTile(72,0,9,0xFF480000,false); /*  [72/0/0]  */
        public static final Tile bow = new BasicTile(73,1,9,0xFF490000,false); /*  [73/0/0]  */
        
        public static final Tile pavedroadhorizontal1 = new BasicTile(96,0,3,0xFF600000,false); /*  [96/0/0]  */
        public static final Tile pavedroadhorizontal2 = new BasicTile(97,1,3,0xFF610000,false); /*  [97/0/0]  */
        
        public static final Tile pavedroadvertical1 = new BasicTile(98,2,3,0xFF620000,false); /*  [98/0/0]  */
        public static final Tile pavedroadvertical2 = new BasicTile(99,3,3,0xFF630000,false); /*  [99/0/0]  */
        
        public static final Tile pavedroadhorizontalup1 = new BasicTile(110,0,4,0xFF800000,false); /*  [128/0/0]  */
        public static final Tile pavedroadhorizontalup2 = new BasicTile(111,1,4,0xFF810000,false); /*  [129/0/0]  */
        
        public static final Tile pavedroadhorizontaldown1 = new BasicTile(112,2,4,0xFF820000,false); /*  [130/0/0]  */
        public static final Tile pavedroadhorizontaldown2 = new BasicTile(113,3,4,0xFF830000,false); /*  [131/0/0]  */
        
        public static final Tile pavedroadverticalleft1 = new BasicTile(114,4,4,0xFF840000,false); /*  [132/0/0]  */
        public static final Tile pavedroadverticalleft2 = new BasicTile(115,5,4,0xFF850000,false); /*  [133/0/0]  */
        
        public static final Tile pavedroadverticalright1 = new BasicTile(116,6,4,0xFF860000,false); /*  [134/0/0]  */
        public static final Tile pavedroadverticalright2 = new BasicTile(117,7,4,0xFF870000,false); /*  [135/0/0]  */
        
        public static final Tile pavedroadcoin1 = new BasicTile(118,4,3,0xFF880000,false); /*  [136/0/0]  */
        public static final Tile pavedroadcoin2 = new BasicTile(119,7,3,0xFF890000,false); /*  [137/0/0]  */
        public static final Tile pavedroadcoin3 = new BasicTile(120,5,3,0xFF8A0000,false); /*  [138/0/0]  */
        public static final Tile pavedroadcoin4 = new BasicTile(121,6,3,0xFF8B0000,false); /*  [139/0/0]  */
        
        public static final Tile pavedroadcoininv1 = new BasicTile(122,8,4,0xFF8C0000,false); /*  [140/0/0]  */
        public static final Tile pavedroadcoininv2 = new BasicTile(123,9,4,0xFF8D0000,false); /*  [141/0/0]  */
        public static final Tile pavedroadcoininv3 = new BasicTile(124,10,4,0xFF8E0000,false); /*  [142/0/0]  */
        public static final Tile pavedroadcoininv4 = new BasicTile(125,11,4,0xFF8F0000,false); /*  [143/0/0]  */
        
        public static final Tile pondfull = new AnimatedTile(130,new int[][]{{0,13},{0,14},{0,15},{0,14},{0,13},{0,13},{0,13}},0xFF000082,200,false); /*  [0/0/130]  */
        public static final Tile pondcornerbl = new AnimatedTile(131,new int[][]{{1,13},{1,14},{1,15},{1,14},{1,13},{1,13},{1,13}},0xFF000083,200,false); /*  [0/0/131]  */
        public static final Tile pondcornerbr = new AnimatedTile(132,new int[][]{{2,13},{2,14},{2,15},{2,14},{2,13},{2,13},{2,13}},0xFF000084,200,false);/*  [0/0/132]  */
        public static final Tile pondcornertl = new AnimatedTile(133,new int[][]{{3,13},{3,14},{3,15},{3,14},{3,13},{3,13},{3,13}},0xFF000085,200,false);/*  [0/0/133]  */
        public static final Tile pondcornertr = new AnimatedTile(134,new int[][]{{4,13},{4,14},{4,15},{4,14},{4,13},{4,13},{4,13}},0xFF000086,200,false);/*  [0/0/134]  */
        public static final Tile pondleft = new AnimatedTile(135,new int[][]{{5,13},{5,14},{5,15},{5,14},{5,13},{5,13},{5,13}},0xFF000087,200,false);/*  [0/0/135]  */
        public static final Tile pondright = new AnimatedTile(136,new int[][]{{6,13},{6,14},{6,15},{6,14},{6,13},{6,13},{6,13}},0xFF000088,200,false);/*  [0/0/136]  */
        
        public static final Tile pondbottom = new AnimatedTile(137,new int[][]{{7,13},{7,14},{7,15},{7,14},{7,13},{7,13},{7,13}},0xFF000089,200,false);/*  [0/0/137]  */
        public static final Tile pondtop = new AnimatedTile(138,new int[][]{{8,13},{8,14},{8,15},{8,14},{8,13},{8,13},{8,13}},0xFF00008A,200,false);/*  [0/0/138]  */
        
        public static final Tile pondfull2 = new AnimatedTile(139,new int[][]{{9,13},{9,14},{9,15},{9,14},{9,13},{9,13},{9,13}},0xFF00008B,200,false);/*  [0/0/139]  */
        
        public static final Tile pondcornerbr1 = new AnimatedTile(140,new int[][]{{10,13},{10,14},{10,15},{10,14},{10,13},{10,13},{10,13}},0xFF00008C,200,false);/*  [0/0/140]  */
        public static final Tile pondcornertr1 = new AnimatedTile(141,new int[][]{{11,13},{11,14},{11,15},{11,14},{11,13},{11,13},{11,13}},0xFF00008D,200,false);/*  [0/0/141]  */
        public static final Tile pondcornertl1 = new AnimatedTile(142,new int[][]{{12,13},{12,14},{12,15},{12,14},{12,13},{12,13},{12,13}},0xFF00008E,200,false);/*  [0/0/142]  */
        public static final Tile pondcornerbl1 = new AnimatedTile(143,new int[][]{{13,13},{13,14},{13,15},{13,14},{13,13},{13,13},{13,13}},0xFF00008F,200,false);/*  [0/0/143]  */
        
        public static final Tile ducktest = new AnimatedTile(144,new int[][]{{14,13},{14,14},{14,15},{15,13},{15,14},{15,15},{16,13},{16,14},{16,15},{17,13},{17,14},{17,15}},0xFF000090,200,false);/*  [0/0/144]  */
        
        public static final Tile fountain1 = new BasicTile(201,27,0,0xFF000100,true); /*  [0/1/0]  */
        public static final Tile fountain2 = new BasicTile(202,28,0,0xFF000200,true); /*  [0/2/0]  */
        public static final Tile fountain3 = new BasicTile(203,29,0,0xFF000300,true); /*  [0/3/0]  */
        public static final Tile fountain4 = new BasicTile(204,30,0,0xFF000400,true); /*  [0/4/0]  */
        public static final Tile fountain5 = new BasicTile(205,31,0,0xFF000500,true); /*  [0/5/0]  */
        public static final Tile fountain6 = new BasicTile(206,27,1,0xFF000600,true); /*  [0/6/0]  */
        public static final Tile fountain7 = new BasicTile(207,28,1,0xFF000700,true); /*  [0/7/0]  */
        public static final Tile fountain8 = new BasicTile(208,29,1,0xFF000800,true); /*  [0/8/0]  */
        public static final Tile fountain9 = new BasicTile(209,30,1,0xFF000900,true); /*  [0/9/0]  */
        public static final Tile fountain10 = new BasicTile(210,31,1,0xFF000A00,true); /*  [0/10/0]  */
        public static final Tile fountain11 = new AnimatedTile(211,new int[][]{{27,2},{27,9},{27,16},{27,9}},0xFF000B00,300,false); /*  [0/11/0]  */
        public static final Tile fountain12 = new AnimatedTile(212,new int[][]{{28,2},{28,9},{28,2}},0xFF000C00,300,true); /*  [0/12/0]  */
        public static final Tile fountain13 = new BasicSolidTile(213,29,2,0xFF000D00,true); /*  [0/13/0]  */
        public static final Tile fountain14 = new BasicSolidTile(214,30,2,0xFF000E00,true); /*  [0/14/0]  */
        public static final Tile fountain15 = new BasicTile(215,31,2,0xFF000F00,false); /*  [0/15/0]  */
        public static final Tile fountain16 = new AnimatedSolidTile(216,new int[][]{{27,3},{27,10},{27,17}},0xFF001000,300,false); /*  [0/16/0]  */
        public static final Tile fountain17 = new AnimatedSolidTile(217,new int[][]{{28,3},{28,10},{28,17}},0xFF001100,300,false); /*  [0/17/0]  */
        public static final Tile fountain18 = new AnimatedSolidTile(218,new int[][]{{29,3},{29,10},{29,17}},0xFF001200,300,false); /*  [0/18/0]  */
        public static final Tile fountain19 = new AnimatedSolidTile(219,new int[][]{{30,3},{30,10},{30,17}},0xFF001300,300,false); /*  [0/19/0]  */
        public static final Tile fountain20 = new AnimatedSolidTile(220,new int[][]{{31,3},{31,10},{31,17}},0xFF001400,300,false); /*  [0/20/0]  */
        public static final Tile fountain21 = new AnimatedSolidTile(221,new int[][]{{27,4},{27,11},{27,18}},0xFF001500,300,false); /*  [0/21/0]  */
        public static final Tile fountain22 = new AnimatedSolidTile(222,new int[][]{{28,4},{28,11},{28,18}},0xFF001600,300,false); /*  [0/22/0]  */
        public static final Tile fountain23 = new AnimatedSolidTile(223,new int[][]{{29,4},{29,11},{29,18}},0xFF001700,300,false); /*  [0/23/0]  */
        public static final Tile fountain24 = new AnimatedSolidTile(224,new int[][]{{30,4},{30,11},{30,18}},0xFF001800,300,false); /*  [0/24/0]  */
        public static final Tile fountain25 = new AnimatedSolidTile(225,new int[][]{{31,4},{31,11},{31,18}},0xFF001900,300,false); /*  [0/25/0]  */
        public static final Tile fountain26 = new BasicTile(226,27,5,0xFF001A00,false); /*  [0/26/0]  */
        public static final Tile fountain27 = new AnimatedSolidTile(227,new int[][]{{28,5},{28,12},{28,19}},0xFF001B00,300,false); /*  [0/27/0]  */
        public static final Tile fountain28 = new AnimatedSolidTile(228,new int[][]{{29,5},{29,12},{29,19}},0xFF001C00,300,false); /*  [0/28/0]  */
        public static final Tile fountain29 = new AnimatedSolidTile(229,new int[][]{{30,5},{30,12},{30,19}},0xFF001D00,300,false); /*  [0/29/0]  */
        public static final Tile fountain30 = new BasicTile(230,31,5,0xFF001E00,false); /*  [0/30/0]  */
        public static final Tile fountain31 = new BasicTile(231,27,6,0xFF001F00,false); /*  [0/31/0]  */
        public static final Tile fountain32 = new BasicTile(232,28,6,0xFF002000,false); /*  [0/32/0]  */
        public static final Tile fountain33 = new BasicTile(233,29,6,0xFF002100,false); /*  [0/33/0]  */
        public static final Tile fountain34 = new BasicTile(234,30,6,0xFF002200,false); /*  [0/34/0]  */
        public static final Tile fountain35 = new BasicTile(235,31,6,0xFF002300,false); /*  [0/35/0]  */
    
        
        public static final Tile outside1 = new BasicTile(236,19,2,0xFF002400,false); /*  [0/36/0]  */
        public static final Tile outside2 = new BasicTile(237,20,2,0xFF002500,false); /*  [0/37/0]  */
        public static final Tile outside3 = new BasicTile(238,21,2,0xFF002600,false); /*  [0/38/0]  */
        
        public static final Tile outside4 = new BasicTile(239,19,3,0xFF002700,false); /*  [0/36/0]  */
        public static final Tile outside5 = new BasicTile(240,20,3,0xFF002800,false); /*  [0/37/0]  */
        public static final Tile outside6 = new BasicTile(241,21,3,0xFF002900,false); /*  [0/38/0]  */
        
        public static final Tile outside7 = new BasicTile(242,19,4,0xFF002A00,false); /*  [0/36/0]  */
        public static final Tile outside8 = new BasicTile(243,20,4,0xFF002B00,false); /*  [0/37/0]  */
        public static final Tile outside9 = new BasicTile(244,21,4,0xFF002C00,false); /*  [0/38/0]  */
       
        public static final Tile outside10 = new BasicTile(245,19,5,0xFF002D00,false); /*  [0/36/0]  */
        public static final Tile outside11 = new BasicTile(246,20,5,0xFF002E00,false); /*  [0/37/0]  */
        public static final Tile outside12 = new BasicTile(247,21,5,0xFF002F00,false); /*  [0/38/0]  */
        
        public static final Tile outside13 = new BasicTile(248,19,6,0xFF003000,false); /*  [0/36/0]  */
        public static final Tile outside14 = new BasicTile(249,20,6,0xFF003100,false); /*  [0/37/0]  */
        public static final Tile outside15 = new BasicTile(250,21,6,0xFF003200,false); /*  [0/38/0]  */
        
        public static final Tile outside16 = new BasicTile(251,19,7,0xFF003300,false); /*  [0/36/0]  */
        public static final Tile outside17 = new BasicTile(252,20,7,0xFF003400,false); /*  [0/37/0]  */
        public static final Tile outside18 = new BasicTile(253,21,7,0xFF003500,false); /*  [0/38/0]  */

        public static final Tile outside19 = new BasicTile(254,19,8,0xFF003600,false); /*  [0/36/0]  */
        public static final Tile outside20 = new BasicTile(255,20,8,0xFF003700,false); /*  [0/37/0]  */
        public static final Tile outside21 = new BasicTile(256,21,8,0xFF003800,false); /*  [0/38/0]  */
        
        public static final Tile outside22 = new BasicTile(257,19,9,0xFF003900,false); /*  [0/36/0]  */
        public static final Tile outside23 = new BasicTile(258,20,9,0xFF003A00,false); /*  [0/37/0]  */
        public static final Tile outside24 = new BasicTile(259,21,9,0xFF003B00,false); /*  [0/38/0]  */
        
        
        
        
	protected int id;
	protected boolean solid;
    	protected boolean emitter =false;
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