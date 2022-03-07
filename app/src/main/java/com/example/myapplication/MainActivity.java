package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Button btnEx2;
    ConnectionThread ct;
    TextView inputText;
    TextView outputText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button2);
        btnEx2 = (Button) findViewById(R.id.button);
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

        //2.2 5
        btnEx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char[] input = inputText.getText().toString().toCharArray();
                ArrayList <Integer> temp = new ArrayList<Integer>();

                //loops through input and only adds non prime numers
                for(int i = 0; i<input.length; i++){
                    int currentNumber = Integer.parseInt(input[i]+"");
                    if( currentNumber != 2 &&  currentNumber != 3 && currentNumber != 5 && currentNumber != 7){
                        temp.add(Integer.parseInt(input[i]+""));
                    }
                }

                //Sorting
                Object[] finalData = temp.toArray();
                Arrays.sort(finalData);

                //Preparing output
                String output = "";
                for(int i = 0; i< finalData.length; i++){
                    output+=finalData[i];
                }

                outputText.setText(output);


            }
        });





    }



}