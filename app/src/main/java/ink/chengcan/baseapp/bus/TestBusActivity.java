package ink.chengcan.baseapp.bus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import ink.chengcan.base.BaseActivity;
import ink.chengcan.base.bus.RxBus;

public class TestBusActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Button button = new Button(this);
        button.setWidth(100);
        button.setHeight(100);
        button.setText("Send Data");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getInstance().send(new TestEvent("testBus"));
                finish();

            }
        });

        setContentView(button);
    }
}
