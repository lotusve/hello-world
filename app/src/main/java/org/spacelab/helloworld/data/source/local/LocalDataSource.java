package org.spacelab.helloworld.data.source.local;

import org.spacelab.helloworld.data.source.DataSource;
import org.spacelab.helloworld.data.source.remote.http.gallery.RequestBean;

public class LocalDataSource implements DataSource {

    private static LocalDataSource INSTANCE;

    private DataDao dao;

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
    public void getData(RequestBean bean, GetDataCallback callback) {

    }

    @Override
    public void getData(String imageUrl) {

    }

    @Override
    public void saveData() {

    }
}
