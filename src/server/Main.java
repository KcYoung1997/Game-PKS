package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
    	//Vars for client thread
    	byte currentID = 0;
    	List<Runnable> ClientThreads = new ArrayList<Runnable>();
    	//Start server listening on socket (port)
	    ServerSocket echoServer = null;
        try {
           echoServer = new ServerSocket(9999);
        }
        catch (IOException e) {
           System.out.println(e);
        }   
        System.out.println("Server opened");
        //Wait for new client, then start it's interface thread
        while (true) {
		    try {
	
		        	ClientThreads.add(new Client(echoServer.accept(), currentID));
		        	new Thread(ClientThreads.get(currentID)).start();
		            currentID++;
		    }   
		    catch (IOException e) {
		           System.out.println(e);
		    }
    	}
	}
}
