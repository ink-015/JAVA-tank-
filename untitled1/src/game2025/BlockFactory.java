package game2025;

/**
 * 方块工厂：把地图文件里的一行类型字符串变成一块砖。
 *
 * 支持“基础类型 + 装饰器”的叠加写法，例如：
 *     brick           普通砖
 *     brick+strong    加固砖
 *     brick+bomb      炸药砖
 *     brick+gift      惊喜砖
 *     brick+strong+bomb+gift   全都来一层
 *
 * 这正是装饰模式的好处：新增一种“特殊砖”不用改 Map，也不用新增子类。
 */
public class BlockFactory {

	private BlockFactory() {
	}

	public static Block create(String type, int x, int y) {
		if (type == null) {
			return null;
		}
		String[] parts = type.toLowerCase().trim().split("\\+");
		Block block = createBase(parts[0].trim(), x, y);
		if (block == null) {
			return null;
		}
		for (int i = 1; i < parts.length; i++) {
			block = decorate(block, parts[i].trim());
		}
		return block;
	}

	/** 基础地形 */
	private static Block createBase(String type, int x, int y) {
		if ("brick".equals(type)) {
			return new BlockBrick(x, y);
		} else if ("stone".equals(type)) {
			return new BlockStone(x, y);
		} else if ("water".equals(type)) {
			return new BlockWater(x, y);
		} else if ("grass".equals(type)) {
			return new BlockGrass(x, y);
		}
		System.err.println("地图里有不认识的方块类型：" + type + "，已跳过");
		return null;
	}

	/** 挂装饰器 */
	private static Block decorate(Block block, String type) {
		if ("strong".equals(type)) {
			return new DcrStrongBlock(block);
		} else if ("bomb".equals(type)) {
			return new DcrBombBlock(block);
		} else if ("gift".equals(type)) {
			return new DcrGiftBlock(block);
		}
		System.err.println("地图里有不认识的装饰类型：" + type + "，已跳过");
		return block;
	}
}
