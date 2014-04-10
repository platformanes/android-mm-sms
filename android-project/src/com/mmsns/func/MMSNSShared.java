package com.mmsns.func;

import mm.sms.purchasesdk.SMSPurchase;

import android.util.Log;
import android.widget.Toast;

import com.adobe.fre.FREContext;

/**
 * 
 * @author CZQ
 *
 */
public class MMSNSShared {
	public static Boolean DEBUG = false;
	
	public static  String APPID = "0000000000";
	public static  String APPKEY = "0000000000";
	
	public static SMSPurchase purchase;
	public  static IAPListener mListener;
	
	public static FREContext context;
	
	public static void event(String code,String level  ){
		Log.d(code, "---------抛出event-------" + ":"+level );
		context.dispatchStatusEventAsync(code, level );
		if (DEBUG){
			Toast.makeText(context.getActivity(), code + ":" + level, Toast.LENGTH_LONG).show();
		}
	}
}
