package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Client implements Runnable {
	Socket client;
	int ID;
	
	public Client(Socket c, int i){
		client = c;
		ID = i;
    }

	@Override
	public void run() {
        System.out.println("Client Accepted: " + ID);
        PrintStream os;
        DataInputStream is;
	    try {
			is = new DataInputStream(client.getInputStream());
		    os = new PrintStream(client.getOutputStream());
		} catch (IOException e) {
			System.err.println("Failed to get inputstream for client number " + ID);
			return;
		}
	    System.out.println("Client Streams Opened: " + ID);
	}
}
