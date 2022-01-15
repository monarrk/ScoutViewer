package singularity.scoutviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import android.widget.Toast;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //make main shared directory
/*        Log.e("MainActivity", getFilesDir().getAbsolutePath());
        File mainDir = new File(getFilesDir().getAbsolutePath() + "/singularity2022");
        if(!mainDir.exists()) {
            Toast.makeText(getApplicationContext(),"Main directory does not exist, creating...",
                    Toast.LENGTH_LONG).show();
            mainDir.mkdirs();
        }
        if(mainDir.exists()) {
            Toast.makeText(getApplication(),"Directory created",
                    Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Failed to create Directory",
                    Toast.LENGTH_LONG).show();
        }

*/
        Files.write(this, "/test.json", "{\"int\":1}");

        File test = new File(getFilesDir().getAbsolutePath() + "/test.txt");
        if(test.exists()) {
            Toast.makeText(getApplication(),"test.txt exists",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplication(),"test.txt not exists",
                    Toast.LENGTH_LONG).show();
        }
    }
}