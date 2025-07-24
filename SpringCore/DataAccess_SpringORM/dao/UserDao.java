package SpringCore.DataAccess_SpringORM.dao;

public interface UserDao {
    void save(User user);
    List<User> getAll();
}
