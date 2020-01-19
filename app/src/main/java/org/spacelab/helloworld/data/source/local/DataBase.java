package org.spacelab.helloworld.data.source.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.spacelab.helloworld.data.entiry.Image;

/**
 * 数据库
 */
@Database(entities = {Image.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    private static DataBase instance;

    public abstract ImageDao dataDao();

    private static final Object sLock = new Object();

    public static DataBase getInstance(Context context) {
        synchronized (sLock) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, "Helloworld.db").build();
            }
            return instance;
        }
    }

}
