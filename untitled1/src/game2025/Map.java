package game2025;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 地图：一堆方块。
 *
 * 相比原版的变化：
 * 1. 找文件不再只认当前工作目录（原来在 IDEA 默认工作目录下跑会报
 *    “map1.txt (系统找不到指定的文件)”），现在会依次到 src/、上一级目录里找。
 * 2. 读文件时跳过空行和 # 注释行。原版遇到空行会 strs[1] 数组越界。
 * 3. 方块类型交给 BlockFactory，支持 "brick+strong+bomb" 这种叠加写法。
 * 4. 新增 hitPoint / remove，让子弹可以打碎砖块。
 */
public class Map {

	private static final String[] MAP_CANDIDATES = {
			"map1.txt",
			"src/map1.txt",
			"../map1.txt",
			"untitled1/src/map1.txt"
	};

	private ArrayList<Block> blocks;

	public Map() {
		blocks = new ArrayList<Block>();
	}

	public void draw(Graphics g) {
		for (Block block : blocks) {
			block.draw(g);
		}
	}

	public void loadData(String file) throws IOException {
		File f = Resourse.findFile(file);
		if (f == null) {
			f = Resourse.findFile(MAP_CANDIDATES);
		}
		if (f == null) {
			throw new IOException("找不到地图文件：" + file);
		}
		System.out.println("加载地图：" + f.getAbsolutePath());

		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
		try {
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty() || line.startsWith("#")) {
					continue;          // 空行、注释行直接跳过
				}
				String[] strs = line.split("=");
				if (strs.length < 2) {
					continue;          // 格式不对的行不让它崩整个地图
				}
				String[] xys = strs[1].split(",");
				if (xys.length < 2) {
					continue;
				}
				int x, y;
				try {
					x = Integer.parseInt(xys[0].trim()) * VisibleItem.SIZE;
					y = Integer.parseInt(xys[1].trim()) * VisibleItem.SIZE;
				} catch (NumberFormatException e) {
					continue;          // 坐标不是数字，跳过这一行
				}
				Block block = BlockFactory.create(strs[0].trim(), x, y);
				if (block != null) {
					blocks.add(block);
				}
			}
		} finally {
			br.close();
		}
	}

	/** 找出覆盖 (px,py) 这个点的方块；没有就返回 null。后放的盖住先放的，所以倒着找 */
	public Block hitPoint(int px, int py) {
		for (int i = blocks.size() - 1; i >= 0; i--) {
			Block block = blocks.get(i);
			if (block.contains(px, py)) {
				return block;
			}
		}
		return null;
	}

	public void remove(Block block) {
		blocks.remove(block);
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public int size() {
		return blocks.size();
	}
}
