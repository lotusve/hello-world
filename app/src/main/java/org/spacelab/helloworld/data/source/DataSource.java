package org.spacelab.helloworld.data.source;

import org.spacelab.helloworld.data.entiry.Image;
import org.spacelab.helloworld.data.source.remote.http.gallery.RequestBean;
import org.spacelab.helloworld.data.source.remote.http.gallery.ResponseBean;

/**
 * 数据统一接口
 */
public interface DataSource {

    interface GetDataCallback {

        void onDataLoaded(ResponseBean bean);

        void onDataNotAvailable();

    }

    void getData(RequestBean bean, GetDataCallback callback);

    void getData(String imageUrl);

    void saveImage(Image image);

}
