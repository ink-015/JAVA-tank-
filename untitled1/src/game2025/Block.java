package game2025;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * 地图上的一个方块。
 *
 * 为了支持“不同砖块”，这里加了三个可被子类和装饰器改写的属性：
 *   hp            还能挨几枪（-1 表示打不烂，比如石头）
 *   blocksBullet  是否挡子弹（水和草不挡）
 *   explodeRadius 被打碎时的爆炸半径（0 表示不爆炸）
 * 具体的砖块由 BlockFactory 根据地图文件里的类型名创建，可以叠加装饰器。
 */
public abstract class Block extends VisibleItem {

	/** -1 表示不可摧毁 */
	protected int hp = -1;
	/** 是否阻挡子弹 */
	protected boolean blocksBullet = true;

	public Block(int x, int y) {
		super(x, y);
	}

	public int getHp() { return hp; }

	public boolean isDestroyable() { return hp > 0; }

	public boolean isBlocksBullet() { return blocksBullet; }

	public int getExplodeRadius() { return 0; }

	/**
	 * 挨了一枪。返回 true 表示这一下被打碎了。
	 * 不可摧毁的方块永远返回 false。
	 */
	public boolean hit() {
		if (!isDestroyable()) {
			return false;
		}
		hp--;
		return hp <= 0;
	}

	/** 被打碎时掉出来的道具，默认不掉 */
	public List<Gameprop> dropItems() {
		return new ArrayList<Gameprop>();
	}

	/** 子弹命中特效的小工具：把一块方块标成“已碎”（子类可覆盖外观） */
	public abstract void draw(Graphics g);
}
