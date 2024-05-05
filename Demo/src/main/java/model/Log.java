package model;

import java.time.LocalDateTime;

public class Log {
    private int id;
    private int level;
    private String table;
    private String preValue;
    private String value;
    private LocalDateTime dateTime;

    public Log() {
    }

    public Log(int level, String table, String preValue, String value, LocalDateTime dateTime) {
        this.level = level;
        this.table = table;
        this.preValue = preValue;
        this.value = value;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPreValue() {
        return preValue;
    }

    public void setPreValue(String preValue) {
        this.preValue = preValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
