package com.mmsns.func;

import java.util.HashMap;

import mm.sms.purchasesdk.OnSMSPurchaseListener;
import mm.sms.purchasesdk.PurchaseCode;
import mm.sms.purchasesdk.SMSPurchase;
import android.os.Message;
import android.util.Log;

import com.adobe.fre.FREContext;

public class IAPListener implements OnSMSPurchaseListener {
	private final String TAG = "IAPListener";
	private IAPHandler iapHandler;

	public IAPListener(FREContext context, IAPHandler iapHandler) {
		MMSNSShared.context = context;
		this.iapHandler = iapHandler;
	}

	@Override
	public void onInitFinish(int code) {
		MMSNSShared.event(TAG, "Init finish, status code = " + code);
		Message message = iapHandler.obtainMessage(IAPHandler.INIT_FINISH);
		String result = "初始化结果：" + SMSPurchase.getReason(code);
		message.obj = result;
		message.sendToTarget();
	}

	@Override
	public void onBillingFinish(int code, HashMap arg1) {
		MMSNSShared.event(TAG, "billing finish, status code = " + code);
		String result = "订购成功";
		Message message = iapHandler.obtainMessage(IAPHandler.BILL_FINISH);
		// 商品信息
		String paycode = null;
		// 商品的交易 ID，用户可以根据这个交易ID，查询商品是否已经交易
		String tradeID = null;
		if (code == PurchaseCode.ORDER_OK
				|| code == PurchaseCode.ORDER_OK_TIMEOUT) {
			/**
			 * 商品购买成功或者已经购买。 此时会返回商品的paycode，orderID,以及剩余时间(租赁类型商品)
			 */
			if (arg1 != null) {
				paycode = (String) arg1.get(OnSMSPurchaseListener.PAYCODE);
				if (paycode != null && paycode.trim().length() != 0) {
					result = result + ",Paycode:" + paycode;
				}
				tradeID = (String) arg1.get(OnSMSPurchaseListener.TRADEID);
				if (tradeID != null && tradeID.trim().length() != 0) {
					result = result + ",tradeid:" + tradeID;
				}
				MMSNSShared.event("ORDER_SUCCESS", paycode);
			}
		} else {
			/**
			 * 表示订购失败。
			 */
			result = "订购失败：" + SMSPurchase.getReason(code);
			MMSNSShared.event("ORDER_FAIL", SMSPurchase.getReason(code));
			Log.d(TAG, "ORDER_FAIL" + SMSPurchase.getReason(code));
		}
		MMSNSShared.event(TAG, result);

	}
}
