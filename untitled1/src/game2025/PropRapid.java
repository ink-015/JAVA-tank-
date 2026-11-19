package game2025;

/** 连发道具：捡到后开火冷却大幅缩短 */
public class PropRapid extends Gameprop {

	public PropRapid(int x, int y) {
		super(x, y, Icons.RAPID_COL, Icons.RAPID_ROW);
	}

	@Override
	public Decorator createBuff(Spirite tank) { return new DcrRapid(tank); }

	@Override
	public String getName() { return "连发"; }
}
