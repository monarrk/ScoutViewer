package singularity.scoutviewer;

import android.graphics.Point;

import org.json.JSONException;
import org.json.JSONObject;

public class Team {
    int matchNum;
    int teamNum;
    boolean isBlue;
    int startingPos;

    boolean taxi;
    int autoLowerHub;
    int autoUpperHub;
    int teleLowerHub;
    int teleUpperHub;
    int hanger;

    public Team(String path, int index) throws JSONException {
        String json = Files.read(path + Files.listWith(path, true, true, index));
        JSONObject jObject = new JSONObject(json);

        matchNum = jObject.getInt("matchNum");
        teamNum = jObject.getInt("teamNum");
        isBlue = jObject.getBoolean("isBlue");
        startingPos = jObject.getInt("startingPos");

        taxi = jObject.getBoolean("taxi");
        autoLowerHub = jObject.getInt("autoLowerHub");
        autoUpperHub = jObject.getInt("autoUpperHub");
        teleLowerHub = jObject.getInt("teleLowerHub");
        teleUpperHub = jObject.getInt("teleUpperHub");
        hanger = jObject.getInt("hanger");
    }

    public int getMatchNum() {
        return matchNum;
    }
    public int getTeamNum() {
        return teamNum;
    }
    public boolean getIsBlue() {
        return isBlue;
    }
    public int getStartingPos() {
        return startingPos;
    }
    public boolean getTaxi() {
        return taxi;
    }
    public int getAutoLowerHub() {
        return autoLowerHub;
    }
    public int getAutoUpperHub() {
        return autoUpperHub;
    }
    public int getTeleLowerHub() {
        return teleLowerHub;
    }
    public int getTeleUpperHub() {
        return teleUpperHub;
    }
    public int getHanger() {
        return hanger;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    public int getSubtotal() {
        int total = 0;

        if (getTaxi()) total += Points.TAXI;
        total += getAutoLowerHub() * Points.AUTO_LOWER_HUB;
        total += getAutoUpperHub() * Points.AUTO_UPPER_HUB;
        total += getTeleLowerHub() * Points.TELE_LOWER_HUB;
        total += getTeleUpperHub() * Points.TELE_UPPER_HUB;
        total += Points.HANGER[getHanger()];

        return total;
    }

    public String toString() {
        return Integer.toString(getTeamNum());
    }
}
