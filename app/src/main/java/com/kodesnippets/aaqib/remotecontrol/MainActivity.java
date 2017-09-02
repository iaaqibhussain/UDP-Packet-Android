package com.kodesnippets.aaqib.remotecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button sendBtn, enableBtn;
    EditText hostAddress, port, message, delay;
    UDPModel udpModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        udpModel = new UDPModel();
        setupComponentsAndListeners();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        udpModel.close();
    }

    public void setupComponentsAndListeners(){
        hostAddress = (EditText) findViewById(R.id.host_edit_text);
        port = (EditText) findViewById(R.id.port_edit_text);
        message = (EditText) findViewById(R.id.message_edit_text);
        delay = (EditText) findViewById(R.id.delay_edit_text);
        sendBtn = (Button) findViewById(R.id.send_button);
        enableBtn = (Button) findViewById(R.id.enable_button);
        sendOnClickListener();
        enableOnClickListener();
    }

    public void sendOnClickListener(){
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Util.isEditTextEmpty(hostAddress) || Util.isEditTextEmpty(port)){
                 Util.showToast(getApplicationContext(),"Host or Port cannot be empty");
                }else {
                    if (!Util.isEditTextEmpty(message)) {
                        udpModel.message = message.getText().toString();
                        udpModel.hostAddress = hostAddress.getText().toString();
                        udpModel.delay = delay.getText().toString();
                        udpModel.port = Integer.valueOf(port.getText().toString());
                        new Thread(udpModel).start();
                    }else{
                        Util.showToast(getApplicationContext(), "Message cannot be empty");
                    }
                }
            }
        });

    }

    public void enableOnClickListener(){
        enableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Util.isEditTextEmpty(hostAddress) || Util.isEditTextEmpty(port)){
                    Util.showToast(getApplicationContext(),"Host or Port cannot be empty");
                }else {
                    if (!Util.isEditTextEmpty(message)) {

                        udpModel.enableBroadcast();
                    }else{
                        Util.showToast(getApplicationContext(), "Message cannot be empty");
                    }
                }
            }
        });

    }
}
