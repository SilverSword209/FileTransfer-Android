package com.example.filetransfer.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.filetransfer.adapter.ListViewAdapter;
import com.example.filetransfer.application.myApplication;
import com.example.filetransfer.data.MsgConst;
import com.example.filetransfer.data.User;
import com.example.filetransfer.net.NetHelper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	private Button searchbtn;
	private ListView userlist;
	private NetHelper mNetHelper;
	private myApplication mApplication;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViews();
		mApplication = (myApplication) getApplication();
		mNetHelper = new NetHelper(this);
		mApplication.setNetHelper(mNetHelper);
		Log.v("Handler", "add_mainAcitivity_handler");
		mApplication.putHandler("MainActivity",mainHandler);
		if(!isWifiActive()){	//若wifi没有打开，提示
        	Toast.makeText(this, R.string.wifi_fail, Toast.LENGTH_LONG).show();
        }
		searchbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intents = new Intent(MainActivity.this,SearchActivity.class);
				startActivity(intents);
			}
		});
	}
	private void refreshListView()
	{
		Map<String,User> usermap = mNetHelper.getUsers();
		List<User> lists = sortUsers(usermap);
		ListViewAdapter myAdapter = new ListViewAdapter(this,lists);
		userlist.setAdapter(myAdapter);
	}
	private List<User> sortUsers(Map<String, User> users) {
		// TODO Auto-generated method stub
		ArrayList<User> lists = new ArrayList<User>();
		//if(users == null) Log.v("USERLIST", "NULL");
		if(users!=null){
		Iterator it = users.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry<String, User> entry=(Entry<String, User>) it.next();   
			lists.add(entry.getValue());}
		Collections.sort(lists, new Comparator<User>(){       
			@Override      public int compare(User o1, User o2) {         
				if(o1.getDistance()<o1.getDistance()) return 1;
				return 0;
				}     
			});
		}
		return lists;
	}
	private void findViews() {
		// TODO Auto-generated method stub
		searchbtn = (Button)findViewById(R.id.btn_search);
		userlist=(ListView) findViewById(R.id.listView);
	}
	private boolean isWifiActive() {
		// TODO Auto-generated method stub
		ConnectivityManager mConnectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if(mConnectivity != null){
			NetworkInfo[] infos = mConnectivity.getAllNetworkInfo();
			
			if(infos != null){
				for(NetworkInfo ni: infos){
					if("WIFI".equals(ni.getTypeName()) && ni.isConnected())
						return true;
				}
			}
		}
		return false;
	}
	@Override  
    protected void onDestroy() {  
        super.onDestroy(); 
        Log.v("Handler", "remove_mainAcitivity_handler");
        mApplication.removeHandler("MainActivity"); 
    }  

	private Handler mainHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case MsgConst.STOPSEARCH:
				Log.v("STOPSEARCH", "hello");
				refreshListView();
				break;
			}
		}
	};
	
}
