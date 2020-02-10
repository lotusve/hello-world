package org.spacelab.helloworld.data.source.local;

import org.spacelab.helloworld.component.AppExecutors;
import org.spacelab.helloworld.data.entiry.Image;
import org.spacelab.helloworld.data.source.DataSource;
import org.spacelab.helloworld.data.source.remote.http.gallery.RequestBean;

/**
 * 本地数据
 */
public class LocalDataSource implements DataSource {

    private static LocalDataSource INSTANCE;

    private ImageDao mImageDao;

    private AppExecutors mAppExecutors;

    private LocalDataSource(ImageDao imageDao, AppExecutors appExecutors) {
        mImageDao = imageDao;
        mAppExecutors = appExecutors;
    }

    public static LocalDataSource getInstance(ImageDao dataDao, AppExecutors appExecutors) {
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
    public void saveImage(final Image image) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mImageDao.insertImage(image);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }
}
