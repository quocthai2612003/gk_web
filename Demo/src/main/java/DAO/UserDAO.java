package DAO;


import model.User;
import org.jdbi.v3.core.Jdbi;
import vn.fit.nlu.gk.db.JDBIConnector;

import java.util.List;

public class UserDAO extends AbsDao<User> {
    private static Jdbi jdbi = JDBIConnector.getInstance();
    private static UserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) instance = new UserDAO();
        return instance;
    }

    public static List<User> listUser() {
        List<User> userList = jdbi.withHandle(handle -> {
            return handle.createQuery("Select id, name, email From user")
                    .mapToBean(User.class).list();
        });
        return userList;
    }

    @Override
    public int insert(User model) {
        int execute = jdbi.withHandle(handle -> {
            return handle.createUpdate("Insert Into user(name, email, password) " +
                    "values(?,?,?)")
                    .bind(0, model.getName())
                    .bind(1, model.getEmail())
                    .bind(2, model.encryptMd5(model.getPassword())).execute();
        });

        if (execute > 0) {
            super.insert(model);
        }
        return execute;
    }

    @Override
    public int delete(User model) {
            int execute = jdbi.withHandle(handle -> {
                return handle.createUpdate("delete from user where id = ?")
                        .bind(0, model.getId()).execute();
            });
            if (execute>0) {
                super.delete(model);
            }
            return execute;
    }

    @Override
    public int update(User model) {
        int execute = jdbi.withHandle(handle -> {
            return handle.createUpdate("Update user set name = ? where id = ?")
                    .bind(0, model.getName())
                    .bind(1, model.getId()).execute();
        });
        if (execute > 0) {
            super.update(model);
        }

        return execute;
    }

    public User selectUserById(String id) {
        User user = jdbi.withHandle(handle -> {
            return handle.createQuery("Select id, name, email, password from user where id = ?")
                    .bind(0, id).mapToBean(User.class).first();
        });
        return user;
    }
}
