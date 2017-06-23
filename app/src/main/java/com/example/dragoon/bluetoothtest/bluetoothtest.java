package com.example.dragoon.bluetoothtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class bluetoothtest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final ListView lv;
        Button on, find;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetoothtest);


        lv = (ListView) findViewById(R.id.listView);
        on = (Button) findViewById(R.id.button);
        find = (Button) findViewById(R.id.button2);
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        ThreadConnectBTdevice myThreadConnectBTdevice;
        ThreadConnected myThreadConnected;

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        BroadcastReceiver mReceiver = new BroadcastReceiver() {





            public void onReceive(Context context, Intent intent) {


                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    List<String> mDeviceList = new ArrayList<String>();
                    mDeviceList.add(device.getName() + "\n" + device.getAddress());
                    ArrayAdapter <String> adapter=(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, mDeviceList));
                    lv.setAdapter(adapter);
                    System.out.println(adapter);



                }
            }

        };
        registerReceiver(mReceiver,filter);








        on.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn, 0);
                }
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            /*    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

                List<String> s = new ArrayList<String>();
                for(BluetoothDevice bt : pairedDevices) {
                    s.add(bt.getName());

                    System.out.println("connected: " + s);
                                    }*/

                mBluetoothAdapter.startDiscovery();



            }


        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView)view).getText().toString();
                myThreadConnectBTdevice = new ThreadConnectBTdevice(device);
                myThreadConnectBTdevice.start();

                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
            }
        });







    }
}
