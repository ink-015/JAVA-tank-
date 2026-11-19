package game2025;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * 惊喜砖（宝藏砖）：里面藏着道具，打碎必掉两个。
 * 这样“特殊砖块产生掉落物”就是靠装饰器挂上去的，不用改 BlockBrick 一行代码。
 */
public class DcrGiftBlock extends BlockDecorator {

	public DcrGiftBlock(Block block) {
		super(block);
	}

	@Override
	public List<Gameprop> dropItems() {
		List<Gameprop> list = new ArrayList<Gameprop>(block.dropItems());
		list.add(Gameprop.randomProp(x, y));
		list.add(Gameprop.randomProp(x, y));
		return list;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		// 金色边框
		g.setColor(new Color(255, 205, 60));
		g.drawRect(x, y, 33, 33);
		g.drawRect(x + 1, y + 1, 31, 31);
		// 中间的加号，表示“有东西”
		g.setColor(new Color(255, 235, 120));
		g.fillRect(x + 14, y + 8, 6, 18);
		g.fillRect(x + 8, y + 14, 18, 6);
	}
}
