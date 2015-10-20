package com.example.love_hzau;


import com.example.love_hzau.function.AboutUs;
import com.example.love_hzau.function.NightMode;
import com.example.love_hzau.function.Update;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 *≤‡ª¨≤Àµ• ”Õº */
public class SlidMenu_Fragment extends Fragment {
	private ListView list = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View slidmenu = inflater.inflate(R.layout.slidemenu, container, false);
		list = (ListView) slidmenu.findViewById(R.id.functionlist);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					Intent int1 = new Intent(getActivity(), Update.class);
					getActivity().startActivity(int1);
					break;
				case 1:
					Intent int2 = new Intent(getActivity(), AboutUs.class);
					getActivity().startActivity(int2);
					break;
				case 2:
					Intent int3 = new Intent(getActivity(), NightMode.class);
					getActivity().startActivity(int3);
					break;

				default:
					break;
				}
			}
			
		});
		return slidmenu;
	}

}
