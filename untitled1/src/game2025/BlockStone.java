package game2025;

import java.awt.Graphics;

/** 石头：子弹打不烂的地形边界 */
public class BlockStone extends Block {

	public BlockStone(int x, int y) {
		super(x, y);
		hp = -1;
		blocksBullet = true;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Resourse.getImg(), x, y, x+17, y+17,      0 , 6*34,17 , 6*34+17, null);
		g.drawImage(Resourse.getImg(), x+17, y, x+34, y+17,   0 , 6*34,17 , 6*34+17, null);
		g.drawImage(Resourse.getImg(), x, y+17, x+17, y+34,   0 , 6*34,17 , 6*34+17, null);
		g.drawImage(Resourse.getImg(), x+17, y+17, x+34, y+34,0 , 6*34,17 , 6*34+17, null);
	}
}
