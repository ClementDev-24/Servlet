import java.io.*;
import java.net.Socket;
import java.util.Scanner;



public class Receiver {
    public static void main(String[] args) {
        int port = 12340;
        try {
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket("localhost", port);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            System.out.println("Connected to server. Enter command (add / fetch):");
            String command = scanner.next().toLowerCase();
            oos.writeObject(command); // sends the string to server

            if ("add".equals(command)) {
            	System.out.println("Enter name:");
            	String name = scanner.next();
            	System.out.println("Enter ID:");
            	String id = scanner.next();
            	System.out.println("Enter Age:");
            	int age = scanner.nextInt();
            	System.out.println("Enter Department:");
            	String dept = scanner.next();
            	System.out.println("Enter Email ID:");
            	String email = scanner.next();
            	System.out.println("Enter Rank:");
            	int rank =scanner.nextInt();
            	Student dataPacket = new Student(name, id, rank, email, email, rank);
            	oos.writeObject(dataPacket);
            	Student responseStudent = (Student) ois.readObject();
            	System.out.println("Response from server:");
            	System.out.println(responseStudent);
            	
            } else if ("fetch".equals(command)) {
                System.out.println("Enter ID to fetch data:");
                String id = scanner.next();
                oos.writeObject(id);

                System.out.println("Do you want to apply filtering? (yes / no):");
                String applyFilter = scanner.next().toLowerCase();
                oos.writeObject(applyFilter);

                if ("yes".equals(applyFilter)) {
                    System.out.println("Filter by age (enter age or 0 to ignore):");
                    int filterAge = scanner.nextInt();
                    oos.writeObject(filterAge);

                    System.out.println("Filter by department (enter department or 'none' to ignore):");
                    String filterDepartment = scanner.next();
                    oos.writeObject(filterDepartment);

                    System.out.println("Filter by rank (enter rank or 0 to ignore):");
                    int filterRank = scanner.nextInt();
                    oos.writeObject(filterRank);
                }

                String response = (String) ois.readObject();
                System.out.println(response); // data or message from the server
                if (!response.equals("No data found for the given ID.")) {
                    int numRecords = (int) ois.readObject();
                    for (int i = 0; i < numRecords; i++) {
                        String studentId = (String) ois.readObject();
                        String name = (String) ois.readObject();
                        int age = (int) ois.readObject();
                        String dept = (String) ois.readObject();
                        String email = (String) ois.readObject();
                        int rank = (int) ois.readObject();

                        System.out.println("Data for ID " + studentId);
                        System.out.println("Name of the Student: " + name);
                        System.out.println("Age of the Student: " + age);
                        System.out.println("Department of the Student: " + dept);
                        System.out.println("Email ID of the Student: " + email);
                        System.out.println("Rank of the Student: " + rank);
                    }
                }
            } else {
                System.out.println("Invalid command.");
            }

            // Close the resources after use
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
