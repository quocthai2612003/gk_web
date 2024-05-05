package DAO;

import model.IModel;

public abstract class  AbsDao<T extends IModel> implements IDao<T> {
    @Override
    public int insert(T model) {
        LogDao.insert("2", model.getTable(), "0", model.afterData(model));
        return 0;
    }

    @Override
    public int delete(T model) {
        LogDao.insert("4", model.getTable(), model.beforeData(), "0");
        return 0;
    }

    @Override
    public int update(T model) {
        LogDao.insert("4", model.getTable(), model.beforeData(), model.afterData(model));
        return 0;
    }
}
