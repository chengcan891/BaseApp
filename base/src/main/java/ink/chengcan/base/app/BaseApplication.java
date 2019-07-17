package ink.chengcan.base.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ink.chengcan.base.BuildConfig;

public abstract class BaseApplication extends Application {

    public static Activity context;

    private List<Class<? extends BaseAppLogic>> baseLogicClassList = new ArrayList<>();
    private List<BaseAppLogic> baseLogicList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(getCallback());

        //
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        initLogic();
        logicCreate();

        for (BaseAppLogic baseAppLogic : baseLogicList) {
            baseAppLogic.setApplication(this);
        }

        for (BaseAppLogic baseAppLogic : baseLogicList) {
            baseAppLogic.onCreate();
        }

    }


    protected abstract void initLogic();

    private void logicCreate() {
        for (Class<? extends BaseAppLogic> logicClazz : baseLogicClassList) {
            try {
                BaseAppLogic baseAppLogic = logicClazz.newInstance();
                baseLogicList.add(baseAppLogic);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    protected void register(Class<? extends BaseAppLogic>... baseLogicClazz) {
        this.baseLogicClassList.addAll(Arrays.asList(baseLogicClazz));
    }

    private ActivityLifecycleCallbacks getCallback() {
        return new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                context = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        };
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (BaseAppLogic baseAppLogic : baseLogicList) {
            baseAppLogic.onTerminate();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (BaseAppLogic baseAppLogic : baseLogicList) {
            baseAppLogic.onLowMemory();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        for (BaseAppLogic baseAppLogic : baseLogicList) {
            baseAppLogic.onConfigurationChanged(newConfig);
        }
    }
}
