package game2025;

/** 护盾道具：捡到后获得一次免伤 */
public class PropShield extends Gameprop {

	public PropShield(int x, int y) {
		super(x, y, Icons.SHIELD_COL, Icons.SHIELD_ROW);
	}

	@Override
	public Decorator createBuff(Spirite tank) { return new DcrShield(tank); }

	@Override
	public String getName() { return "护盾"; }
}
