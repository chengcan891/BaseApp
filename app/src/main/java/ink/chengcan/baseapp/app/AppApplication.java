package ink.chengcan.baseapp.app;

import ink.chengcan.base.app.BaseApplication;
import ink.chengcan.testbus.app.TestBusApp;
import ink.chengcan.testroute.app.TestRouteApp;

public class AppApplication extends BaseApplication {

    @Override
    protected void initLogic() {
        register(TestBusApp.class, TestRouteApp.class);
    }
}
