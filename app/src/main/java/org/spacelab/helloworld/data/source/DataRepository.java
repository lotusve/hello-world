package org.spacelab.helloworld.data.source;

import org.spacelab.helloworld.data.source.remote.RemoteDataSource;

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
    public void getData(GetDataCallback callback, String imageFilePath) {

        remoteDataSource.getData(callback, imageFilePath);

    }

    @Override
    public void getData() {
        remoteDataSource.getData();
    }

    @Override
    public void saveData() {

    }

}
