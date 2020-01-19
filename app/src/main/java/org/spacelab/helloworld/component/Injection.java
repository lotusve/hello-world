package org.spacelab.helloworld.component;

import android.content.Context;

import org.spacelab.helloworld.data.source.DataRepository;
import org.spacelab.helloworld.data.source.local.DataBase;
import org.spacelab.helloworld.data.source.local.LocalDataSource;
import org.spacelab.helloworld.data.source.remote.RemoteDataSource;

/**
 * 构造 app DataRepository
 */
public class Injection {

    public static DataRepository proviceDataRepository(Context context) {

        DataBase dataBase = DataBase.getInstance(context);

        return DataRepository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(dataBase.dataDao(), new AppExecutors()));
    }

}
