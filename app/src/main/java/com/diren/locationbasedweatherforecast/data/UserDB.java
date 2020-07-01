package com.diren.locationbasedweatherforecast.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.diren.locationbasedweatherforecast.model.User;

@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class UserDB extends RoomDatabase {

    public static final String DATABASE_NAME = "login_database";

    public static final String TABLE_LOGIN = "login";
    private static UserDB instance=null;
    Context context;
    public abstract UserDao loginDAO();


    public static UserDB getInstance(Context context) {
        if (instance == null) {
            synchronized (UserDB.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        UserDB.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();

            }
        }
        return instance;
    }
    public static void destroyInstance()
    {
        instance = null;
    }
}