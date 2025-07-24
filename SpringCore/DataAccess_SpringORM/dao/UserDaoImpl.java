@Repository
@Scope("singleton") // Scope demo: singleton (default), hoặc bạn dùng prototype để thử
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return sessionFactory.getCurrentSession()
            .createQuery("from User", User.class)
            .list();
    }
}
