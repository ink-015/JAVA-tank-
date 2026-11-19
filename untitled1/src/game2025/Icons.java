package game2025;

/**
 * 贴图集里用到的图标坐标（列, 行）。
 * 全部集中在类里，改图的时候就只改这一处。
 *
 * 注意：这些坐标是对着 insect_sprite.png 一格一格核对过的，
 * 第 4 行第 27~31 列依次是：红心、手枪、齿轮、锁、扳手。
 */
public class Icons {

	private Icons() {
	}

	// —— 掉落物 / buff（贴图第 4 行）——
	public static final int HEAL_COL = 27,   HEAL_ROW = 4;    // 红心
	public static final int RAPID_COL = 28,  RAPID_ROW = 4;   // 手枪
	public static final int SPEED_COL = 29,  SPEED_ROW = 4;   // 齿轮
	public static final int SHIELD_COL = 30, SHIELD_ROW = 4;  // 锁
	public static final int POWER_COL = 31,  POWER_ROW = 4;   // 扳手
	public static final int TRAP_COL = 22,   TRAP_ROW = 4;    // 爆炸星光
}
