package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.editText);
        et2=findViewById(R.id.editText2);
        Button b1=findViewById(R.id.button);
        Button b2=findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et1.getText().toString();
                String no=et2.getText().toString();
                try (FileOutputStream fos = openFileOutput("example.txt", MODE_PRIVATE)) {
                    fos.write(name.getBytes());
                    fos.write('\n');
                    fos.write(no.getBytes());
                    et1.getText().clear();
                    et2.getText().clear();
                    Toast.makeText(getApplicationContext(),"saved a record at "+ getFilesDir()+"/example.txt",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis=openFileInput("example.txt");
                    InputStreamReader isr=new InputStreamReader(fis);
                    BufferedReader br=new BufferedReader(isr);
                    StringBuilder sb=new StringBuilder();
                    String txt;
                    while((txt=br.readLine())!=null)
                    {
                        sb.append(txt).append("\n");
                    }
                    et1.setText(sb.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

}
