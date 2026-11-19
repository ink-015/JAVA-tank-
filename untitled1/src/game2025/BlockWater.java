package game2025;

import java.awt.Graphics;

/** 水：不挡子弹，子弹可以直接飞过去 */
public class BlockWater extends Block {

	public BlockWater(int x, int y) {
		super(x, y);
		hp = -1;
		blocksBullet = false;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Resourse.getImg(), x, y, x+34, y+34,
				0    , 7*34,
				34 , 8*34, null);
	}
}
