package ink.chengcan.testroute.app;

import android.util.Log;

import ink.chengcan.base.app.BaseAppLogic;

public class TestRouteApp extends BaseAppLogic {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Application", "TestBusApp onCreate" + mApplication);
    }

}
