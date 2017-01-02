package aeontanvir.com.mobitourmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import aeontanvir.com.mobitourmate.db.DBLoginManager;
import aeontanvir.com.mobitourmate.pojo.User;
import aeontanvir.com.mobitourmate.preference.Preference;

public class LoginActivity extends AppCompatActivity {

    TextView logEdtUsername, logEdtPassword;

    DBLoginManager dbLoginManager;

    Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbLoginManager = new DBLoginManager(LoginActivity.this);

        preference = new Preference(this);

        logEdtUsername = (TextView) findViewById(R.id.logEdtUsername);
        logEdtPassword = (TextView) findViewById(R.id.logEdtPassword);



    }

    public void doLogin(View view) {

        String username = String.valueOf(logEdtUsername.getText());
        String password = String.valueOf(logEdtPassword.getText());

        if(username.equals("")){
            makeToast("Enter your username!");
        }else if(password.equals("")){
            makeToast("Enter your password!");
        }else{
            User user = dbLoginManager.getUser(1);
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
                preference.saveUserData("in");
                finish();
            }else {
                makeToast("Enter valid username and password");
                logEdtPassword.setText("");
            }

        }

    }

    public void makeToast(String messege){
        Toast.makeText(LoginActivity.this, messege, Toast.LENGTH_SHORT).show();
    }
}
