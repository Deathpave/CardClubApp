package com.example.cardclub;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Socket socket = null;
    private String address = "10.108.137.77";
    private int port = 5004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connectBtnClicked(View view)
    {
        new Thread(() -> connect()).start();
    }

    public void connect() {
        try {
            if (!String.valueOf(((EditText) findViewById(R.id.IpText)).getText()).equals("Ip Address")) {
                address = String.valueOf(((EditText) findViewById(R.id.IpText)).getText());
            }
            if (Integer.valueOf(String.valueOf(((EditText) findViewById(R.id.PortText)).getText())) != port) {
                port = Integer.valueOf(String.valueOf(((EditText) findViewById(R.id.PortText)).getText()));
            }
            socket = new Socket(address, port);
            new Thread(new ServerInput(this, socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageClick(View view) {
        // add stuff to send
        String x = String.valueOf(((EditText) findViewById(R.id.editText)).getText());
        new Thread(new ServerOutput(socket, x)).start();
    }

    public void setMessageView(String msg)
    {
        (((TextView)findViewById(R.id.textView))).setText(msg);
    }
}