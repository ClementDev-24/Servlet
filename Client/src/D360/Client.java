package D360;

public class Client {
    private int id;
    private String userName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String role;
    private String password;

    public Client(int id, String userName, String firstName, String middleName, String lastName, String email,
                  String role, String password) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
    }
    public Client(String userName, String password,String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public Client(String userName, String firstName, String middleName, String lastName, String email, String role,String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password=password;
    }
    


	public Client(int id,String userName, String firstName, String middleName, String lastName, String email, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}





	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        
    }

    public  String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
   
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                 + '\'' +
                '}';
    }
}
