package ink.chengcan.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ink.chengcan.dao.greenDao.db.DaoMaster;
import ink.chengcan.dao.greenDao.db.DaoSession;

public class DbManager {

    //怎么加密
    public static final boolean ENCRYPTED = true;

    private static final String DB_NAME = "student.db";

    private Context context;
    private static DbManager instance;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private DbManager(Context context) {
        this.context = context;
        this.devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);

    }

    public static DbManager getInstance(Context context) {
        if (null == instance) {
            synchronized (DbManager.class) {
                if (null == instance) {
                    instance = new DbManager(context);
                }
            }
        }
        return instance;
    }

    public DaoMaster getDaoMaster() {
        if (null == daoMaster) {
            synchronized (DbManager.this) {
                if (null == daoMaster) {
                    daoMaster = new DaoMaster(getWritableDatabase());
                }
            }
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (null == daoSession) {
            synchronized (DbManager.class) {
                if (null == daoSession) {
                    daoSession = getDaoMaster().newSession();
                }
            }
        }
        return daoSession;
    }

    public SQLiteDatabase getWritableDatabase() {
        if (null == devOpenHelper) {
            getInstance(context);
        }
        return devOpenHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase() {
        if (null == devOpenHelper) {
            getInstance(context);
        }
        return devOpenHelper.getReadableDatabase();
    }


}
