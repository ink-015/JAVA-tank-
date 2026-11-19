package game2025;

/**
 * 回血道具：直接加一条命，不是持续 buff，所以 createBuff() 返回 null。
 */
public class PropHeal extends Gameprop {

	public PropHeal(int x, int y) {
		super(x, y, Icons.HEAL_COL, Icons.HEAL_ROW);
	}

	@Override
	public int getLifeBonus() { return 1; }

	@Override
	public Decorator createBuff(Spirite tank) { return null; }

	@Override
	public String getName() { return "生命"; }
}
