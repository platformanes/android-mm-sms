package com.mmsns.func;

import android.content.Context;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * bill
 * @author CZQ
 *
 */
public class MMSNSBill implements FREFunction {

	private String TAG = "MMSNSBill";
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		String payCode = null,description = null;
		int num = 0;
		FREObject result = null; 
		//get params
		try{
			payCode = arg1[0].getAsString();
			num = arg1[1].getAsInt();
			description = arg1[2].getAsString();
		}catch(Exception e){
			MMSNSShared.event(TAG, "参数错误：" + e.getMessage() + "|" + payCode + "|" + num + description);
			return null;
		}
		/**
		 * 商品购买接口。
		 */
		if(MMSNSShared.context != null){
			order(MMSNSShared.context.getActivity(), payCode, num, description);
		}else{
			MMSNSShared.event(TAG, "尚未初始化");
		}
		//--------------------------------
		MMSNSShared.event(TAG, "bill complete");
		return result;
	}
	public void order(Context context, String payCode, int num, String description) {
		try {
			MMSNSShared.event(TAG, "支付咯");
			MMSNSShared.purchase.smsOrder(context, payCode, MMSNSShared.mListener, description);
		} catch (Exception e) {
			MMSNSShared.event(TAG, "支付失败" + e.getMessage() + "," + e.toString());
		}
	}

}
