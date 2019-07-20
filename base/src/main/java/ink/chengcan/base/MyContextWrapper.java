package ink.chengcan.base;

import android.content.Context;
import android.content.ContextWrapper;

public class MyContextWrapper extends ContextWrapper {
    public MyContextWrapper(Context base) {
        super(base);
    }
}
