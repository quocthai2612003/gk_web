package model;

public interface IModel<T> {
    String getTable();
    String beforeData();
    String afterData(T model);
}
