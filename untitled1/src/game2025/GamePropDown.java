package game2025;

/**
 * 陷阱箱：敌人被击败时有小概率掉出来，踩到会被减速一段时间。
 *
 * 装饰器不仅能加好处，也能加坏处——这里和增益箱用同一套机制，
 * 只是套上去的装饰器换成了 DcrSlow。
 */
public class GamePropDown extends Gameprop {

	public GamePropDown(int x, int y) {
		super(x, y, Icons.TRAP_COL, Icons.TRAP_ROW);
	}

	@Override
	public Decorator createBuff(Spirite tank) {
		return new DcrSlow(tank);
	}

	@Override
	public String getName() { return "陷阱"; }
}
