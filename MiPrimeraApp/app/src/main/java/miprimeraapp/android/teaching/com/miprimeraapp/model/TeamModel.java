package miprimeraapp.android.teaching.com.miprimeraapp.model;

public class TeamModel {

    private int id;
    private String name;
    private int description;
    private String officialWebSiteUrl;
    private int iconDrawable;
    private int backgroundDrawable;

    public TeamModel(int id, String name, int description, String officialWebSiteUrl, int iconDrawable, int backgroundDrawable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.officialWebSiteUrl = officialWebSiteUrl;
        this.iconDrawable = iconDrawable;
        this.backgroundDrawable = backgroundDrawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public String getOfficialWebSiteUrl() {
        return officialWebSiteUrl;
    }

    public void setOfficialWebSiteUrl(String officialWebSiteUrl) {
        this.officialWebSiteUrl = officialWebSiteUrl;
    }

    public int getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(int iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    public int getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public void setBackgroundDrawable(int backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
    }
}
