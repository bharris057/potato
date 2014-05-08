package com.lowbrassrage.hotpotato;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {
	
	private WifiP2pManager mManager;
    private Channel mChannel;
    private Activity mActivity;
    PeerListListener myPeerListListener;
    
    // public ArrayAdapter mAdapter;
    // private ArrayList<WifiP2pDevice> mDeviceList = new ArrayList<WifiP2pDevice>();
    // int flag=0;
    
    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, Channel channel, Activity activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;
    }
    
    //private void showDeviceListDialog() {
    //    DeviceListDialog deviceListDialog = new DeviceListDialog();
    //    deviceListDialog.show(getFragmentManager(), "devices");
    //}
    
    
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction();
		
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) 
        {
            // Check to see if Wi-Fi is enabled and notify appropriate activity
        	int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
        	
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) 
            {
            	// Wifi P2P is enabled
                String title = "State Change";
                Toast.makeText(mActivity, "Wi-Fi Direct is enabled. " + title, Toast.LENGTH_SHORT).show();
            }
            else 
            {
            	// Wifi P2P is not enabled
                Toast.makeText(mActivity, "Wi-Fi Direct is disabled.", Toast.LENGTH_SHORT).show();
            }
        } 
        else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) 
        {
            // Call WifiP2pManager.requestPeers() to get a list of current peers
        	String title = "WifiDirectBroadcastReceiver: onReceive: Peers Discovered.";
            Toast.makeText(mActivity, "Wi-Fi Direct is enabled. " + title, Toast.LENGTH_SHORT).show();
            
            // request available peers from the wifi p2p manager. This is an
            // asynchronous call and the calling activity is notified with a
            // callback on PeerListListener.onPeersAvailable()
            if (mManager != null) {
            	mManager.requestPeers(mChannel, myPeerListListener);
            }
            Log.d(WiFiDirectActivity.TAG, "P2P peers changed");
            
        	/*
        	if (mManager != null) 
        	{
        
                mManager.requestPeers(mChannel, new PeerListListener() 
                {

                    public void onPeersAvailable(WifiP2pDeviceList peers) 
                    {
                        if (peers != null) 
                        {
                            mDeviceList.addAll(peers.getDeviceList());
                            ArrayList<String> deviceNames = new ArrayList<String>();
                            for (WifiP2pDevice device : mDeviceList) 
                            {
                                deviceNames.add(device.deviceName);
                            }
                            if (deviceNames.size() > 0) 
                            {
                                mAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, deviceNames);
                                if(flag==0)
                                {
                                    flag=1;
                                    showDeviceListDialog();
                                }
                            } 
                            else 
                            {
                                Toast.makeText(mActivity, "Device list is empty.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        	}
        	*/
        } 
        else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) 
        {
            // Respond to new connection or disconnections
        } 
        else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) 
        {
            // Respond to this device's wifi state changing
        }

        //Toast toast = Toast.makeText(context, "Hello More Success!", Toast.LENGTH_SHORT).show();
	}

}
