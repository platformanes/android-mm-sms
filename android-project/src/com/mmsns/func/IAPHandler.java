package com.mmsns.func;


import android.os.Handler;
import android.os.Message;

import com.adobe.fre.FREContext;

public class IAPHandler extends Handler {

	public static final int INIT_FINISH = 10000;
	public static final int BILL_FINISH = 10001;
	public static final int QUERY_FINISH = 10002;
	public static final int UNSUB_FINISH = 10003;

	public IAPHandler(FREContext context) {
		MMSNSShared.context = (FREContext) context;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		int what = msg.what;
		switch (what) {
		case INIT_FINISH:
			MMSNSShared.event("INIT_FINISH", (String) msg.obj);
			break;
		default:
			MMSNSShared.event(String.valueOf(what), (String) msg.obj);
			break;
		}
	}

}
