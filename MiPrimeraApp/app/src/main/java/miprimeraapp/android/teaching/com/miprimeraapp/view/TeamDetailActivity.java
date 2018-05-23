package miprimeraapp.android.teaching.com.miprimeraapp.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import miprimeraapp.android.teaching.com.miprimeraapp.R;
import miprimeraapp.android.teaching.com.miprimeraapp.model.TeamModel;
import miprimeraapp.android.teaching.com.miprimeraapp.presenter.TeamDetailPresenter;

public class TeamDetailActivity extends AppCompatActivity implements TeamDetailView{

    private TeamDetailPresenter presenter;
    private int currentTeamId;
    private String currentTeamWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        presenter= new TeamDetailPresenter();

        currentTeamId=getIntent().getIntExtra("team_id",0);

        Toolbar toolbar =findViewById(R.id.detailtoolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    protected void onStart(){
        super.onStart();
        presenter.startPresenting(this);
        presenter.loadTeamWithId(currentTeamId);
    }
    @Override
    public void onTeamLoaded(TeamModel team) {
        ImageView icono = findViewById(R.id.detailimage);
        icono.setImageResource(team.getIconDrawable());

        ImageView fondo = findViewById(R.id.detailimage);
        fondo.setBackgroundResource(team.getBackgroundDrawable());

        TextView texto = findViewById(R.id.detailtext);
        texto.setText(team.getDescription());

        Toolbar toolbar = findViewById(R.id.detailtoolbar);
        toolbar.setTitle(team.getName());

        this.currentTeamWeb=team.getOfficialWebSiteUrl();

    }
    public void selectwebsite(View view){
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(currentTeamWeb));
        startActivity(websiteIntent);
    }
}
