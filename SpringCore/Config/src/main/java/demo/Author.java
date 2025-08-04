package demo;

import java.util.Properties;

public class Author {
	private String name;
	private Properties info;
	
    public Author() {
        System.out.println("Author constructor called");
    }
    public Author(String name) {
    	this.name = name;
    	System.out.println("Author constructor has argument called");
    }
    public Author(String name, Properties info) {
		this.name = name;
		this.info = info;
	}
    
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public Properties getInfo() {
		return info;
	}
	public void setInfo(Properties info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		return "Author [name=" + name + ", info=" + info + "]";
	}
}
