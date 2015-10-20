package com.example.love_hzau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  ◊“≥GridView  ≈‰∆˜*/
public class Home_GridViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	 
    String[] text;
 
    int[] image;
    
    public Home_GridViewAdapter(Context context, String[] text, int[] image){
    	this.inflater = LayoutInflater.from(context);
    	this.image = image;
    	this.text = text;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return text.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(arg1 == null){
			holder = new ViewHolder();
			arg1 = this.inflater.inflate(R.layout.grid, null);
			holder.image = (ImageView) arg1.findViewById(R.id.imagview);
			holder.text = (TextView) arg1.findViewById(R.id.textview);
			arg1.setTag(holder);
		}else{
			holder = (ViewHolder) arg1.getTag();
		}
		holder.image.setImageResource(image[arg0]);
		holder.text.setText(text[arg0]);
		return arg1;
	}
	private class ViewHolder{
		 
        ImageView image;
 
        TextView text;
 
    }

}
