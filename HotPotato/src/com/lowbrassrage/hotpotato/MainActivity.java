package com.lowbrassrage.hotpotato;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

import com.lowbrassrage.hotpotato.WiFiDirectBroadcastReceiver;

public class MainActivity extends ActionBarActivity {
	
	WifiP2pManager mManager;
	Channel mChannel;
	BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);
        
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        
        //Setting up the main page buttons
        setupCreateButton();
        setupSettingsButton();
        setupCreditsButton();
        setupJoinGameButton();

    }

	public void onReceive(Context context, Intent intent) {
    	
    	String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Wifi P2P is enabled
            	Toast toast = Toast.makeText(getApplicationContext(), "Wifi P2P enabled", Toast.LENGTH_LONG);
            	toast.show();
            } else {
                // Wi-Fi P2P is not enabled
            	Toast toast = Toast.makeText(getApplicationContext(), "Wifi P2P not enabled", Toast.LENGTH_LONG);
            	toast.show();
            }
        }
      
    }
    
    @Override
    protected void onResume() {
        super.onResume();
		//Context.registerReceiver(mReceiver, mIntentFilter);
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
    
    private void setupCreateButton()
    {
    	Button createButton = (Button) findViewById(R.id.CreateGameButton);
    	createButton.setOnClickListener(new View.OnClickListener() 
    	{
			
			@Override
			public void onClick(View v) {
				setContentView(R.layout.create_game_page);
			}
		});
    	
    }
    
    private void setupJoinGameButton() {
		// TODO Auto-generated method stub
    	Button joinGameButton = (Button) findViewById(R.id.JoinGameButton);
    	joinGameButton.setOnClickListener(new View.OnClickListener() 
    	{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//setContentView(R.layout.join_game_page);
			}
		});
		
	}

	private void setupCreditsButton() {
		// TODO Auto-generated method stub
		Button setupCreditsButton = (Button) findViewById(R.id.CreditsButton);
    	setupCreditsButton.setOnClickListener(new View.OnClickListener() 
    	{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//setContentView(R.layout.credits_page);
			}
		});
	}

	private void setupSettingsButton() {
		// TODO Auto-generated method stub
		Button setupSettingsButton = (Button) findViewById(R.id.SettingsButton);
    	setupSettingsButton.setOnClickListener(new View.OnClickListener() 
    	{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//setContentView(R.layout.settings_page);
			}
		});
	}
}
