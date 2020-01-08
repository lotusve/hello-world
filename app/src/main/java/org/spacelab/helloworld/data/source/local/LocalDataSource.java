package org.spacelab.helloworld.data.source.local;

import org.spacelab.helloworld.data.source.DataSource;

public class LocalDataSource implements DataSource {

    private static LocalDataSource INSTANCE;

    private HelloWorldDao dao;

    private LocalDataSource() {

    }

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (LocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSource();
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
