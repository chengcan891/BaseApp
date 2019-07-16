package ink.chengcan.baseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

import ink.chengcan.base.bus.RxBus;
import ink.chengcan.base.route.RouteKey;
import ink.chengcan.base.route.RoutePath;
import ink.chengcan.baseapp.bus.TestBusActivity;
import ink.chengcan.baseapp.bus.TestEvent;
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

        findViewById(R.id.btn_test_route).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RoutePath.APP_ROUTE).withString(RouteKey.MESSAGE, "t").navigation();
            }
        });
        observe();

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
