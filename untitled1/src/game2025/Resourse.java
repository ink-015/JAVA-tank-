package game2025;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 资源管理器（单例模式）。
 *
 * 相比原版修了两个问题：
 * 1. 原来只有 getInstance() 会加载图片，而 Tank / NPC 等地方直接调用静态方法 getImg()，
 *    一旦它先被调用，img 还是 null，画图时就会抛 NullPointerException。
 *    现在 getImg() 内部保证单例已经初始化。
 * 2. 原来写死 new File("insect_sprite.png")，只有在“工程根目录”下启动才找得到；
 *    在 src 目录下启动就报 FileNotFoundException。现在会依次尝试几个常见目录。
 */
public class Resourse {
	/** 贴图集每格的边长 */
	public static final int TILE = 34;

	/** 图片可能所在的相对位置（覆盖 IDEA 默认工作目录、src 目录、上一级目录三种情况） */
	private static final String[] IMG_CANDIDATES = {
			"insect_sprite.png",
			"src/insect_sprite.png",
			"../insect_sprite.png",
			"untitled1/insect_sprite.png"
	};

	private static Image img;
	private static Resourse instance = null;

	private Resourse() {
		File f = findFile(IMG_CANDIDATES);
		if (f == null) {
			System.err.println("警告：找不到 insect_sprite.png，检查一下工作目录。");
			return;
		}
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Resourse getInstance() {
		if (instance == null) {
			instance = new Resourse();
		}
		return instance;
	}

	/** 保证图片已经加载过再返回，避免空指针 */
	public static Image getImg() {
		if (img == null) {
			getInstance();
		}
		return img;
	}

	/** 依次尝试若干相对路径，返回第一个存在的文件；都找不到返回 null */
	public static File findFile(String... candidates) {
		for (String name : candidates) {
			File f = new File(name);
			if (f.isFile()) {
				return f;
			}
		}
		// 再退一步：从当前目录逐级往上找
		File dir = new File(".").getAbsoluteFile();
		for (int i = 0; i < 4 && dir != null; i++) {
			for (String name : candidates) {
				File f = new File(dir, name);
				if (f.isFile()) {
					return f;
				}
			}
			dir = dir.getParentFile();
		}
		return null;
	}

	public int getDis(VisibleItem item1, VisibleItem item2) {
		int d1 = item1.getX() - item2.getX();
		d1 = d1 * d1;
		int d2 = item1.getY() - item2.getY();
		d2 = d2 * d2;
		return (int) Math.sqrt(d1 + d2);
	}

	/** 把贴图集第 (col,row) 格画到屏幕 (px,py) 处，边长 size。UI 图标用 */
	public static void drawTile(Graphics g, int col, int row, int px, int py, int size) {
		g.drawImage(getImg(), px, py, px + size, py + size,
				col * TILE, row * TILE, col * TILE + TILE, row * TILE + TILE, null);
	}
}
