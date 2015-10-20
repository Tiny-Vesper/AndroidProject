package com.example.love_hzau;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;


public class StartAnimation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub  
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		final View view = View.inflate(this, R.layout.start_animation, null);
		setContentView(view);
		AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);        
		aa.setDuration(3000);        
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				RedirectTo();
			}
		});
		
	}
	private void RedirectTo(){               
	   	 Intent intent = new Intent(this, MainActivity.class);        
	   	 startActivity(intent); 
	   	 overridePendingTransition(R.anim.fade, R.anim.hold); 
	   	 finish();   
	   	 }
}
