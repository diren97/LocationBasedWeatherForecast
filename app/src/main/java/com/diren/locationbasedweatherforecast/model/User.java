package com.diren.locationbasedweatherforecast.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Patterns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.diren.locationbasedweatherforecast.data.UserDB;

import java.io.Serializable;


@Entity(tableName = UserDB.TABLE_LOGIN)
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long  ID;

    @ColumnInfo(name = "EMAIL")
    private String strEmailAddress;

    @ColumnInfo(name = "PASSWORD")
    private String strPassword;

    @ColumnInfo(name = "NAMESURNAME")
    private String nameSurname;
    @Ignore
    public User(String EmailAddress, String Password) {
        strEmailAddress = EmailAddress;
        strPassword = Password;
    }
    @Ignore
    public User(String strEmailAddress, String strPassword, String nameSurname) {
        this.strEmailAddress = strEmailAddress;
        this.strPassword = strPassword;
        this.nameSurname = nameSurname;
    }

    @Ignore
    public User(Long  id, String nameSurname, String strEmailAddress, String strPassword) {
        this.ID = id;
        this.strEmailAddress = strEmailAddress;
        this.strPassword = strPassword;
        this.nameSurname = nameSurname;
    }

    public User() {
    }


    public String getStrEmailAddress() {
        return strEmailAddress;
    }

    public String getStrPassword() {
        return strPassword;
    }


    public void setStrEmailAddress(String strEmailAddress) {
        this.strEmailAddress = strEmailAddress;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 5;
    }




}
