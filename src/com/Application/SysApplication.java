package com.Application;

import java.util.LinkedList;
import java.util.List;










import com.Model.UserInfo;
import com.alljf.jf.CommonConstants;
import com.other.NetReceiver;
import com.pay.ali.Ali_Pay;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.app.Application;
import android.content.IntentFilter;

public class SysApplication extends Application {

	NetReceiver mReceiver = new NetReceiver();
    IntentFilter mFilter = new IntentFilter();
	private static UserInfo user= UserInfo.getInstance();

	private static SysApplication instance;

	private Activity tempActivitie = new Activity();
	
	private boolean  islogin = false;
	
	/**
	 * 用于存储或者记录所有打开过的activity
	 */
	private List<Activity> mList = new LinkedList<Activity>();
	
	public static Ali_Pay ali ;
@Override
public void onCreate() {
	// TODO Auto-generated method stub
	super.onCreate();
	IWXAPI api = WXAPIFactory.createWXAPI(this, CommonConstants.WXAPP_ID);
	api.registerApp(CommonConstants.WXAPP_ID);
	ali = new Ali_Pay(getApplicationContext());
}
	
	public boolean getIsLogin(){
		return islogin;
	}

	public void logOut(){
		islogin = false;
		user = null;
	}

	// 锟斤拷取实锟斤拷
	public synchronized static SysApplication getInstance() {
		if (instance == null)
			instance = new SysApplication();
		return instance;
	}

	// 锟斤拷锟斤拷锟揭拷锟斤拷锟缴撅拷锟斤拷页锟斤拷
	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	// 锟斤拷锟矫碉拷陆锟矫伙拷锟斤拷锟斤拷息
	public void addUserInfo(UserInfo model) {
		islogin = true;
		user = model;
	}

	// 锟斤拷取锟斤拷陆锟矫伙拷锟斤拷锟斤拷息
	public UserInfo getUserInfo() {
		return user;
	}

	// 用于退出应用程序
	public void exit() {
		for (Activity activity : mList)
			if (activity != null)
				activity.finish();
	}

	// 锟斤拷锟揭拷碌锟揭筹拷锟�
	public void addTempActivity(Activity activity) {
		tempActivitie = activity;
	}

	// 锟饺关憋拷要刷锟铰碉拷页锟斤拷
	public void refurbish() {
		tempActivitie.finish();
		tempActivitie = null;
	}

}
