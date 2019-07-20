package ink.chengcan.baseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ink.chengcan.base.BaseActivity;
import ink.chengcan.bus.RxBus;
import ink.chengcan.bus.test.TestEvent;
import ink.chengcan.testbus.TestBusActivity;
import ink.chengcan.testintercept.TestInterceptActivity;
import ink.chengcan.testpermission.TestPermissionActivity;
import io.reactivex.functions.Consumer;

//import ink.chengcan.dao.greendao.student.Student;
//import ink.chengcan.dao.greendao.student.StudentDaoManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_test_bus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestBusActivity.class));
            }
        });
        findViewById(R.id.btn_test_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestPermissionActivity.class));
            }
        });

        findViewById(R.id.btn_test_intercept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestInterceptActivity.class));
            }
        });


        observe();

//        DbManager.init(this);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("time:" + System.currentTimeMillis());
//                List<Student> students = new ArrayList<>(100000);
//                for (int i = 0; i < 100000; i++) {
//                    Student student = new Student();
//                    student.setStudentNo(i);
//                    students.add(student);
//                }
//
//                StudentDaoManager studentDaoManager = new StudentDaoManager();
//                studentDaoManager.init(MainActivity.this);
//                System.out.println("time: insert start: " + System.currentTimeMillis());
//                studentDaoManager.insert(students);
//                System.out.println("time:  insert end : " + System.currentTimeMillis());
//                studentDaoManager.query();
//                System.out.println("time:  query end : " + System.currentTimeMillis());
////                studentDaoManager.delete();
////                System.out.println("time:  delete end : " + System.currentTimeMillis());
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("time:" + System.currentTimeMillis());
//                List<Student> students = new ArrayList<>(10000);
//                for (int i = 0; i < 10000; i++) {
//                    Student student = new Student();
//                    student.setStudentNo(i);
//                    students.add(student);
//                }
//                System.out.println("time: insert start: " + System.currentTimeMillis());
//                StudentDaoManager.save(MainActivity.this, students);
//                System.out.println("time:  insert end : " + System.currentTimeMillis());
//                students = StudentDaoManager.query(MainActivity.this, 10000, 0);
//                System.out.println("time:  query end : " + System.currentTimeMillis()  +" num"+ students.size());
//                StudentDaoManager.delete(MainActivity.this,students);
//                System.out.println("time:  delete end : " + System.currentTimeMillis());
//            }
//        }).start();
//        initGreenDao();
    }


    private void observe() {
        RxBus.getInstance().toObservable().subscribe(new Consumer<Object>() {

                                                         @Override
                                                         public void accept(Object o) throws Exception {

                                                             if (o instanceof TestEvent) {
                                                                 Toast.makeText(MainActivity.this, ((TestEvent) o).getMessage(), Toast.LENGTH_SHORT).show();
                                                             }
                                                         }

                                                     }
        );
    }
}
