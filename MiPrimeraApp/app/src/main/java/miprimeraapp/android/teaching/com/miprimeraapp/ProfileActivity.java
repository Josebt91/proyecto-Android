package miprimeraapp.android.teaching.com.miprimeraapp;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private RadioButton genderFemaleRadioButton;
    private RadioButton genderMasculineRadioButton;
    private EditText ageEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        usernameEditText = findViewById(R.id.username);
        emailEditText= findViewById(R.id.email);
        passwordEditText=findViewById(R.id.password);
        genderFemaleRadioButton=findViewById(R.id.genderfemale);
        genderMasculineRadioButton=findViewById(R.id.gendermasculine);
        ageEditText=findViewById(R.id.age);

        File imgFile = new File(getExternalFilesDir(null),"profile.png");

        if(imgFile.exists()){
            ImageView myImage=findViewById(R.id.profileImage);
            myImage.setImageURI(Uri.fromFile(imgFile));
        }

        ageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    //Mostrar DatePickerDialog
                    new DatePickerDialog(ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            int edad;
                            Calendar fecha = Calendar.getInstance();
                            int año = fecha.get(Calendar.YEAR);
                            edad=año-year;
                            ageEditText.setText(String.valueOf(edad));
                        }
                    },1990,01,01).show();
                }
            }
        });

        Toolbar toolbar =findViewById(R.id.menutoolbarprofile);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        String gender ="";

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_user), Context.MODE_PRIVATE);
        String usersave = sharedPref.getString("username","no username");


        if(usersave.equals("no username")) {
        }
        else{
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"database-name").allowMainThreadQueries().build();
            User user=db.userDao().findUserByUsername(usersave);
            usernameEditText.setText(user.getUsername());
            emailEditText.setText(user.getEmail());
            passwordEditText.setText(user.getPassword());
            ageEditText.setText(user.getAge());
            gender=user.getGender();
            if(gender.equals("MAN")){
                genderMasculineRadioButton.setChecked(true);
            }else if(gender.equals("WOMAN")){
                genderFemaleRadioButton.setChecked(true);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mtoolbarprofile,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveprofiletool:
                saveInternal();
                break;
            case R.id.deletefiletool:
                deleteInternal();
                break;
        }

        return true;
    }
    public void saveInternal(){

        Log.d("ProfileActivity","Username: "+ usernameEditText.getText());
        Log.d("ProfileActivity","Email: "+ emailEditText.getText());
        Log.d("ProfileActivity","Password: "+ passwordEditText.getText());
        Log.d("ProfileActivity","Age: "+ ageEditText.getText());

        String gender ="";

        if(genderMasculineRadioButton.isChecked()){
            Log.d("ProfileActivity","Gender: Male");
            gender= "MAN";
        }else if(genderFemaleRadioButton.isChecked()){
            Log.d("ProfileActivity","Gender: Female");
            gender= "WOMAN";
        }
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"database-name").allowMainThreadQueries().build();

        try{
            User user = new User();
            user.setUsername(usernameEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
            user.setAge(ageEditText.getText().toString());
            user.setGender(gender);
            db.userDao().insert(user);
        }
        catch(SQLiteConstraintException ex){
            Toast.makeText(ProfileActivity.this,"Invalid Username",Toast.LENGTH_LONG).show();
        }

    }
    public void deleteInternal(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ProfileActivity.this,"SI QUIERO",Toast.LENGTH_LONG).show();
            }

        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    public void guardarDatos(View view) {
        saveInternal();
    }
    public void borrarDatos(View view){
        deleteInternal();
    }


}
