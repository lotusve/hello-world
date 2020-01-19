package org.spacelab.helloworld.component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * DiskIO 线程池
 */
public class DiskIOThreadExecutor implements Executor {

    private final Executor mDiskIO;

    public DiskIOThreadExecutor() {
        mDiskIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable command) {
        mDiskIO.execute(command);
    }

}
