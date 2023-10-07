package D360;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/ClientController")
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientDbUtil clientDbUtil;

	@Resource(name = "jdbc/D3Sixty")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			clientDbUtil = new ClientDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");

			if (theCommand == null) {
				theCommand = "LOGIN";
			}

			switch (theCommand) {
			case "LOGIN":
				loginClient(request, response);
				break;
			case "ADD":
				addClient(request, response);
				break;
			case "UPDATE":
				updateClient(request, response);
				break;
			case "LOAD":
				loadClient(request, response);
				break;
			case "DELETE":
				deleteClient(request, response);
				break;
			case "SHOW":
				showcount(request, response);
				break;
			default:
				loginClient(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student id from the data
		int theClientId = Integer.parseInt(request.getParameter("ClientId"));
		// delete the student from the database
		clientDbUtil.deleteClient(theClientId);
		// send them to list students
		listClient(request, response);
	}

	private void updateClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("clientId"));
		String userName = request.getParameter("userName");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String role = request.getParameter("role");

		Client theClient = new Client(id, userName, firstName, middleName, lastName, email, role);

		clientDbUtil.updateClient(theClient);

		listClient(request, response);

	}

	private void loadClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int theClientId = Integer.parseInt(request.getParameter("clientId"));

		Client theClient = clientDbUtil.getClient(theClientId);

		// place student in the request attribute
		request.setAttribute("Client_list", theClient);

		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Update.jsp");
		dispatcher.forward(request, response);
	}

	private void addClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String userName = request.getParameter("userName");

		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String password = request.getParameter("password");

		// create a new student object
		Client theClient = new Client(userName, firstName, middleName, lastName, email, role, password);

		// add the student to the database
		clientDbUtil.addClient(theClient);

		// send back to main page (the student list)
		listClient(request, response);
	}

	private void listClient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get students from db util
		List<Client> client = clientDbUtil.getClients();

		// add students to the request
		request.setAttribute("ClientList", client);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin-Index.jsp");
		dispatcher.forward(request, response);
	}

	private void loginClient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		

		List<Client> clients = clientDbUtil.getClients();

		Client authenticatedClient = null;
		for (Client client : clients) {
			
			System.out.println(client);
			if (client.getUserName().equals(username)) {
				authenticatedClient = client;
				break;
			}
		}

		if (authenticatedClient != null && authenticatedClient.getPassword().equals(password)) {
			if(authenticatedClient.getRole().equals("ADMIN")) {
			request.setAttribute("authenticatedClient", authenticatedClient);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin-Index.jsp");
			listClient(request, response);
			dispatcher.forward(request, response);
			}
			else {
				request.setAttribute("authenticatedClient", authenticatedClient);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin-Index.jsp");
//				showcount(request, response);
				dispatcher.forward(request, response);
				}
			}
			
		else {
			request.setAttribute("errorMessage", "Incorrect username or password");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void showcount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String theClientIdFrom = request.getParameter("ClientIdFrom");
		System.out.println(theClientIdFrom);
		String  theClientIdTo = request.getParameter("ClientIdTo");
		
		List<Client> theClient = clientDbUtil.count(theClientIdFrom,theClientIdTo);
		

		// place student in the request attribute
		request.setAttribute("ClientList", theClient);
		System.out.println(theClient);
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Client.jsp");
		dispatcher.forward(request, response);		
	}
}