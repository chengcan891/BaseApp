package ink.chengcan.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


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
        this.devOpenHelper = new MyDevOpenHelper(context, DB_NAME, null);
//        this.devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);

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

    /**
     * 初始化变量，升级
     *
     * @param context
     */
    public static void init(Context context) {
        getInstance(context).getDaoSession();
    }

    public DaoMaster getDaoMaster() {
        if (null == daoMaster) {
            synchronized (DbManager.this) {
                if (null == daoMaster) {
                    MyDevOpenHelper helper = new MyDevOpenHelper(context, DB_NAME, null);
                    if (ENCRYPTED) {
                        daoMaster = new DaoMaster(helper.getEncryptedReadableDb("chengcan"));
                    } else {
                        daoMaster = new DaoMaster(helper.getWritableDatabase());
                    }
//                    daoMaster = new DaoMaster(getWritableDatabase());
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
        return devOpenHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase() {
        return devOpenHelper.getReadableDatabase();
    }


}
