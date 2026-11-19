package game2025;

import java.awt.Graphics;

/** 草丛：不挡子弹，相当于给对手做掩护 */
public class BlockGrass extends Block {

	public BlockGrass(int x, int y) {
		super(x, y);
		hp = -1;
		blocksBullet = false;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(Resourse.getImg(), x, y, x+34, y+34,
				4*34    , 7*34,
				5*34 , 8*34, null);
	}
}
