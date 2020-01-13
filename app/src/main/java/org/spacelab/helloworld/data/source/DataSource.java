package org.spacelab.helloworld.data.source;

public interface DataSource {

    interface GetDataCallback {

        void onDataLoaded();

        void onDataNotAvailable();

    }

    void getData(GetDataCallback callback, String imageFilePath);

    void getData();

    void saveData();

}
