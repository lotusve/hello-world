package org.spacelab.helloworld.data.source.remote;

import org.spacelab.helloworld.data.source.DataSource;

public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE;

    private RemoteDataSource() {

    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getData(GetDataCallback callback) {

    }

    @Override
    public void saveData() {

    }
}
