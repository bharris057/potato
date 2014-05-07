package com.lowbrassrage.hotpotato;

import android.app.Activity;
import android.app.Fragment;
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

import com.lowbrassrage.hotpotato.WiFiDirectBroadcastReceiver;

public class CreateGameActivity extends Activity {

	WifiP2pManager mManager;
	Channel mChannel;
	BroadcastReceiver mReceiver;
	IntentFilter mIntentFilter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	     mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
	     mChannel = mManager.initialize(this, getMainLooper(), null);
	     mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);
	        
	     IntentFilter mIntentFilter = new IntentFilter();
	     mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
	     mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
	     mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
	     mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
	     
	     
	     setupWifiEnable();
	     //Note! Activity_main is for TESTING ONLY. Change to "Create Game Page" For production
	     setContentView(R.layout.activity_main);
	}
	//Moved onReceive() to WiFiDirectBroadcastReceiver where it belongs
		
	@Override
    protected void onResume() {
        super.onResume();
		//registerReceiver(mReceiver, mIntentFilter);
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(mReceiver);
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_create_game,
					container, false);
			return rootView;
		}
	}

	private void setupWifiEnable()
    {
    	Button createButton = (Button) findViewById(R.id.CheckWifi);
    	createButton.setOnClickListener(new View.OnClickListener() 
    	{ 
			@Override
			public void onClick(View v) {
				//On Click goes here
				
				//mReceiver.onReceive(getApplicationContext(), getIntent());
				
			}
		});
    	
    }
}
