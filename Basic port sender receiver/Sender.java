import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Sender {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
    	String check = "send";
    	System.out.println("Enter the size of array:");
    	int size = sc.nextInt();
            
            
            String receiverIP = "localhost";	 
           int port =8080; //  port number on the receiver machine

            		String[] dataArray = new String[size];
            		int length=dataArray.length;
            for (int i=0; i<=length-1;i++) {
            	System.out.println("Enter the "+i+" Element:");
                dataArray[i]=sc.next();
       		 
            }
            
            System.out.println("To send data type send");
            System.out.println("To cancel type cancel");
            String checkf = sc.next().toLowerCase();
            System.out.println("You have Entered: "+checkf);
            
            
            if(check.equals(checkf)) {
            	Socket socket = new Socket(receiverIP, port);
            	
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                 objectOutputStream.writeObject(dataArray);
                 System.out.println("The data has been sent successfully");

                 try {
					objectOutputStream.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
                 
                 try {
					socket.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
            	 
             }
             else {
            	System.out.println("The Process is disconnected");
             }
          
        
    }
}
