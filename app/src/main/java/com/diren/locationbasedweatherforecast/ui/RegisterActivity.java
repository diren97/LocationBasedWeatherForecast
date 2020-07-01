package com.diren.locationbasedweatherforecast.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.diren.locationbasedweatherforecast.R;
import com.diren.locationbasedweatherforecast.data.UserDB;
import com.diren.locationbasedweatherforecast.data.UserDao;
import com.diren.locationbasedweatherforecast.databinding.ActivityMainBinding;
import com.diren.locationbasedweatherforecast.model.User;
import com.diren.locationbasedweatherforecast.viewmodel.UserViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText register_activity_email;
    TextInputEditText register_activity_fname;
    TextInputEditText re_register_activity_password;
    RelativeLayout rlayout;
    TextInputEditText register_activity_password;
    Button register_activity_button;
    private UserViewModel userViewModel;
    private ActivityMainBinding registerBinding;
    UserDB userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }
        setContentView(R.layout.activity_register);

        userDB = UserDB.getInstance(this);
        register_activity_email = findViewById(R.id.register_activity_email);
        register_activity_fname = findViewById(R.id.register_activity_fname);
        register_activity_password = findViewById(R.id.register_activity_password);
        re_register_activity_password = findViewById(R.id.re_register_activity_password);
        register_activity_button = findViewById(R.id.register_activity_button);
        rlayout = findViewById(R.id.rlayout);
        register_activity_button.setOnClickListener(v -> {
            emailControl(register_activity_email.getText().toString());
        });
    }
    private void register() {
        String username = register_activity_fname.getText().toString();
        String email = register_activity_email.getText().toString();
        String pass = register_activity_password.getText().toString();
        String re_pass = re_register_activity_password.getText().toString();
        if (username.isEmpty()) {
            register_activity_fname.setError("This field cannot be blank");
            register_activity_fname.requestFocus();
        }
        else if (email.isEmpty()) {
            register_activity_fname.setError("This field cannot be blank");
            register_activity_fname.requestFocus();
        }
        else if (!re_pass.equals(pass)) {
            Snackbar snackbar = Snackbar.make(rlayout, "Passwords do not match", Snackbar.LENGTH_LONG);
            snackbar.setAction("OK", v -> {
                snackbar.dismiss();
            });
            snackbar.show();
        }
        else if (pass.length() < 6) {
            Snackbar snackbar = Snackbar.make(rlayout, "Passwords must be at least 6 characters", Snackbar.LENGTH_LONG);
            snackbar.setAction("OK", v -> {
                snackbar.dismiss();
            });
            snackbar.show();
        }
        else if (!username.matches("") && !email.matches("") && pass.matches(re_pass)) {
            User login = new User(register_activity_email.getText().toString(), register_activity_password.getText().toString(), register_activity_fname.getText().toString());
            insertRow(login);
        }


    }

    public void emailControl(String email) {
        if (userDB.loginDAO().queryUser(email)!=null){
            Toast.makeText(RegisterActivity.this, "There are users", Toast.LENGTH_SHORT).show();

        }else{
            register();
        }

    }


    @SuppressLint("StaticFieldLeak")
    private void insertRow(User user) {
        new AsyncTask<User, Void, Long>() {
            @Override
            protected Long doInBackground(User... params) {
                Log.d("ok", "" + params[0]);
                return userDB.loginDAO().insertUser((User) params[0]);
            }

            @Override
            protected void onPostExecute(Long id) {
                super.onPostExecute(id);
                Toast.makeText(RegisterActivity.this, "Succesfuly "+id, Toast.LENGTH_SHORT)
                        .show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));


            }
        }.execute(user);

    }

}