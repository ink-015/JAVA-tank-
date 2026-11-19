package game2025;

import java.awt.Graphics;
import java.util.List;

/**
 * 方块装饰器（Decorator 模式的抽象装饰类）。
 *
 * 它本身也是一块 Block，所以可以继续放到地图里、继续被别的装饰器包起来；
 * 默认把所有请求原样转发给被装饰的方块，子类只改写自己关心的那一个方法。
 *
 * 用法：
 *     Block b = new BlockBrick(x, y);          // 普通砖
 *     b = new DcrStrongBlock(b);               // 加固砖（外面包一层）
 *     b = new DcrBombBlock(b);                 // 又变成会炸的加固砖
 * 这样“砖块种类”是组合出来的，不是一个一个继承出来的。
 */
public abstract class BlockDecorator extends Block {

	/** 被装饰的方块 */
	protected Block block;

	public BlockDecorator(Block block) {
		super(block.getX(), block.getY());
		this.block = block;
	}

	public Block getInner() { return block; }

	@Override
	public void draw(Graphics g) { block.draw(g); }

	@Override
	public int getHp() { return block.getHp(); }

	@Override
	public boolean isDestroyable() { return block.isDestroyable(); }

	@Override
	public boolean isBlocksBullet() { return block.isBlocksBullet(); }

	@Override
	public int getExplodeRadius() { return block.getExplodeRadius(); }

	@Override
	public boolean hit() { return block.hit(); }

	@Override
	public List<Gameprop> dropItems() { return block.dropItems(); }
}
