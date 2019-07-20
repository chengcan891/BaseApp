package ink.chengcan.route;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

// A more classic application is to handle login events during a jump so that there is no need to repeat the login check on the target page.
// Interceptors will be executed between jumps, multiple interceptors will be executed in order of priority
@Interceptor(priority = 8, name = "test interceptor")
public class TestInterceptor implements IInterceptor {

    private static String TAG = TestInterceptor.class.getSimpleName();

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        Log.i(TAG, "process");

        // No problem! hand over control to the framework
        callback.onContinue(postcard);

        // Interrupt routing process
        // callback.onInterrupt(new RuntimeException("Something exception"));

        // The above two types need to call at least one of them, otherwise it will not continue routing
    }

    @Override
    public void init(Context context) {
        // Interceptor initialization, this method will be called when sdk is initialized, it will only be called once
        Log.i(TAG, "init");
    }
}