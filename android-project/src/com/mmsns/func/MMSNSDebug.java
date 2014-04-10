package com.mmsns.func;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class MMSNSDebug implements FREFunction {

	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		MMSNSShared.DEBUG = true;
		return null;
	}

}
