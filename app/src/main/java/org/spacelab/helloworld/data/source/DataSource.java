package org.spacelab.helloworld.data.source;

public interface DataSource {

    interface GetDataCallback {

        void onDataLoaded();

        void onDataNotAvailable();

    }

    void getData(GetDataCallback callback);

    void getData();

    void saveData();

}
