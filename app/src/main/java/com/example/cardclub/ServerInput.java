package com.example.cardclub;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerInput  extends Thread{

    private MainActivity ma = null;
    private Socket socket = null;
    private DataInputStream input = null;

    public ServerInput(MainActivity ma, Socket socket)
    {
        this.socket = socket;
        this.ma = ma;
        try {
            input = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        String line = "";
        while(!(line.equals("Done")))
        {
            try {
                line = input.readUTF();
                ma.setMessageView(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
