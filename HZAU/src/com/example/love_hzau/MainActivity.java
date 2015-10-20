package com.example.love_hzau;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * 控制侧滑菜单
 * 进行FragMent的填充*/
public class MainActivity extends SlidingFragmentActivity implements OnClickListener{
	private ActionBar actionBar = null;
	private Home_Fragment homeFragment = null;
	private View home_layout;
	private More_Fragment moreFragment = null;
	private View more_layout;
	private Message_Fragment messageFragment = null;
	private View message_layout;
	private FragmentManager fragmentManager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getActionBar();
		setSlidingActionBarEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        //初始化主界面
        initView();
        fragmentManager = getFragmentManager();
        setTabSelection(1);
        //初始化滑动菜单
        initSlidingMenu(savedInstanceState);
	}
	private void initView(){
		home_layout = findViewById(R.id.main_layout);
		home_layout.setOnClickListener(this);
		more_layout = findViewById(R.id.more_layout);
		more_layout.setOnClickListener(this);
		message_layout = findViewById(R.id.message_layout);
		message_layout.setOnClickListener(this);
	}
	/**
	 * 初始化滑动菜单*/
	private void initSlidingMenu(Bundle savedInstanceState) {
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new SlidMenu_Fragment()).commit();	
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setBehindWidth(400);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		
	}
	/**
	 * 点击图标实现滑动菜单*/
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;		
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.message_layout:
			setTabSelection(0);
			break;
		case R.id.main_layout:
			setTabSelection(1);
			break;
		case R.id.more_layout:
			setTabSelection(2);
			break;

		default:
			break;
		}
	}
	private void setTabSelection(int index){
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragments(transaction);
		switch (index) {
		case 0:
			message_layout.setBackgroundResource(R.drawable.menu_selected);
			home_layout.setBackgroundResource(0);
			more_layout.setBackgroundResource(0);
			if(messageFragment == null){
				messageFragment = new Message_Fragment();
				transaction.add(R.id.content, messageFragment);
			}else{
				transaction.show(messageFragment);
			}
			break;
		case 1:
			home_layout.setBackgroundResource(R.drawable.menu_selected);
			more_layout.setBackgroundResource(0);
			message_layout.setBackgroundResource(0);
			if(homeFragment == null){
				homeFragment = new Home_Fragment();
				transaction.add(R.id.content, homeFragment);
			}else{
				transaction.show(homeFragment);
			}
			break;
		case 2:
			more_layout.setBackgroundResource(R.drawable.menu_selected);
			home_layout.setBackgroundResource(0);
			message_layout.setBackgroundResource(0);
			if(moreFragment == null){
				moreFragment = new More_Fragment();
				transaction.add(R.id.content, moreFragment);
			}else{
				transaction.show(moreFragment);
			}
			break;

		default:
			break;
		
	  }
		transaction.commit();
	}
	
	private void hideFragments(FragmentTransaction transaction){
		if(homeFragment != null){
			transaction.hide(homeFragment);
		}
		if(moreFragment != null){
			transaction.hide(moreFragment);
		}
		if(messageFragment != null){
			transaction.hide(messageFragment);
		}
	}
}
