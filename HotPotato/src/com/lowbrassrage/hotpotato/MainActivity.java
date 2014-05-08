package com.lowbrassrage.hotpotato;

import android.support.v7.app.ActionBarActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.lowbrassrage.hotpotato.WiFiDirectBroadcastReceiver;

public class MainActivity extends ActionBarActivity {
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
       
        
        //Setting up the main page buttons
        setupCreateButton();
        setupSettingsButton();
        setupCreditsButton();
        setupJoinGameButton();

    }

    //main page button
    private void setupCreateButton()
    {
    	Button createButton = (Button) findViewById(R.id.CreateGameButton);
    	createButton.setOnClickListener(new View.OnClickListener() 
    	{
			// TODO change all buttons from changing ContentView to actually making new activities
    		// This is actually how Android is supposed to handle multiple pages. 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CreateGameActivity.class);
				startActivity(intent);
			}
		});
    	
    }
    
    //main page button
    private void setupJoinGameButton() {
    	Button joinGameButton = (Button) findViewById(R.id.JoinGameButton);
    	joinGameButton.setOnClickListener(new View.OnClickListener() 
    	{
    		// TODO change all buttons from changing ContentView to actually making new activities
    		// This is actually how Android is supposed to handle multiple pages. 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, JoinGameActivity.class);
				startActivity(intent);

			}
		});
	}
    
    //join game page button
    private void setupJoinSelectedButton()
    {
    	Button setupJoinSelectedButton = (Button) findViewById(R.id.joinSelectedGameButton);
    	setupJoinSelectedButton.setOnClickListener(new View.OnClickListener()
    	{
    		// TODO change all buttons from changing ContentView to actually making new activities
    		// This is actually how Android is supposed to handle multiple pages. 
    		@Override
    		public void onClick(View v)
    		{
    			//put stuff here
    		}
    	});
    }
    
    //main page button
	private void setupCreditsButton() {
		Button setupCreditsButton = (Button) findViewById(R.id.CreditsButton);
    	setupCreditsButton.setOnClickListener(new View.OnClickListener() 
    	{
    		// TODO change all buttons from changing ContentView to actually making new activities
    		// This is actually how Android is supposed to handle multiple pages. 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ShowCreditsPageActivity.class);
				startActivity(intent);
			}
		});
	}
	
	//main page button
	private void setupSettingsButton() {
		Button setupSettingsButton = (Button) findViewById(R.id.SettingsButton);
    	setupSettingsButton.setOnClickListener(new View.OnClickListener() 
    	{
    		// TODO change all buttons from changing ContentView to actually making new activities
    		// This is actually how Android is supposed to handle multiple pages. 
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, MainPageSettingsActivity.class);
				startActivity(intent);
			}
		});
	}
    	
    //easy button setup
    private void setupEasyButton() {
    	Button setupEasyButton = (Button) findViewById(R.id.EasyButton);
    	setupEasyButton.setOnClickListener(new View.OnClickListener()
    	{
    		// TODO change all buttons from changing ContentView to actually making new activities
    		// This is actually how Android is supposed to handle multiple pages. 
    		@Override
    		public void onClick(View v){
    			//setContentView(R.layout.game_page);
    		}
    	});
	}
}
