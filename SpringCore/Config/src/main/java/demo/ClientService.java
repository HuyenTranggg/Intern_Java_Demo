package demo;

public class ClientService {
	private static ClientService clientService = new ClientService();
	private ClientService() {}
	
	public static ClientService createInstance() {
		System.out.println("Create bean via static factory method");
		return clientService;
	}
}