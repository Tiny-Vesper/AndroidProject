package com.example.love_hzau;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import com.example.love_hzau.itterface.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更多 界面视图
 * ListView适配
 * 搜索框结合
 * 应用点击跳转*/
public class More_Fragment extends Fragment implements SearchView.OnQueryTextListener,OnRefreshListener{
	String[] data = {"1","2","3","4","5","6","7","8","9","10"}; 
	int[] imagIds ={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher
			,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher
			,R.drawable.ic_launcher};
	private SearchView searchView = null;
	private MyListView listView = null;
	private SimpleAdapter adapter = null;
	private List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View more = inflater.inflate(R.layout.more, container,false);
		listView = (MyListView) more.findViewById(R.id.applicationlist);
		for(int i=0;i<data.length;i++){
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("imag", String.valueOf(imagIds[i]));
        	map.put("text", data[i]);
        	this.list.add(map);
        }
		adapter = new SimpleAdapter(this.getActivity(), list, R.layout.more_listadapter, new String[]{"imag","text"}, 
				new int[]{R.id.appimag,R.id.appdescription});
		listView.setAdapter(adapter);
		listView.setTextFilterEnabled(true);
		listView.setOnRefreshListener(this);
		
		searchView = (SearchView) more.findViewById(R.id.search_view);
		searchView.setOnQueryTextListener(this);
		
		return more;
	}

	@Override
	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		if(TextUtils.isEmpty(arg0)){
			listView.clearTextFilter();
		}else{
			listView.setFilterText(arg0.toString());
		}
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDownPullRefresh() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
				SystemClock.sleep(1500);
				return null;
			}
			@Override
			protected void onPostExecute(Void result) {
				adapter.notifyDataSetChanged();
				listView.hideHeaderView();
			}
		}.execute(new Void[] {});
	}

	@Override
	public void onLoadingMore() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				SystemClock.sleep(3000);
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				adapter.notifyDataSetChanged();

				listView.hideFooterView();
			}
		}.execute(new Void[] {});
	}

}
