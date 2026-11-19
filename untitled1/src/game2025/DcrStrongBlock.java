package game2025;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 加固砖：外面加了一层钢板，要先把钢板打掉（armor 次），才能伤到里面的砖。
 *
 * 装饰器不修改被装饰对象，只在自己身上记 armor，hit() 里先消耗自己的 armor。
 */
public class DcrStrongBlock extends BlockDecorator {

	/** 还剩几层钢板 */
	private int armor;

	public DcrStrongBlock(Block block) {
		this(block, 2);
	}

	public DcrStrongBlock(Block block, int armor) {
		super(block);
		this.armor = armor;
	}

	public int getArmor() { return armor; }

	/** 总血量 = 里面砖块的血 + 钢板层数 */
	@Override
	public int getHp() {
		return block.getHp() + armor;
	}

	@Override
	public boolean isDestroyable() {
		return true;
	}

	@Override
	public boolean hit() {
		if (armor > 0) {
			armor--;
			return false;   // 这一枪只打掉一层钢板
		}
		return block.hit();
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		// 半透明钢板
		g.setColor(new Color(150, 175, 205, 130));
		g.fillRect(x, y, 34, 34);
		// 边框 + 四角铆钉
		g.setColor(armor > 0 ? new Color(225, 235, 250) : new Color(110, 125, 145));
		g.drawRect(x, y, 33, 33);
		g.drawRect(x + 2, y + 2, 29, 29);
		g.fillOval(x + 3, y + 3, 4, 4);
		g.fillOval(x + 27, y + 3, 4, 4);
		g.fillOval(x + 3, y + 27, 4, 4);
		g.fillOval(x + 27, y + 27, 4, 4);
		// 剩余钢板层数
		g.setColor(new Color(255, 240, 120));
		for (int i = 0; i < armor; i++) {
			g.fillRect(x + 5 + i * 6, y + 15, 4, 4);
		}
	}
}
