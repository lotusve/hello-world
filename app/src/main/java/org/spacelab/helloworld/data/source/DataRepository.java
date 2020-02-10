package org.spacelab.helloworld.data.source;

import org.spacelab.helloworld.data.entiry.Image;
import org.spacelab.helloworld.data.source.remote.http.gallery.RequestBean;
import org.spacelab.helloworld.data.source.remote.http.gallery.ResponseBean;

/**
 * 统一数据接口，实现类
 */
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
    public void getData(RequestBean bean, final GetDataCallback callback) {
        mRemoteDataSource.getData(bean, new GetDataCallback() {
            @Override
            public void onDataLoaded(ResponseBean bean) {
                callback.onDataLoaded(bean);

                Image image = new Image(bean.getImage_id(), bean.getRequest_id(), bean.getTime_used(), bean.getError_message());

                mLocalDataSource.saveImage(image);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getData(String imageUrl) {
        mRemoteDataSource.getData(imageUrl);
    }

    @Override
    public void saveImage(Image image) {

    }
}
