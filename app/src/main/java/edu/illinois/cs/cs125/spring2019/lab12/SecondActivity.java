package edu.illinois.cs.cs125.spring2019.lab12;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;


/**
 * Main class for our UI design lab.
 */
public class SecondActivity extends AppCompatActivity {
    /**
     * oho.
     */
    private Button calButton;
    /**
     * oho.
     */
    private EditText setHeight, setWeight;
    /**
     * oho.
     */
    private TextView result;
    /**
     * oho.
     */
    private static final String TAG = "Lab12:Main";
    /**
     * oho.
     */
    private static RequestQueue requestQueue;
    /**
     * oho.
     */
    public static TextView fetchedData;
    /**
     * oho.
     */
    private Button fetching;
    /**
     * oho.
     */
    private static double bmi;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        calButton = (Button) findViewById(R.id.calButton);
        fetching = (Button) findViewById(R.id.button);
        setHeight = (EditText) findViewById(R.id.setHeight);
        setWeight = (EditText) findViewById(R.id.setWeight);
        result = (TextView) findViewById(R.id.textView3);
        fetchedData = (TextView) findViewById(R.id.textView4);
        calButton.setOnClickListener(new CalBmi());
        fetching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (bmi > 0.00245) {
                    FetchData process = new FetchData();
                    process.execute();
                } else if (bmi < 0.00185) {
                    AnotherWords process = new AnotherWords();
                    process.execute();
                } else {
                    AnotherApi process = new AnotherApi();
                    process.execute();
                }
            }
        });

    }

    /**
     * oho.
     */
    class CalBmi implements View.OnClickListener {

        @Override
        public void onClick(final View v) {
            if (v.getId() == R.id.calButton) {
                String height = setHeight.getText().toString();
                String weight = setWeight.getText().toString();
                double h, w;
                if (!height.isEmpty() && !weight.isEmpty()) {
                    h = Double.parseDouble(height);
                    w = Double.parseDouble(weight);
                    bmi = w / (h * h);
                    if (bmi > 0.00245) {
                        result.setText(R.string.fat);
                    } else if (bmi < 0.00185) {
                        result.setText(R.string.thin);
                    } else {
                        result.setText(R.string.healthy);
                    }
                }
            } else {
                Toast.makeText(SecondActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
