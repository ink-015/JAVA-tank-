package game2025;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * 增益箱：打碎特殊砖块 / 击败敌人时可能出现，捡到随机给一个正面 buff。
 *
 * 它不直接决定给什么，而是转手让随机的具体道具去生成装饰器，
 * 这样以后新增道具类型，这个箱子一行都不用改。
 */
public class GamePropUp extends Gameprop {

	public GamePropUp(int x, int y) {
		super(x, y, -1, -1);       // 图标自己画，不用贴图集
	}

	@Override
	public Decorator createBuff(Spirite tank) {
		Gameprop prop;
		do {
			prop = Gameprop.randomProp(x, y);
		} while (prop.getLifeBonus() > 0);      // 只挑会生成装饰器的道具
		return prop.createBuff(tank);
	}

	@Override
	public String getName() { return "增益箱"; }

	/** 画一个金色宝箱，中间一个问号 */
	@Override
	public void draw(Graphics g) {
		if (life < 90 && (life / 6) % 2 == 0) {
			return;
		}
		g.setColor(new Color(70, 45, 10, 200));
		g.fillRoundRect(x + 3, y + 5, 28, 25, 6, 6);
		g.setColor(new Color(230, 175, 55));
		g.fillRoundRect(x + 4, y + 6, 26, 23, 5, 5);
		g.setColor(new Color(120, 85, 20));
		g.drawRoundRect(x + 4, y + 6, 25, 22, 5, 5);
		g.drawLine(x + 4, y + 15, x + 29, y + 15);

		g.setColor(new Color(255, 250, 225));
		g.setFont(new Font("Consolas", Font.BOLD, 14));
		g.drawString("?", x + 14, y + 25);
	}
}
