package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class Main extends Canvas{
	//Throws a warning if not implemented, java customs.
	private static final long serialVersionUID = -1L;
	//Screen size
	private static int width = 1024;
	private static int height = width/16*9;
	//Frame container, creates window.
	private JFrame frame;
	//BufferedImage is the finalised frame to be displayed
	private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);	
	//Array of pixels contained within above image.
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	//Game itself
	private Game game;
	
	public static void main(String[] args){
		Main game = new Main();
	}
	
	public Main()
	{
		setPreferredSize(new Dimension(width,height));
		frame = new JFrame();

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setResizable(true);
		frame.setTitle("PKS Game Engine");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game = new Game();
		Thread gThread = new Thread(game);
		gThread.start();
	    
		requestFocus();
		while (true){
			render();
		}
	}

	private void render() {
		//Allocate buffer space to hold image
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		//Reset pixels to black.
		for (int i = 0; i < pixels.length; i++) {
			int one = (int) (System.currentTimeMillis()/ 7 % 512);
			int two = (int) (System.currentTimeMillis()/ 41 % 512);
			int three = (int) (System.currentTimeMillis()/ 19 % 512);
			pixels[i] = (one > 256 ? 256 - one : one)*0x010000 +
					 (two > 256 ? 256 - two : two)*0x000100 +
					 (three > 256 ? 256 - three : three)*0x0000001;
					
		}
		
		pixels = game.Render(width, height, pixels);
		
		
		//display the image in the window
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);		
		bs.show();
		//Free up memory.
		g.dispose();
	}
}
