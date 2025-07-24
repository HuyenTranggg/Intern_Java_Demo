import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springtransaction.SpringTransaction.config.AppConfig;
import com.springtransaction.SpringTransaction.dao.ProductDao;
import com.springtransaction.SpringTransaction.entity.Product;
import com.springtransaction.SpringTransaction.service.DeclarativeProductService;
import com.springtransaction.SpringTransaction.service.ProgrammaticProductService;

public class Application{
	public static void main(String[] args) {
		// Khoi tao Spring Container, nap cac bean tu appconfig
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// lay bean
		DeclarativeProductService declarativeService = context.getBean(DeclarativeProductService.class);
		ProgrammaticProductService programmaticService = context.getBean(ProgrammaticProductService.class);
		ProductDao productDao = context.getBean(ProductDao.class);
		
		// khoi tao
		System.out.println(productDao.count());
		
		// Declarative - Fail case
		try {
			declarativeService.createProductAndFail(new Product("Fail"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(productDao.count());
		
		//Declarative - success case
		declarativeService.createProductSuccess(new Product("Laptop"));
		System.out.println(productDao.count());
		
		// Programmatic - fail case
		try {
			programmaticService.createProductAndFail(new Product("fail"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(productDao.count());
		
		// Programmatic -success case
		programmaticService.createProductSusscess(new Product("Laptop"));
		System.out.println(productDao.count());
		
		// Giai phong du lieu va dong Spring container
		context.close();
	}
}