package miprimeraapp.android.teaching.com.miprimeraapp.interactors;

import java.util.ArrayList;

import miprimeraapp.android.teaching.com.miprimeraapp.R;
import miprimeraapp.android.teaching.com.miprimeraapp.model.TeamModel;

public class TeamInteractor {

    private static ArrayList<TeamModel> teams;

    public TeamInteractor(){
        if(teams==null){
            TeamModel rmTeamModel = new TeamModel(0,
                    "Real Madrid",
                    R.string.Detailtextrm,
                    "https://www.realmadrid.com",
                    R.drawable.realmadrid,R.drawable.detalle_rm);
            TeamModel bTeamModel = new TeamModel(1,
                    "FC Barcelona",
                    R.string.Detailtextb,
                    "https://www.fcbarcelona.es",
                    R.drawable.barcelona,R.drawable.detalle_b);
            TeamModel vTeamModel = new TeamModel(2,
                    "Villareal",
                    R.string.Detailtextv,
                    "https://www.villarealcf.es",
                    R.drawable.villareal,R.drawable.detalle_v);
            teams=new ArrayList<>();
            teams.add(rmTeamModel);
            teams.add(bTeamModel);
            teams.add(vTeamModel);
        }
    }

    public static ArrayList<TeamModel> getTeams() {
        return teams;
    }

    public TeamModel getTeamWithId(int id){
        for(TeamModel team: teams){
            if(team.getId()==id){
                return team;
            }
        }
        return null;
    }
}
