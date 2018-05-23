package miprimeraapp.android.teaching.com.miprimeraapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","onDestroy");
    }

    public void selectSex(View view){
        Intent intent=new Intent(this,SecondActivity.class);
        intent.putExtra("id","Android Roolz");
        startActivity(intent);
    }

    public void selectGoogle(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        startActivity(intent);
    }

    public void selectGallery(View view){
        Intent intent=new Intent(this,GalleryActivity.class);
        startActivity(intent);
    }
    public void selectLogin(View view){
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    public void selectLogin1(View view){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public void selectList(View view){
        Intent intent=new Intent(this,ListActivity.class);
        startActivity(intent);
    }


}
