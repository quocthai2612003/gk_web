package DAO;

public interface IDao<T> {
    int insert(T model);
    int update(T model);
    int delete(T model);

}
