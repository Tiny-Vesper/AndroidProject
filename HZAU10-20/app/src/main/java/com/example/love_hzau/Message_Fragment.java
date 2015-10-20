package com.example.love_hzau;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 消息界面视图
 * 功能跳转*/
public class Message_Fragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View message = inflater.inflate(R.layout.message, container, false);
		
		return message;
	}
  
}
