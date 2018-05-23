package miprimeraapp.android.teaching.com.miprimeraapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.zip.Inflater;

import miprimeraapp.android.teaching.com.miprimeraapp.interactors.TeamInteractor;
import miprimeraapp.android.teaching.com.miprimeraapp.view.TeamDetailActivity;


public class ListActivity extends AppCompatActivity {

    String[] teamNames={"Real Madrid FC","FC Barcelona","Villareal FC"};
    int[] teamIcons={R.drawable.realmadrid,R.drawable.barcelona,R.drawable.villareal};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Crea automáticamente la carpeta privada en sdcard
        getExternalFilesDir(null);

        ListView listView=findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(ListActivity.this, TeamDetailActivity.class);
               int teamId = new TeamInteractor().getTeams().get(position).getId();
               intent.putExtra("team_id",teamId);

               startActivity(intent);
           }
       });

        Toolbar toolbar =findViewById(R.id.menulistactivity);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menutoolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        return true;
    }


    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView=inflater.inflate(R.layout.list_item,parent,false);

            ImageView icon = rowView.findViewById(R.id.image_view);
            icon.setImageResource(teamIcons[position]);

            TextView text = rowView.findViewById(R.id.text_view);
            text.setText(teamNames[position]);
            return rowView;
        }
    }
}
