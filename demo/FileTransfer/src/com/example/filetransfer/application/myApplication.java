package com.example.filetransfer.application;


import java.util.HashMap;
import java.util.Map;

import com.example.filetransfer.net.NetHelper;

import android.app.Application;
import android.os.Handler;

public class myApplication extends Application {
	
    
	private NetHelper mNetHelper=null;
	private Map<String,Handler> handlers=new HashMap<String,Handler>();
    @Override
    public void onCreate()
    {
        super.onCreate();
    }
    
    public void setNetHelper(NetHelper netHelper)
    {
    	mNetHelper = netHelper;
    }
    public NetHelper getNetHelper()
    {
    	return mNetHelper;
    }
    public void putHandler(String activity_name,Handler mHandler)
    {
    	handlers.put(activity_name,mHandler);
    }
    public Handler getHandler(String activity_name)
    {
    	return handlers.get(activity_name);
    }
    public void removeHandler(String activity_name)
    {
    	handlers.remove(activity_name);
    }
}
