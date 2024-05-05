package DAO;


import model.Log;
import model.User;
import org.jdbi.v3.core.Jdbi;
import vn.fit.nlu.gk.db.JDBIConnector;

import java.time.LocalDateTime;
import java.util.List;

public class LogDao {
    public static Jdbi jdbi = JDBIConnector.getInstance();
    public static void insert(String level, String tableName, String preValue, String value) {
        LocalDateTime date = LocalDateTime.now();
        jdbi.withHandle(handle -> {
            return handle.createUpdate("INSERT INTO log(level, `table`, preValue, value, dateTime) " +
                            "VALUES (?, ?, ?, ?, ?)")
                    .bind(0, level)
                    .bind(1, tableName)
                    .bind(2, preValue)
                    .bind(3, value)
                    .bind(4, date)
                    .execute();
        });
    }
    public static List<Log> getAllLogs() {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM log")
                    .mapToBean(Log.class)
                    .list();
        });
    }
}
