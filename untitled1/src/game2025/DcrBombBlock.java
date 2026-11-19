package game2025;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * 炸药砖：被打碎的瞬间会爆炸，炸掉周围一圈砖块，并且一定掉一个道具。
 * 爆炸半径用 getExplodeRadius() 暴露给 GameFrame，让它去处理伤害。
 */
public class DcrBombBlock extends BlockDecorator {

	public DcrBombBlock(Block block) {
		super(block);
	}

	@Override
	public int getExplodeRadius() {
		return 80;
	}

	/** 炸开以后多掉一个道具 */
	@Override
	public List<Gameprop> dropItems() {
		List<Gameprop> list = new ArrayList<Gameprop>(block.dropItems());
		list.add(Gameprop.randomProp(x, y));
		return list;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		// 红色炸药桶
		g.setColor(new Color(35, 25, 25, 150));
		g.fillOval(x + 8, y + 9, 18, 18);
		g.setColor(new Color(230, 60, 60));
		g.fillOval(x + 9, y + 10, 16, 16);
		g.setColor(new Color(255, 220, 120));
		g.drawLine(x + 17, y + 11, x + 23, y + 5);   // 引线
		g.fillOval(x + 22, y + 3, 4, 4);             // 火花
	}
}
