package com.diren.locationbasedweatherforecast.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.diren.locationbasedweatherforecast.R;
import com.diren.locationbasedweatherforecast.data.UserDB;
import com.diren.locationbasedweatherforecast.databinding.ActivityMainBinding;
import com.diren.locationbasedweatherforecast.model.User;
import com.diren.locationbasedweatherforecast.viewmodel.UserViewModel;
public class MainActivity  extends AppCompatActivity {

    private UserViewModel userViewModel;
    private ActivityMainBinding loginBinding;
    UserDB userDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userDB = UserDB.getInstance(this);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        loginBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        loginBinding.setLifecycleOwner(this);
        loginBinding.setUserViewModel(userViewModel);
        userViewModel.getUser().observe(this, loginUser -> {
            if (TextUtils.isEmpty(loginUser.getStrEmailAddress())) {
                loginBinding.loginActivityEmail.setError("Enter an E-mail address");
                loginBinding.loginActivityEmail.requestFocus();
            } else if (!loginUser.isEmailValid()) {
                loginBinding.loginActivityEmail.setError("Enter a Valid Email Address");
                loginBinding.loginActivityEmail.requestFocus();
            } else if (TextUtils.isEmpty(loginUser.getStrPassword())) {
                loginBinding.loginActivityPassword.setError("Enter a Password");
                loginBinding.loginActivityPassword.requestFocus();
            } else if (!loginUser.isPasswordLengthGreaterThan5()) {
                loginBinding.loginActivityPassword.setError("Enter at least 6 Digit password");
                loginBinding.loginActivityPassword.requestFocus();
            } else {
                loginBinding.loginActivityButton.setOnClickListener(v -> {
                    loadControlUser(loginBinding.loginActivityEmail.getText().toString(), loginBinding.loginActivityPassword.getText().toString());
                });
            }

        });

        userViewModel.register().observe(this, v -> {

            loginBinding.loginActivityRegisterText.setOnClickListener(v1 -> {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            });

        });



    }


    boolean isOk = false;

    @SuppressLint("StaticFieldLeak")
    private void loadControlUser(String email, String password) {

        new AsyncTask<String, String, User>() {
            @Override
            protected User doInBackground(String... params) {

                if (userDB.loginDAO().queryUser(email, password)) {
                    isOk = true;
                }
                return null;
            }

            @Override
            protected void onPostExecute(User todoList) {
                if (isOk) {
                    Toast.makeText(MainActivity.this, "Welcome To Do App ", Toast.LENGTH_SHORT).show();
                    userAdd();
                    startActivity(new Intent(MainActivity.this, MapActivity.class));

                } else {
                    Toast.makeText(MainActivity.this, "find not User ", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(email, password);
    }

    private static String PREFS_NAME = "myInfoS";
    public static String PREF_USERNAME = "username";
    public static String PREF_PASSWORD = "password";

    private void userAdd() {
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String username = loginBinding.loginActivityEmail.getText().toString();
        String password = loginBinding.loginActivityEmail.getText().toString();
        editor.putString(PREF_USERNAME, username);
        editor.putString(PREF_PASSWORD, password);
        editor.apply();
    }


}
