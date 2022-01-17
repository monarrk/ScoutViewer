package singularity.scoutviewer;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        Spinner gameSelector = findViewById(R.id.gameSelector);
        EditText password = findViewById(R.id.password);
        Button clearBtn = findViewById(R.id.clearBtn);
        final String PATH = getFilesDir().getAbsolutePath() + "/Singularity2022";
        final String PASSWORD = "singularity";
        // doesn't need to be secure

        // make main shared directory wherever PATH says to make it
        Files.mkDir(PATH);

        // write to a json file
        Files.write(PATH + "/test.json", "{\"int\":1}");

        reloadSpinner(PATH, gameSelector);

        // looks at when the spinner gets set
        gameSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                // basically magic
                textView.setText(Files.read(PATH + Files.listWith(PATH,
                        true, true, index)));
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
                        Toast.LENGTH_LONG).show();
            }
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