package com.lowbrassrage.hotpotato;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.lowbrassrage.hotpotato.WiFiDirectBroadcastReceiver;

public class CreateGameActivity extends Activity {

	WifiP2pManager mManager;
	Channel mChannel;
	BroadcastReceiver mReceiver;
	IntentFilter mIntentFilter;
	private List peers = new ArrayList();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	    mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
	    mChannel = mManager.initialize(this, getMainLooper(), null);
	    mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);
	    
	    mIntentFilter = new IntentFilter();
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
	     
	    //Note! Activity_main is for TESTING ONLY. Change to "Create Game Page" For production
	    setContentView(R.layout.activity_main);
	     
	    setupWifiEnable();
	}
	//Moved onReceive() to WiFiDirectBroadcastReceiver where it belongs
		
	@Override
    protected void onResume() {
        super.onResume();
		registerReceiver(mReceiver, mIntentFilter);
    }
	
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
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
				//Toast toast = Toast.makeText(getApplicationContext(), "Hello Success!", Toast.LENGTH_SHORT);
				//toast.show();
				//mReceiver.onReceive(getApplicationContext(), getIntent());
				
				//Look for peers on click
				//onDiscover();
				mManager.discoverPeers(mChannel,  new WifiP2pManager.ActionListener() {
					
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						Toast toast = Toast.makeText(getApplicationContext(), "discoverPeers: onSuccess: Peers Discovered", Toast.LENGTH_SHORT);
						toast.show();
					}
					
					@Override
					public void onFailure(int reason) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
			}
		});
    }
	
	private void onDiscover() {
		ActionListener listener = new ActionListener(){
			 @Override
		        public void onSuccess() {
		            Toast.makeText(getApplicationContext(), "Discover peers successfully.", Toast.LENGTH_SHORT).show();
		        }

		        @Override
		        public void onFailure(int reason) {
		            Toast.makeText(getApplicationContext(), "Discover peers failed.", Toast.LENGTH_SHORT).show();
		        }
		};
		
	    mManager.discoverPeers(mChannel, listener); 
	}
	
	private PeerListListener peerListListener = new PeerListListener()
	{
		@Override
		public void onPeersAvailable(WifiP2pDeviceList peerList) {
			// TODO Auto-generated method stub
			// Out with the old, in with the new.
			
			peers.clear();
			peers.addAll(peerList.getDeviceList());
			
			// If an AdapterView is backed by this data, notify it
			// of the change.  For instance, if you have a ListView of available
			// peers, trigger an update.
			
			((WiFiPeerListAdapter) getListAdapter()).notifyDataSetChanged();
			
			if (peers.size() == 0) 
			{
				Log.d(WiFiDirectActivity.TAG, "No devices found");
				return;
			}
		}
		
	};
	
	/*
	private PeerListListener peerListListener = new PeerListListener()
	{
		@Override
		public void onPeersAvailable(WifiP2pDeviceList peerList) 
		{
		
			// Out with the old, in with the new.
			peers.clear();
			peers.addAll(peerList.getDeviceList());
			
			// If an AdapterView is backed by this data, notify it
			// of the change.  For instance, if you have a ListView of available
			// peers, trigger an update.
			((WiFiPeerListAdapter) getListAdapter()).notifyDataSetChanged();
			
			if (peers.size() == 0) 
			{
				Log.d(WiFiDirectActivity.TAG, "No devices found");
				return;
			}
		}
	}
	*/
	
	/*
	private class DeviceListDialog extends DialogFragment {

	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setTitle("Select a device")
	               .setSingleChoiceItems(mAdapter, 0, MainActivity.this)
	               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {
	                        dialog.dismiss();
	                    }

	               });

	        return builder.create();
	    }
	}
	*/
}
