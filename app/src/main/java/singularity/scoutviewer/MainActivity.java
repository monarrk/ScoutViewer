package singularity.scoutviewer;

import static android.content.pm.PackageManager.PERMISSION_DENIED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import org.json.JSONException;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                "android.permission.WRITE_EXTERNAL_STORAGE") == PERMISSION_DENIED) {
            //registerForActivityResult()
        }

        TextView textView = findViewById(R.id.textView);
        Spinner gameSelector = findViewById(R.id.gameSelector);
        EditText password = findViewById(R.id.password);
        Button clearBtn = findViewById(R.id.clearBtn);
        Button reloadBtn = findViewById(R.id.reloadBtn);
        final String PATH = getFilesDir().getAbsolutePath() + "/Singularity2022";
        //final String PATH = "dataTest/Singularity";
        final String PASSWORD = "singularity";
        // doesn't need to be secure

        // make main shared directory wherever PATH says to make it
        Files.mkDir(PATH);

        Log.e("path", PATH);

        // write to a json file
        Files.mkDir(PATH + "01-16-2022:match#1");
            Files.write(PATH + "5066.json",
                        "{\n" +
                            "  \"matchNum\":            1,\n" +
                            "  \"teamNum\":          5066,\n" +
                            "  \"isBlue\":           true,\n" +
                            "  \"startingPos\":         1,\n" +
                            "\n" +
                            "  \"taxi\":             true,\n" +
                            "  \"autoLowerHub\":        5,\n" +
                            "  \"autoUpperHub\":        2,\n" +
                            "  \"teleLowerHub\":        5,\n" +
                            "  \"teleUpperHub\":       12,\n" +
                            "  \"hanger\":              3\n" +
                            "}");

        reloadSpinner(PATH, gameSelector);

        // looks at when the spinner gets set
        gameSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                // basically magic
                //try {
                    //Match match = new Match(PATH + Files.listWith(PATH, true,
                            //true, index));
                    //textView.setText(match.displayMatch());
                    textView.setText(Files.read(PATH + "5066.json"));
                //} catch (JSONException e) {
                    //e.printStackTrace();
                    //textView.setText("File not found");
                //}

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("No file selected");
            }
        });

        // clear data
        clearBtn.setOnClickListener(v -> {
            if (password.getText().toString().equals(PASSWORD)) {
                password.getText().clear();

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Clear All Scouting Data")
                        .setMessage("Are you sure you want to delete all scouting data?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            // clear data
                            Toast.makeText(getApplicationContext(),"Clearing all scouting data",
                            Toast.LENGTH_LONG).show();
                            Files.deleteFolder(PATH);
                            Files.mkDir(PATH);
                            reloadSpinner(PATH, gameSelector);
                            textView.setText("No file selected");
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),"Incorrect Password",
                        Toast.LENGTH_SHORT).show();
            }
        });

        reloadBtn.setOnClickListener(v -> {
            reloadSpinner(PATH, gameSelector);
            Toast.makeText(getApplicationContext(),"Selector Reloaded",
                    Toast.LENGTH_SHORT).show();
        });
    }

    public void reloadSpinner(String path, Spinner spinner) {
        // read the match numbers/ids and populate the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Files.listWith(path, false,
                false));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}