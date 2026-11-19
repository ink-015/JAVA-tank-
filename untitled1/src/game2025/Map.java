package game2025;

import java.awt.Graphics;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
	private ArrayList<Block> blocks;
	
	public Map() {
		blocks = new ArrayList<Block>();
	}
	
	public void draw(Graphics g) {
		for(Block block:blocks) {
			block.draw(g);
		}
	}
	public void loadData(String file) throws IOException {
		FileReader fr = new FileReader(file); 
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		while((line=br.readLine())!=null) {
			String[] strs;
			strs = line.split("=");
			String[] xys;
			xys = strs[1].split(",");			
			int x,y;
			x = Integer.parseInt(xys[0]) *34 ;
			y = Integer.parseInt(xys[1]) *34 ;
			if(strs[0].compareTo("brick") == 0) {
				blocks.add(new BlockBrick(x,y));
			}else if(strs[0].compareTo("grass") == 0) {
				blocks.add(new BlockGrass(x,y));
			}else if(strs[0].compareTo("stone") == 0) {
				blocks.add(new BlockStone(x,y));
			}else if(strs[0].compareTo("water") == 0) {
				blocks.add(new BlockWater(x,y));
			}			
		}
		br.close();
	}

}
