package ink.chengcan.testroute;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import ink.chengcan.base.BaseActivity;
import ink.chengcan.route.CommonRouteKey;
import ink.chengcan.route.TestRoutePath;


@Route(path = TestRoutePath.APP_ROUTE)
public class ARouteActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText(getIntent().getStringExtra(CommonRouteKey.MESSAGE));
        setContentView(textView);
    }
}
