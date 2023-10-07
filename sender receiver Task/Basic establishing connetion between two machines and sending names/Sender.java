import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Sender {
	public static String[] createValue() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of array:");
		int size = sc.nextInt();// Size of array
		String[] dataArray = new String[size];// Array creation
		int length = dataArray.length;// calculating the size of array to get input using for loop
		for (int i = 0; i <= length - 1; i++) {// used this for loop to initialize the array
			int a=i+1;// Adding here when we do this in sysout it concats example:01,02
			System.out.println("Enter the " + a + " Element:");
			dataArray[i] = sc.next();
		}
		return dataArray;// returning this reference variable from this method 
		//get the String[] in main method using createValue()

	}
	// Used to connect to the server using socket
	 public static void sendDataToServer(String receiverIP, int port, String[] data) {
		 //
	        try {
	            Socket socket = new Socket(receiverIP, port);

	            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	            oos.writeObject(data);
	            System.out.println("The data has been sent successfully");

	            oos.close();
	            socket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 public static void sendDataCanclled(String receiverIP, int port, String can) {
		 //
	        try {
	            Socket socket = new Socket(receiverIP, port);

	            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	            oos.writeObject(can);
	            System.out.println("The data has been sent successfully");

	            oos.close();
	            socket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public static void main(String[] args) throws Exception {
		  Scanner sc = new Scanner(System.in);
	        String check = "send";// This String is used in if clause 
	        String receiverIP = "localhost"; // Localhost is used to represent that it runs in same devices
	        int port = 8080;// This is the port number data will be sent both sender and receiver must have same port number
	        String[] data = createValue(); // getting the array and storing it in data variable
	        System.out.println("To send data type send");
	        System.out.println("To cancel type cancel");
	        String checkf = sc.next().toLowerCase();// Converting and storing input in lower case
	        System.out.println("You have Entered: " + checkf); // used to show the entered String value

	        if (check.equals(checkf)) {
	            sendDataToServer(receiverIP, port, data);// calling the method and sending the values to the method
	        } else {
	        	String cc="The transfer has been cancelled from sender side";
	            sendDataCanclled(receiverIP, port, cc);
	        }
	}
}
