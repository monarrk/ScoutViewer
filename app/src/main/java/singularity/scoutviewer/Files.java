package singularity.scoutviewer;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Files {
    public static void write(Activity a, String p, String s) {
        try {
            FileOutputStream fos = new FileOutputStream(a.getFilesDir() + p);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(s);
            osw.flush();
            fos.close();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
