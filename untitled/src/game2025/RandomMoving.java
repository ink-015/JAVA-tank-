package game2025;

import java.util.Random;

public class RandomMoving implements IStrategy {
	protected Random rnd = new Random();
	public DIR calculateDir(int x1,int y1,int x2,int y2) {
		if (rnd.nextInt() % 10 == 1) {
			int index = Math.abs(rnd.nextInt() % 4);
			return DIR.values()[index];
		}
		return null;
	}
}
