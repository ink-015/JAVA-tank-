package game2025;

/** 强化弹道具：捡到后子弹更快更大、伤害翻倍 */
public class PropPower extends Gameprop {

	public PropPower(int x, int y) {
		super(x, y, Icons.POWER_COL, Icons.POWER_ROW);
	}

	@Override
	public Decorator createBuff(Spirite tank) { return new DcrPower(tank); }

	@Override
	public String getName() { return "强化弹"; }
}
