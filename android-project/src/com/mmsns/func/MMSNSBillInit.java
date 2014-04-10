package com.mmsns.func;

import mm.sms.purchasesdk.SMSPurchase;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * init
 * @author CZQ
 *
 */
public class MMSNSBillInit implements FREFunction {

	private String TAG = "MMBillInit";
	
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		MMSNSShared.context = context;
		FREObject result = null; 
		try
		{
			MMSNSShared.APPID = arg1[0].getAsString();
			MMSNSShared.APPKEY = arg1[1].getAsString();
		}
		catch(Exception e)
		{
			return null;
		}
		MMSNSShared.event(TAG, "init started");
		IAPHandler iapHandler = new IAPHandler(MMSNSShared.context);
		/**
		 * IAP组件初始化.包括下面3步。
		 */
		/**
		 * step1.实例化PurchaseListener。实例化传入的参数与您实现PurchaseListener接口的对象有关。
		 * 例如，此Demo代码中使用IAPListener继承PurchaseListener，其构造函数需要Context实例。
		 */
		MMSNSShared.mListener = new IAPListener(MMSNSShared.context, iapHandler);
		/**
		 * step2.获取Purchase实例。
		 */
		MMSNSShared.purchase = SMSPurchase.getInstance();
		/**
		 * step3.向Purhase传入应用信息。APPID，APPKEY。 需要传入参数APPID，APPKEY。 APPID，见开发者文档
		 * APPKEY，见开发者文档
		 */
		try {
			MMSNSShared.purchase.setAppInfo(MMSNSShared.APPID, MMSNSShared.APPKEY);
		} catch (Exception e1) {
			MMSNSShared.event(TAG, "setAppInfo:"+e1.getMessage());
			return null;
		}
		/**
		 * step4. IAP组件初始化开始， 参数PurchaseListener，初始化函数需传入step1时实例化的
		 * PurchaseListener。
		 */
		try {
			MMSNSShared.purchase.smsInit(context.getActivity(), MMSNSShared.mListener); 
		} catch (Exception e) {
			MMSNSShared.event(TAG, "smsInit fail:"+e.getMessage());
			return null;
		}
		MMSNSShared.event(TAG, "init complete");
		//--------------------------------

		return result;
	}
}
