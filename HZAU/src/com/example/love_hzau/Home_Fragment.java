package com.example.love_hzau;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.example.love_hzau.function.Announcement;
import com.example.love_hzau.function.CampusCulture;
import com.example.love_hzau.function.CampusMessage;
import com.example.love_hzau.function.DepartmentPhone;
import com.example.love_hzau.function.DownloadPart;
import com.example.love_hzau.function.EducationMessage;
import com.example.love_hzau.function.Examination;
import com.example.love_hzau.function.HzauHistory;
import com.example.love_hzau.function.LionPlatfrom;
import com.example.love_hzau.function.Postgraduate;
import com.example.love_hzau.function.QueryMore;
import com.example.love_hzau.function.SchoolCalendar;
import com.example.love_hzau.function.TextInformation;
import com.example.love_hzau.function.UndergraduateCourse;

/**
 * 首页界面视图
 * 图标点击后的跳转*/
public class Home_Fragment extends Fragment {
	private int grid_imag[]={R.drawable.jwkx,R.drawable.tzgg,R.drawable.ksxx,
		R.drawable.bmdh,R.drawable.xzzq,R.drawable.xl,R.drawable.bkzs,
		R.drawable.yjzs,R.drawable.gkpd,R.drawable.ckgd};
	private String[] grid_text = {"教务快讯","通知公告","考试信息","部门电话","下载专区","校历","本科招生","研究生招生","高考频道","查看更多"};
	private GridView gridViewHome = null;
	private Home_GridViewAdapter adapter = null;
	private Button campusCulture, campusMessage, hzauHistory, lionPlatfrom;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View home = inflater.inflate(R.layout.home, container,false);
		
		gridViewHome = (GridView) home.findViewById(R.id.gridviewhome);
		gridViewHome.setNumColumns(4);
		gridViewHome.setHorizontalSpacing(3);
		gridViewHome.setVerticalSpacing(50);
		gridViewHome.setPadding(15, 15, 15, 15);
		gridViewHome.setGravity(Gravity.CENTER);
		gridViewHome.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		adapter = new Home_GridViewAdapter(this.getActivity(), grid_text,grid_imag);
		gridViewHome.setAdapter(adapter);
		gridViewHome.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					Intent intent0 = new Intent(getActivity(), EducationMessage.class);//教务快讯
					getActivity().startActivity(intent0);
					break;
				case 1:
					Intent intent1 = new Intent(getActivity(), Announcement.class);//通知公告
					getActivity().startActivity(intent1);
					break;
				case 2:
					Intent intent2 = new Intent(getActivity(), TextInformation.class);//考试信息
					getActivity().startActivity(intent2);
					break;
				case 3:
					Intent intent3 = new Intent(getActivity(), DepartmentPhone.class);//部门电话
					getActivity().startActivity(intent3);
					break;
				case 4:
					Intent intent4 = new Intent(getActivity(), DownloadPart.class);//下载专区
					getActivity().startActivity(intent4);
					break;
				case 5:
					Intent intent5 = new Intent(getActivity(), SchoolCalendar.class);//校历
					getActivity().startActivity(intent5);
					break;
				case 6:
					Intent intent6 = new Intent(getActivity(), UndergraduateCourse.class);//本科招生
					getActivity().startActivity(intent6);
					break;
				case 7:
					Intent intent7 = new Intent(getActivity(), Postgraduate.class);//研究生招生
					getActivity().startActivity(intent7);
					break;
				case 8:
					Intent intent8 = new Intent(getActivity(), Examination.class);//高考频道
					getActivity().startActivity(intent8);
					break;
				case 9:
					Intent intent9 = new Intent(getActivity(), QueryMore.class);//查询更多
					getActivity().startActivity(intent9);
					break;
					
				default:
					break;
				}
			}
			
		});
		
		campusCulture = (Button) home.findViewById(R.id.campusculture);  //校园文化
		campusCulture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it1 = new Intent(getActivity(), CampusCulture.class);
				getActivity().startActivity(it1);
			}
		});
		campusMessage = (Button) home.findViewById(R.id.campusmessage); //校园快讯
		campusMessage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it2 = new Intent(getActivity(), CampusMessage.class);
				getActivity().startActivity(it2);
			}
		});
		hzauHistory = (Button) home.findViewById(R.id.hzauhistory);//华农历史
		hzauHistory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it3 = new Intent(getActivity(), HzauHistory.class);
				getActivity().startActivity(it3);
			}
		});
		lionPlatfrom = (Button) home.findViewById(R.id.lionplatfrom);//狮子山讲坛
		lionPlatfrom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it4 = new Intent(getActivity(), LionPlatfrom.class);
				getActivity().startActivity(it4);
			}
		});
		return home;
		
	}
	
	

}
