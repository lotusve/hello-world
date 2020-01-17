package org.spacelab.helloworld.data.source.local;

import org.spacelab.helloworld.component.AppExecutors;
import org.spacelab.helloworld.data.source.DataSource;
import org.spacelab.helloworld.data.source.remote.http.gallery.RequestBean;

/**
 * 本地数据
 */
public class LocalDataSource implements DataSource {

    private static LocalDataSource INSTANCE;

    private DataDao mDataDao;

    private AppExecutors mAppExecutors;

    private LocalDataSource(DataDao dataDao, AppExecutors appExecutors) {
        mDataDao = dataDao;
        mAppExecutors = appExecutors;
    }

    public static LocalDataSource getInstance(DataDao dataDao, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (LocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocalDataSource(dataDao, appExecutors);
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
