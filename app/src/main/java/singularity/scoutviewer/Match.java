package singularity.scoutviewer;

import org.json.JSONException;

public class Match {
    Team[] team = new Team[6];

    public Match(String filePath) throws JSONException {
        for (int i = 0; i < team.length; i++) {
            team[i] = new Team(filePath, i);
        }
    }

    public int getMatchNum() {
        return team[0].getMatchNum();
    }

    public String toString()  {
        return Integer.toString(getMatchNum());
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

    public String getWinners() {
        if (getBlueTotal() > getRedTotal()) {
            return "blue";
        } else if (getBlueTotal() < getRedTotal()) {
            return "red";
        } else {
            return "tie";
        }
    }

    public Team[] getBlueAlliance() {
        int i = 0;
        for (int j = 0; j < team.length; j++) {
            if (team[i].getIsBlue()) {
                i++;
            }
        }
        Team[] blue = new Team[i];
        i = 0;
        for (int j = 0; j < team.length; j++) {
            if (team[i].getIsBlue()) {
                blue[i++] = team[j];
            }
        }
        return blue;
    }

    public Team[] getRedAlliance() {
        int i = 0;
        for (int j = 0; j < team.length; j++) {
            if (!team[i].getIsBlue()) {
                i++;
            }
        }
        Team[] red = new Team[i];
        i = 0;
        for (int j = 0; j < team.length; j++) {
            if (!team[i].getIsBlue()) {
                red[i++] = team[j];
            }
        }
        return red;
    }
}
