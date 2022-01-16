package singularity.scoutviewer;

import org.json.JSONException;

public class Match {
    Team[] team = new Team[6];

    public Match(String filePath) throws JSONException {
        for (int i = 0; i < team.length; i++) {
            team[i] = new Team(filePath, i);
        }
    }

    public int getBlueTotal() {
        int total = 0;
        for (int i = 0; i < team.length; i++) {
            if (team[i].getIsBlue()) {
                total += team[i].getSubtotal();
            }
        }
        return total;
    }

    public int getRedTotal() {
        int total = 0;
        for (int i = 0; i < team.length; i++) {
            if (!team[i].getIsBlue()) {
                total += team[i].getSubtotal();
            }
        }
        return total;
    }

    public Team getWinners() {
        if (getBlueTotal() > )
    }
}
