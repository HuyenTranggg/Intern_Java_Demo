package demo.annotation.beanscopes.consumer;

import demo.annotation.beanscopes.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScopeDemoConsumer {
	
	public ScopeDemoConsumer() {
		System.out.println(">>> scopeDemoConsumer bean has bean created");
	}

    @Autowired
    private SingletonBean singletonBean;

    @Autowired
    private PrototypeBean prototypeBean;

    public void printBeanTimes() {
        System.out.println("----------------------------------------");
        System.out.println("Singleton Bean Time:   " + singletonBean.getCreationTime());
        System.out.println("Prototype Bean Time:   " + prototypeBean.getCreationTime());
        System.out.println("----------------------------------------");
    }
}