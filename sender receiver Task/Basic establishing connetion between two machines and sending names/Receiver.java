import java.io.*;
import java.net.*;

public class Receiver {
	public static void startServer(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);// in this part we connect to the server using the port number
          // ServerSocket will wait for the request in the network
			System.out.println("Waiting for the sender to connect...");

			Socket socket = serverSocket.accept();//accept is used to accept the incoming connection request
			System.out.println("Connection established with the sender.");

			processReceivedData(socket);

			socket.close();
			serverSocket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	 private static void processReceivedData(Socket socket) throws IOException, ClassNotFoundException {
	        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
	        // ObjectInputStream deserializes primitive data and objects previously written using an ObjectOutputStream
		       // socket.getInputStream() /  input stream of data coming from the remote socket
	        Object ois = objectInputStream.readObject();// this is used to read the objects present in the input stream and 
	        //use it in the instance of to find the type of data present in the received object
	       
	        if (ois instanceof String[]) {// this is accessed only there is an String array inside
	        	 String[] receivedArray = (String[]) ois;
	        	 for (String value : receivedArray) {//used to print the array received
	 	            System.out.println(value + " ");
	 	        }
	 	        System.out.println();
	 	        System.out.println("The data received from the sender");
	 	        System.out.println();
	        }
	        else if(ois instanceof String) {
            String rs = (String) ois;
            System.out.println(rs);
	        }

	        objectInputStream.close(); // closes the stream even there is an exception
	    }

	public static void main(String[] args) {
		 int port = 8080;
	        startServer(port);// calls the method and helps to process
	}
}