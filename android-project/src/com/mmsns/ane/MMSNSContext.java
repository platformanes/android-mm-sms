package com.mmsns.ane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.mmsns.func.MMSNSBill;
import com.mmsns.func.MMSNSBillInit;
import com.mmsns.func.MMSNSDebug;

public class MMSNSContext extends FREContext {
	/**
	 * 初始化
	 */
	public static final String MMSNS_FUNCTION_INIT = "mmsns_function_init";
	/**
	 * 订购
	 */
	public static final String MMSNS_FUNCTION_BILL = "mmsns_function_bill";
	/**
	 * DEBUG
	 */
	public static final String MMSNS_FUNCTION_DEBUG = "mmsns_function_debug";
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		// TODO Auto-generated method stub
		Map<String, FREFunction> map = new HashMap<String, FREFunction>();
		//映射
		map.put(MMSNS_FUNCTION_INIT, new MMSNSBillInit());
		map.put(MMSNS_FUNCTION_BILL, new MMSNSBill());
		map.put(MMSNS_FUNCTION_DEBUG, new MMSNSDebug());
		return map;
	}

}
