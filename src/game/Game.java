package game;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Game implements Runnable {
	//Debug vars
	private final int connectRetries = 2;
	//Server IO Streams
    DataInputStream serverInput;
    PrintStream serverOutput;
    Socket serverClient;
    
	@Override
	public void run() {
		//Attempt to connect to server, taking a second break between tries.
		boolean connected = false;
		for(int i = 0; i < connectRetries && !connected; i++){
			if(i>0) { try { Thread.sleep(1000); } catch (InterruptedException e) {} }
			connected = connect();
		}
		
		
		

		
	    try {
	    	serverOutput.close();
	    	serverInput.close();
	    	serverClient.close();
	    } 
	    catch (IOException e) {
	       System.err.println("Cannot close client connection.");
	    }
	}

	private boolean connect() {
		try {
			serverClient = new Socket("localhost", 9999);
			serverInput = new DataInputStream(serverClient.getInputStream());
			serverOutput = new PrintStream(serverClient.getOutputStream());
		} catch (UnknownHostException e) {
			System.err.println("Server connection failed, unknown host.");
			return false;
		} catch (IOException e) {
			System.err.println("Server connection failed, IOerror.");
			return false;
		}
		return true;
	}

	public int[] Render(int width, int height, int[] pixels) {
		
		return pixels;
	}
	
}
