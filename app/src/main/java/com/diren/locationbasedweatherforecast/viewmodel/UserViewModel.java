package com.diren.locationbasedweatherforecast.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.diren.locationbasedweatherforecast.model.User;
import com.google.android.gms.maps.model.LatLng;

public class UserViewModel extends ViewModel {

    public MutableLiveData<String> EmailAdress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<User> userMutableLiveData;
    private MutableLiveData<String> userMutableLiveData2;

    public MutableLiveData<User> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }
    public MutableLiveData<String> register(){
        if (userMutableLiveData2 == null) {
            userMutableLiveData2 = new MutableLiveData<>();
        }
        return userMutableLiveData2;
    }

    public void onClick(View view) {
        User user = new User(EmailAdress.getValue(), password.getValue());
        userMutableLiveData.setValue(user);
    }

    public void onClickListener(View view) {
        userMutableLiveData2.setValue("");
    }

    public ObservableField<LatLng>  mMapLatLng = new ObservableField<>();

}
