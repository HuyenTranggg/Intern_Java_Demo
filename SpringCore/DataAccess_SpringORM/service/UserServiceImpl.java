package SpringCore.DataAccess_SpringORM.service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;

    @Override
    public void registerUser(String name) {
        userDao.save(new User(name));
    }

    @Override
    public void listUsers() {
        userDao.getAll().forEach(u -> System.out.println(u.getName()));
    }
}