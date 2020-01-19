package org.spacelab.helloworld.component;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * 主线程，线程池
 */
public class MainThreadExecutor implements Executor {

    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable command) {
        mainThreadHandler.post(command);
    }
}
