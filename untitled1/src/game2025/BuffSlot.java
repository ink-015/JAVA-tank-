package game2025;

/**
 * 一个正在生效的 buff：记住是哪一层装饰器、还能撑多少帧。
 * 时间到了 GameFrame 会把这一层从装饰链上摘掉。
 */
public class BuffSlot {

	private final Decorator decorator;
	private final int total;
	private int remain;

	public BuffSlot(Decorator decorator, int frames) {
		this.decorator = decorator;
		this.total = frames;
		this.remain = frames;
	}

	public Decorator getDecorator() { return decorator; }

	public void tick() { remain--; }

	public boolean isExpired() { return remain <= 0; }

	public int getRemain() { return remain; }

	public int getTotal() { return total; }

	/** 剩余比例，画进度条用 */
	public double getRatio() {
		return total <= 0 ? 0 : (double) remain / total;
	}
}
