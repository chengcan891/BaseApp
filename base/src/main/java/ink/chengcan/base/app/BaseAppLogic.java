package ink.chengcan.base.app;

import android.content.res.Configuration;

public class BaseAppLogic {
    protected BaseApplication mApplication;

    public void setApplication(BaseApplication baseApplication) {
        this.mApplication = baseApplication;
    }


    public void onCreate() {

    }


    public void onTerminate() {

    }


    public void onLowMemory() {

    }


    public void onConfigurationChanged(Configuration newConfig) {

    }
}
