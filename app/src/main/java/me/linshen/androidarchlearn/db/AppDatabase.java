package me.linshen.androidarchlearn.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import me.linshen.androidarchlearn.model.UserEntity;
import me.linshen.androidarchlearn.model.UserEntityDao;

/**
 * Created by linshen on 17-8-3.
 */

@Database(entities = {UserEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public static AppDatabase getDatabase(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    "user.db").build();
        }
        return sInstance;
    }

    public static void onDestroy() {
        sInstance = null;
    }

    public abstract UserEntityDao getUserEntityDao();

}
