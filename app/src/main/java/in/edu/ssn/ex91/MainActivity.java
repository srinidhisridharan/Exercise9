package in.edu.ssn.ex91;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        editText = (EditText)findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void write(View view) {
        File[] fo = getExternalFilesDirs(null);
        File f =fo[1];
        try {

            File file = new File(f,"MyFile4.txt");
           if(!file.exists()){
            file.createNewFile();
            }
            //FileOutputStream fout = new FileOutputStream(file);
           BufferedWriter output;
           output = new BufferedWriter(new FileWriter(file,true));
            //OutputStreamWriter mout = new OutputStreamWriter(fout);
            output.append(editText.getText().toString());
            output.newLine();
            output.close();
            //fout.close();
            Toast.makeText(getBaseContext(),"Data written to SD card",Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    public void read(View view) {
        File[] fo = getExternalFilesDirs(null);
        File f =fo[1];
        try {

            File file = new File(f,"MyFile4.txt");
            FileInputStream fin =new FileInputStream(file);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fin));
            String drow="";
            String dbuf="";
            while ((drow=bf.readLine())!=null){
                dbuf+=drow+"\n";
            }
            editText.setText(dbuf);
            bf.close();
            fin.close();

        }
        catch (Exception e){
            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    public void clear(View view) {
        editText.setText("");
    }
}