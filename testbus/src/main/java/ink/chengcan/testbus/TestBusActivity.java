package ink.chengcan.testbus;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import ink.chengcan.base.BaseActivity;
import ink.chengcan.bus.RxBus;
import ink.chengcan.bus.test.TestEvent;
import ink.chengcan.route.BusRoutePath;
import ink.chengcan.route.CommonRouteKey;
import ink.chengcan.route.TestRoutePath;

@Route(path = BusRoutePath.TEST_ROUTE)
public class TestBusActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bus);

        findViewById(R.id.btn_testBus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getInstance().send(new TestEvent("testBus"));
                finish();
            }
        });
        findViewById(R.id.btn_test_route).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(TestRoutePath.APP_ROUTE).withString(CommonRouteKey.MESSAGE, "t").navigation();
                finish();
            }
        });
    }
}
