package miprimeraapp.android.teaching.com.miprimeraapp;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText=findViewById(R.id.valueUsername);
        passwordEditText=findViewById(R.id.valuePassword);

        Toolbar toolbar =findViewById(R.id.menuloginactivity);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPref=getSharedPreferences(getString(R.string.preference_user),Context.MODE_PRIVATE);
        String value = sharedPref.getString("username","no-username");
        usernameEditText.setText(value);
    }


    public void doLogin(View view){
        String username = usernameEditText.getText().toString();
        String password= passwordEditText.getText().toString();

        //Check empty values
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            usernameEditText.setError(getString(R.string.ErrorLogin));
        }else{

        SharedPreferences sharedPref=getSharedPreferences(getString(R.string.preference_user), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username",usernameEditText.getText().toString());
        editor.apply();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"database-name").allowMainThreadQueries().build();

            User user = db.userDao().findUserByUsername(username);
            if(user==null){
                Toast.makeText(LoginActivity.this,"Login failed",Toast.LENGTH_LONG).show();
            }
            else if(user.getPassword().equalsIgnoreCase(password))
                {
                    Intent profileIntent=new Intent(this, ProfileActivity.class);
                    startActivity(profileIntent);
                }else{
                    Toast.makeText(LoginActivity.this,"Password incorrect",Toast.LENGTH_LONG).show();
                }
            }


    }
    public void doRegister(View view){
        Intent registerIntent=new Intent(this,ProfileActivity.class);
        startActivity(registerIntent);

    }

    public void onCancel(View view) {
        usernameEditText.setText("");
        passwordEditText.setText("");
    }
}
