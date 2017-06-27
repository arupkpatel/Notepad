package dtrix.notepad;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView1,textView2;
    private int noofruns=0;
    private static final String Keynfr = "noofruns";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.tv1);
        textView2 = (TextView) findViewById(R.id.tv2);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        int defaultValue =0;
        noofruns = sharedPreferences.getInt(Keynfr,defaultValue);

        if(noofruns==0){
            Toast.makeText(this, "Welcome to Bropad!!! ", Toast.LENGTH_SHORT).show();
        }

        textView2.setText(String.valueOf(noofruns));

        noofruns++;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Keynfr,noofruns).apply();
    }
}
