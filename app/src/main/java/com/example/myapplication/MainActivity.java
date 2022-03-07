package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ConnectionThread ct;
    TextView inputText;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button2);
        outputText = (TextView) findViewById(R.id.serverAnswerOutput);
        inputText = (TextView) findViewById(R.id.editTextNumber);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inputText.getText().toString();

                if(input.length() != 0){

                //crates a thread
                ct = new ConnectionThread(Integer.parseInt(input));

                //starts the thread
                ct.start();

                try {
                    //waits until the thread is finished
                    ct.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                outputText.setText(ct.getData());

                }
            }
        });
    }



}