package demo;

import java.util.List;
import java.util.Map;

public class ServiceConsumer {
	private int id;
    private AccountService accountService;
    private AccountService constructorInjectedService;
    private List<AccountService> serviceList;
    private Map<String, AccountService> serviceMap;

    public ServiceConsumer() {
    }
    public ServiceConsumer(AccountService constructorInjectedService) {
        System.out.println("Constructor Injected: " + constructorInjectedService.getClass().getSimpleName());
        this.constructorInjectedService = constructorInjectedService;
    }

    public AccountService getAccountService() {
		return accountService;
	}
    public void setAccountService(AccountService accountService) {
        System.out.println("Setter Injected: " + accountService.getClass().getSimpleName());
        this.accountService = accountService;
    }
    public AccountService getConstructorInjectedService() {
		return constructorInjectedService;
	}
	public void setConstructorInjectedService(AccountService constructorInjectedService) {
		this.constructorInjectedService = constructorInjectedService;
	}
    public List<AccountService> getServiceList() {
		return serviceList;
	}
    public void setServiceList(List<AccountService> serviceList) {
        this.serviceList = serviceList;
        System.out.println("List Injected: " + serviceList.size() + " services");
    }
    public Map<String, AccountService> getServiceMap() {
		return serviceMap;
	}
    public void setServiceMap(Map<String, AccountService> serviceMap) {
        this.serviceMap = serviceMap;
        System.out.println("Map Injected: " + serviceMap.keySet());
    }
}