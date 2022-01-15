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
import java.nio.charset.StandardCharsets;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Environment.getExternalStorageDirectory()
        //make main shared directory
        File mainDir = new File(getFilesDir() + "/singularity2022");
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
    }
}