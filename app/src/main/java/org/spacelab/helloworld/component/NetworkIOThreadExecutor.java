package org.spacelab.helloworld.component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NetworkIOThreadExecutor implements Executor {

    private static final int THREAD_COUNT = 3;

    private final Executor mNetworkIO;

    public NetworkIOThreadExecutor() {
        mNetworkIO = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    @Override
    public void execute(Runnable command) {
        mNetworkIO.execute(command);
    }
}
