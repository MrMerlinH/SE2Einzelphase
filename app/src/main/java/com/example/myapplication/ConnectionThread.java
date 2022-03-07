package com.example.myapplication;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionThread extends Thread{
    private int Mtkr;
    private String returnData;

    //data for Socket
    Socket clientSocket;

    public ConnectionThread(int Matrikelnummer){
        this.Mtkr = Matrikelnummer;
    }
    @Override
    public void run() {
        try {
            //socket code is from the Tutorium pdf

            //create client socket, connect to server
            clientSocket = new Socket("se2-isys.aau.at", 53212);

            //create output stream attached to socket
            DataOutputStream ouToServer = new DataOutputStream(clientSocket.getOutputStream());

            //Create input stream attached to socket
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //send line to server
            ouToServer.writeBytes(""+Mtkr+"\n");

            //read line from server
            returnData = inFromServer.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            returnData = "ERROR";
        }
    }

    public String getData(){
        return returnData;
    }
}
