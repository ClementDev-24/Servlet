import java.io.*;
import java.net.*;

public class Receiver {
	
	    public static void main(String[] args) {
	        try {
	           
	            int port = 8080; 
	            ServerSocket serverSocket = new ServerSocket(port);
	            System.out.println("Waiting for the sender to connect...");

	            Socket socket = serverSocket.accept();
	            System.out.println("Connection established with the sender.");

	            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
	            String[] receivedArray = (String[]) objectInputStream.readObject();

	            
	            for (String value : receivedArray) {
	                System.out.println(value + " ");
	            }
	            System.out.println("The data received from the sender"); 

	            
	            objectInputStream.close();
	            socket.close();
	            serverSocket.close();
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	}