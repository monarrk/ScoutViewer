package singularity.scoutviewer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        final String PATH = getFilesDir().getAbsolutePath() + "/Singularity2022";

        // make main shared directory wherever PATH says to make it
        Files.mkDir(PATH);

        // write to a json file
        //Files.write(PATH + "/test.json", "{\"int\":1}");

        // read the match numbers/ids and populate the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Files.listWith(PATH, false,
                false));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameSelector.setAdapter(adapter);

        // looks at when the spinner gets set
        gameSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
                // basically magic
                textView.setText(Files.read(PATH + Files.listWith(PATH,
                        true, true, index)));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(),"No game selected",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}