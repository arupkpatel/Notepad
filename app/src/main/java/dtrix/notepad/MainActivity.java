package dtrix.notepad;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView textView1,textView2;
    private int noofruns=0;
    private static final String Keynfr = "noofruns";
    private final String File = "SAve it!!";


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

    @Override
    protected void onPause() {
        super.onPause();
        SaveFile(textView1.getText().toString());

    }

    @Override
    protected void onResume() {
        super.onResume();
        textView1.setText(getFile());
    }



    //Save File
    private void SaveFile(String string){

        FileOutputStream outputStream =null;

        try{
            outputStream = openFileOutput(File,Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
        }catch (FileNotFoundException e){
            Log.e("Error","FileNotFound");
            e.printStackTrace();
        }catch (IOException e){
            Log.e("Error","IO problems");
            e.printStackTrace();
        }finally {
            try {
                if(outputStream != null)
                    outputStream.close();
            }catch (IOException e){
                Log.e("Error","IO problems");
                e.printStackTrace();
            }
        }
    }

    //read File
    private String getFile(){

        FileInputStream inputStream = null;
        String data=null;

        try {
            inputStream = openFileInput(File);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            data = new String(buffer,"UTF-8");
        }catch (FileNotFoundException e){
            Log.e("Error","FileNotFound");
            e.printStackTrace();
        }catch (IOException e){
            Log.e("Error","IO problems");
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null)
                    inputStream.close();
            }catch (IOException e){
                Log.e("Error","IO problems");
                e.printStackTrace();
            }
        }

        return data;
    }
}
