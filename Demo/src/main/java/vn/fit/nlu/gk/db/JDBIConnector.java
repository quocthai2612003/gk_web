package vn.fit.nlu.gk.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


public class JDBIConnector {
    private static Jdbi jdbi;

    private static void makeConnect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + DBProperties.getDbHost() + ":" + DBProperties.getDbPort() + "/"
                + DBProperties.getDbName());
        dataSource.setUser(DBProperties.getUsername());
        dataSource.setPassword(DBProperties.getPassword());
        try {
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
        jdbi = Jdbi.create(dataSource);
    }


    private JDBIConnector() {
    }

    public static Jdbi getInstance() {
        if (jdbi == null) makeConnect();
        return jdbi;
    }

    public static void main(String[] args) {
        Jdbi connect = getInstance();
        List<String> result = connect.withHandle(handle ->
                handle.createQuery("SELECT * FROM account")
                        .mapTo(String.class)
                        .list());
        System.out.println(result.stream().collect(Collectors.joining(", ")));
    }
}



