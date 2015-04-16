package com.example.filetransfer.net;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.example.filetransfer.application.myApplication;
import com.example.filetransfer.data.MsgConst;
import com.example.filetransfer.data.User;
import com.example.filetransfer.data.UserConst;

public class NetHelper implements Runnable{
	
	private myApplication mApplication;
	private Map<String,User> users = new HashMap<String,User>();
	private Context context;
	public NetHelper(Context mcontext)
	{
		this.context = mcontext;
		mApplication = (myApplication)context.getApplicationContext();
		
	}
	public void startSearch(){
		User u = new User("10.10.10.10",UserConst.NOFILETRANSFER,0,100);
		users.put("10.10.10.10", u);
		u=new User("196.198.123.1",UserConst.SENDINGFILE,40,20);
		users.put("196.198.123.1",u);
		Handler handler = mApplication.getHandler("SearchActivity");
		handler.sendEmptyMessage(MsgConst.CHANGEUSERS);
	}
	public void StopSearch(){}
	public Map<String, User> getUsers() {
		// TODO Auto-generated method stub
		return users;
	}
	public int getUserCount() {
		// TODO Auto-generated method stub
		return users.size();
	}
	@Override
	public void run()
	{
		
	}
}
