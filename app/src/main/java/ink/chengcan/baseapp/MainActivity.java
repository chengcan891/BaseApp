package ink.chengcan.baseapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ink.chengcan.bus.RxBus;
import ink.chengcan.bus.test.TestEvent;
import ink.chengcan.dao.student.Student;
import ink.chengcan.dao.student.StudentDaoManager;
import ink.chengcan.testbus.TestBusActivity;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

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

        observe();

//        initGreenDao();
    }

    /**
     * 测试dao
     */
    private void initGreenDao() {
        Student student = new Student();
        student.setStudentNo(2);
        StudentDaoManager.save(this, student);
        List<Student> students = StudentDaoManager.query(this, 10, 0);
        Log.i("Application", students.get(0).getId() + "id");
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
