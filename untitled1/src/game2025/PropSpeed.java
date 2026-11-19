package game2025;

/** 加速道具：捡到后一段时间内移动速度翻倍 */
public class PropSpeed extends Gameprop {

	public PropSpeed(int x, int y) {
		super(x, y, Icons.SPEED_COL, Icons.SPEED_ROW);
	}

	@Override
	public Decorator createBuff(Spirite tank) { return new DcrSpeed(tank); }

	@Override
	public String getName() { return "加速"; }
}
