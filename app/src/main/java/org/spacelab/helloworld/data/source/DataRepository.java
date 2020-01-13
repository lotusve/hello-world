package org.spacelab.helloworld.data.source;

import org.spacelab.helloworld.data.source.remote.RemoteDataSource;
import org.spacelab.helloworld.data.source.remote.http.gallery.RequestBean;

public class DataRepository implements DataSource {

    private static DataRepository INSTANCE = null;

    private RemoteDataSource remoteDataSource;

    private DataRepository() {
        remoteDataSource = RemoteDataSource.getInstance();
    }

    public static DataRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getData(RequestBean bean, GetDataCallback callback) {
        remoteDataSource.getData(bean, callback);
    }

    @Override
    public void getData(String imageUrl) {
        remoteDataSource.getData(imageUrl);
    }

    @Override
    public void saveData() {

    }

}
