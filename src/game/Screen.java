package game;

import java.util.Random;
	
public class Screen {
	
	// setup location handling variables
	public static int width;
	public static int height;
	public int xOffset;
	public int yOffset;
	public static int i=0;
	public int o=0;
	public int[] pixels;
	public final int MAP_SIZE = 128, MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private Random rnd = new Random();
	public boolean Debug = false;
	
	public Screen(int width, int height) {
		Screen.width = width;
		Screen.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = rnd.nextInt(0xffffff);
		}
	}
	
	// clear the screen to black
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
		}
	}
	
	
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}