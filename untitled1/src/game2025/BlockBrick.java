package game2025;

import java.awt.Graphics;

/** 普通砖块：一枪就碎 */
public class BlockBrick extends Block {

	public BlockBrick(int x, int y) {
		super(x, y);
		hp = 1;
		blocksBullet = true;
	}

	@Override
	public void draw(Graphics g) {
		// 贴图集里这块砖只有 17x17，铺四次拼成一整格
		g.drawImage(Resourse.getImg(), x, y, x+17, y+17, 18*34 , 5*34,18*34+17 , 5*34+17, null);
		g.drawImage(Resourse.getImg(), x+17, y, x+34, y+17, 18*34 , 5*34,18*34+17 , 5*34+17, null);
		g.drawImage(Resourse.getImg(), x, y+17, x+17, y+34, 18*34 , 5*34,18*34+17 , 5*34+17, null);
		g.drawImage(Resourse.getImg(), x+17, y+17, x+34, y+34, 18*34 , 5*34,18*34+17 , 5*34+17, null);
	}
}
