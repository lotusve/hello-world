package org.spacelab.helloworld.data.source;

public class DataRepository implements DataSource {

    private static DataRepository INSTANCE = null;

    private DataRepository() {

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
    public void getData(GetDataCallback callback) {

    }

    @Override
    public void saveData() {

    }

}
