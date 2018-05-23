package miprimeraapp.android.teaching.com.miprimeraapp.presenter;

import miprimeraapp.android.teaching.com.miprimeraapp.interactors.TeamInteractor;
import miprimeraapp.android.teaching.com.miprimeraapp.model.TeamModel;
import miprimeraapp.android.teaching.com.miprimeraapp.view.TeamDetailView;

public class TeamDetailPresenter {

    private TeamInteractor interactor;
    private TeamDetailView view;

    public void startPresenting(TeamDetailView view){
        this.view=view;
        interactor=new TeamInteractor();
    }
    public void loadTeamWithId(int id){
        TeamModel team = interactor.getTeamWithId(id);
        view.onTeamLoaded(team);
    }
}
