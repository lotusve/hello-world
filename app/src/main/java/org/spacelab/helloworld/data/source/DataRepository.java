package org.spacelab.helloworld.data.source;

import org.spacelab.helloworld.data.source.remote.http.gallery.RequestBean;

public class DataRepository implements DataSource {

    private static DataRepository INSTANCE = null;

    private final DataSource mRemoteDataSource;

    private final DataSource mLocalDataSource;

    private DataRepository(DataSource remoteDataSource, DataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public static DataRepository getInstance(DataSource remoteDataSource, DataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository(remoteDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getData(RequestBean bean, GetDataCallback callback) {
        mRemoteDataSource.getData(bean, callback);
    }

    @Override
    public void getData(String imageUrl) {
        mRemoteDataSource.getData(imageUrl);
    }

    @Override
    public void saveData() {

    }

}
