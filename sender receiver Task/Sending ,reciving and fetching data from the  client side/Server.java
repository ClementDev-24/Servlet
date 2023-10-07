import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Student implements Serializable {
    private int studentId;
    private String name;
    private String department;
    private int age;
    private String email;

    public Student(int studentId, String name, String department, int age, String email) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.age = age;
        this.email = email;
    }

    // Getters and setters here (if needed)

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}

/*
class Student implements Serializable {
    private int studentId;
    private String name;
    private String department;
    private int age;
    private String email;

    public Student(int studentId, String name, String department, int age, String email) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.age = age;
        this.email = email;
    }

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
public String getName() {
		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}*/

class StudentServer {
    private ArrayList<Student> students;
    String fileloc= "C:\\Users\\P3INDW23\\Desktop\\Studentdata.txt";
    public StudentServer() {
        students = new ArrayList<>();
        loadStudents();
    }

    private void loadStudents() {
    	
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileloc))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // In case of the first run or file not found, ignore the exception and initialize an empty list
            students = new ArrayList<>();
        }
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileloc))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    public ArrayList<Student> getStudentsById(int studentId) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList<Student> getStudentsByDepartment(String department) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getDepartment().equalsIgnoreCase(department)) {
                result.add(student);
            }
        }
        return result;
    }
}

public class Server {
    public static void main(String[] args) {
        StudentServer server = new StudentServer();
        final int PORT = 12347;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT + "...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    Object requestObject = ois.readObject();
                    if (requestObject instanceof Request) {
                        Request request = (Request) requestObject;
                        Response response = new Response();

                        if ("add".equals(request.getAction())) {
                            Student student = request.getStudent();
                            server.addStudent(student);
                            response.setMessage("Student added successfully.");
                        } else if ("get_by_id".equals(request.getAction())) {
                            int studentId = request.getStudentId();
                            ArrayList<Student> students = server.getStudentsById(studentId);
                            response.setStudents(students);
                        } else if ("get_by_department".equals(request.getAction())) {
                            String department = request.getDepartment();
                            ArrayList<Student> students = server.getStudentsByDepartment(department);
                            response.setStudents(students);
                        }

                        oos.writeObject(response);
                    }
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
