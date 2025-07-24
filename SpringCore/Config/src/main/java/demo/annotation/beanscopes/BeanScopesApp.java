package demo.annotation.beanscopes;

import demo.annotation.beanscopes.beans.PrototypeBean;
import demo.annotation.beanscopes.beans.SingletonBean;
import demo.annotation.beanscopes.config.BeanScopesConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanScopesApp {

    public static void main(String[] args) {
    	// Do bean kiểu PrototypeBean có inject vào class ScopeDemoConsumer (Scope singleton)
    	// Nên bean prototypeBean mới được tạo dù chưa getBean
    	System.out.println("======> CONTAINER INITIALIZED. Singleton beans created. <======");
    	ApplicationContext context = new AnnotationConfigApplicationContext(BeanScopesConfig.class);
    	
    	System.out.println();
    	System.out.println("======> Singleton Scope won't create new bean. <======");
    	SingletonBean st = context.getBean(SingletonBean.class);
    	
    	System.out.println();
    	System.out.println("======> But Prototype Scope will create new bean. <======");
    	PrototypeBean pt = context.getBean(PrototypeBean.class);
    }
    
}