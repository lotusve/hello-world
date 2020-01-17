package org.spacelab.helloworld.component;

import java.util.concurrent.Executor;

public class AppExecutors {

    private final Executor diskIO;

    private final Executor networkIO;

    private final Executor mainThread;

    public AppExecutors() {
        this.diskIO = new DiskIOThreadExecutor();
        this.networkIO = new NetworkIOThreadExecutor();
        this.mainThread = new MainThreadExecutor();
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor networkIO() {
        return networkIO;
    }

    public Executor mainThread() {
        return mainThread;
    }
}
