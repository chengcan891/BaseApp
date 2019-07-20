package ink.chengcan.dao.greendao.student;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import ink.chengcan.dao.greendao.DbManager;
import ink.chengcan.dao.greendao.StudentDao;

/**
 * https://www.jianshu.com/p/53083f782ea2
 *
 * https://blog.csdn.net/qq_36699930/article/details/81540781
 *
 * https://www.jianshu.com/p/669279cda677
 */

public class StudentDaoManager {

    public static void init(Context context) {
        DbManager.getInstance(context).getDaoSession();
    }

    /**
     * @param context
     * @param student
     * @return
     */
    public static long insert(Context context, Student student) {
        return DbManager.getInstance(context).getDaoSession().getStudentDao().insert(student);
    }

    /**
     * @param context
     * @param students
     */
    public static void insert(Context context, List<Student> students) {
        DbManager.getInstance(context).getDaoSession().getStudentDao().insertInTx(students);
    }

    /**
     * @param context
     * @param student
     */
    public static void save(Context context, Student student) {
        DbManager.getInstance(context).getDaoSession().getStudentDao().save(student);
    }

    public static void save(Context context, List<Student> students) {
        DbManager.getInstance(context).getDaoSession().getStudentDao().saveInTx(students);
    }

    public static void delete(Context context, Student student) {
        DbManager.getInstance(context).getDaoSession().getStudentDao().delete(student);
    }

    public static void delete(Context context, List<Student> students) {
        DbManager.getInstance(context).getDaoSession().getStudentDao().deleteInTx(students);
    }

    public static void update(Context context, Student student) {
        DbManager.getInstance(context).getDaoSession().getStudentDao().update(student);
    }

    public static List<Student> query(Context context, int pageSize, int pageNum) {
        StudentDao studentDao = DbManager.getInstance(context).getDaoSession().getStudentDao();
        QueryBuilder<Student> studentQueryBuilder = studentDao.queryBuilder();
        return studentQueryBuilder.offset(pageSize * pageNum).limit(pageSize).list();
    }
}
