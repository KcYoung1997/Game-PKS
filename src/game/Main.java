package game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends Canvas{
	
	private static final long serialVersionUID = -1L;
	
	private static int width = 333;
	private static int height = width/16*9;
	private static int scale = 3;
	private Screen screen;
	private JFrame frame;
	private boolean running;
	
	public static void main(String[] args){
		Main game = new Main();
	}
	
	public Main()
	{
		Dimension size = new Dimension(width*scale,height*scale);
		setPreferredSize(size);
		screen = new Screen(width,height);
		frame = new JFrame();
		frame.setResizable(true);
		frame.setTitle("PKS Game Engine");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		long lt = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running){

			//display the correct pixels to the screen
			render();
			frames++;

		}
		stop();
	}
}
