package com.example.filetransfer.data;

public class User {
	private String Ip;
	private int fileState;
	private int processRate;
	private int Distance;
	
	public User(String ip,int state,int rate,int distance)
	{
		this.Ip=ip;
		this.fileState = state;
		this.processRate = rate;
		this.Distance = distance;
	}
	public void setIp(String ip)
	{
		this.Ip = ip;
	}
	public String getIp()
	{
		return Ip;
	}
	public void setfileState(int state)
	{
		this.fileState = state; 
	}
	public int getFileState()
	{
		return fileState;
	}
	public void setProcessRate(int rate)
	{
		this.processRate = rate;
	}
	public int getProcessRate()
	{
		return processRate;
	}
	public void setDistance(int distance)
	{
		this.Distance = distance;
	}
	public int getDistance()
	{
		return Distance;
	}
}
