import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
class Request implements Serializable {
   
	private String action;
    private int studentId;
    private Student student;
    private String department;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

    // Constructors, getters, setters
}

@SuppressWarnings("serial")
class Response implements Serializable {
    private String message;
    private ArrayList<Student> students;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<Student> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

}

public class Client {
    public static void main(String[] args) {
        final String SERVER_HOST = "localhost";
        final int SERVER_PORT = 12348;

        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n1. Add Student\n2. Get Students by ID\n3. Get Students by Department\n4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (choice == 1) {
                    System.out.print("Enter Student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    Student student = new Student(studentId, name, department, age, email);
                    Request request = new Request();
                    request.setAction("add");
                    request.setStudent(student);

                    oos.writeObject(request);

                    Response response = (Response) ois.readObject();
                    System.out.println(response.getMessage());
                } else if (choice == 2) {
                    System.out.print("Enter Student ID: ");
                    int studentId = scanner.nextInt();

                    Request request = new Request();
                    request.setAction("get_by_id");
                    request.setStudentId(studentId);

                    oos.writeObject(request);

                    Response response = (Response) ois.readObject();
                    ArrayList<Student> students = response.getStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found with ID " + studentId);
                    } else {
                        System.out.println("Students with ID " + studentId + ":");
                        for (Student student : students) {
                            System.out.println(student.getName() + " (" + student.getEmail() + ")");
                        }
                    }
                } else if (choice == 3) {
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();

                    Request request = new Request();
                    request.setAction("get_by_department");
                    request.setDepartment(department);

                    oos.writeObject(request);

                    Response response = (Response) ois.readObject();
                    ArrayList<Student> students = response.getStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found in department " + department);
                    } else {
                        System.out.println("Students in department " + department + ":");
                        for (Student student : students) {
                            System.out.println(student.getName() + " (" + student.getEmail() + ")");
                        }
                    }
                } else if (choice == 4) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
