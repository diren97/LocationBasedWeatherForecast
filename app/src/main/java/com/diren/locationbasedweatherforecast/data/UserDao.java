package com.diren.locationbasedweatherforecast.data;


import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.Query;
import com.diren.locationbasedweatherforecast.model.User;

import java.util.List;
@Dao
public interface UserDao {

    @Insert
    Long insertUser(User loginUser);

    @Insert
    void insertUser(List<User> loginUser);

    @Query("SELECT * FROM "+ UserDB.TABLE_LOGIN+" WHERE EMAIL=:email")
    User queryUser(String email);

    @Query("SELECT * FROM "+ UserDB.TABLE_LOGIN)
    User queryAllUser();

    @Query("SELECT * FROM "+ UserDB.TABLE_LOGIN+" WHERE EMAIL=:email and PASSWORD =:password")
    boolean queryUser(String email,String password);


}
