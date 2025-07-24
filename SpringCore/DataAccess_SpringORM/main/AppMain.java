package SpringCore.DataAccess_SpringORM.main;

public class AppMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        
        UserService service = ctx.getBean(UserService.class);
        
        service.registerUser("Yen");
        service.registerUser("Linh");

        service.listUsers(); // In ra danh sách

        ctx.close();
    }
}