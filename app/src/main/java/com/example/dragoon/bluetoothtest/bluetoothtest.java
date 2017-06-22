package com.example.dragoon.bluetoothtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        mBluetoothAdapter.startDiscovery();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);





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



                final BroadcastReceiver mReceiver = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        System.out.println("keresés");

                        String action = intent.getAction();
                        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                            List<String> mDeviceList = new ArrayList<String>();
                            mDeviceList.add(device.getName() + "\n" + device.getAddress());
                            ArrayAdapter <String> adapter=(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, mDeviceList));
                            lv.setAdapter(adapter);
                            System.out.println("enable" + adapter);


                        }else{
                            System.out.println("nincs");}
                    }
                };


            }


        });
       // String countryList[] = {"India", "China", "Australia", "Portugle", "America", "NewZealand"};

    }
}
