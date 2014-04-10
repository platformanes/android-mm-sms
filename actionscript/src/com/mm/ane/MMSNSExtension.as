package com.mm.ane 
{ 
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * 
	 * @author CZQ
	 * 
	 */	
	public class MMSNSExtension extends EventDispatcher 
	{ 
		private static const MM_FUNCTION_INIT:String = "mmsns_function_init";//与java端中Map里的key一致
		private static const MM_FUNCTION_BILL:String = "mmsns_function_bill";//与java端中Map里的key一致
		private static const MM_FUNCTION_DEBUG:String = "mmsns_function_debug";//与java端中Map里的key一致
		
		private static const EXTENSION_ID:String = "com.mmsns.ane";//与extension.xml中的id标签一致
		private var extContext:ExtensionContext;
		
		
		private static var _instance:MMSNSExtension; 
		public function MMSNSExtension()
		{
			if(extContext == null) {
				extContext = ExtensionContext.createExtensionContext(EXTENSION_ID, "");
				extContext.addEventListener(StatusEvent.STATUS, statusHandler);
			}
		} 
		
		/**
		 * 获取实例
		 * @return DLExtension 单例
		 */
		public static function getInstance():MMSNSExtension
		{
			if(_instance == null) 
				_instance = new MMSNSExtension();
			return _instance;
		}
		
		/**
		 * 转抛事件
		 * @param event 事件
		 */
		private function statusHandler(event:StatusEvent):void
		{
			dispatchEvent(event);
		}
		
		/**
		 * 初始化
		 * @param APPID
		 * @param APPKEY
		 * @return 
		 * 
		 */			
		public function MMSNSInit(APPID:String,APPKEY:String):void{
			if(extContext ){
				extContext.call(MM_FUNCTION_INIT, APPID, APPKEY);
			}
		} 
		
		/**
		 * 商品购买接口。
		 * @param payCode
		 * @param num
		 * @param description
		 * @return 
		 * 
		 */		
		public function MMSNSBilling(payCode:String, num:uint, description:String = ''):void{
			if(extContext ){
				extContext.call(MM_FUNCTION_BILL, payCode, num, description);
			}
		} 
		/**
		 *  Debug开启
		 * 
		 */		
		public function MMSNSDebug():void{
			if(extContext ){
				extContext.call(MM_FUNCTION_DEBUG);
			}
		} 
	} 
}