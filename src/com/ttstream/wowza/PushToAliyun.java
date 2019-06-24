package com.ttstream.wowza;

import com.wowza.wms.application.*;
import com.wowza.wms.module.*;
import com.wowza.wms.stream.*;
import com.wowza.wms.logging.WMSLogger;
import com.wowza.wms.logging.WMSLoggerFactory;

/*
 * the custom module used to addClientListener to a stream
 */
public class PushToAliyun extends ModuleBase {

	private WMSLogger logger = WMSLoggerFactory.getInstance().getLoggerObj(this.getClass().getName());
	
	private IMediaStreamActionNotify3 actionNotify = new StreamListener();	
	
	public void onAppStart(IApplicationInstance appInstance) {
		String fullname = appInstance.getApplication().getName() + "/" + appInstance.getName();
		logger.debug("PushToAliyun onAppStart: " + fullname);
	}

	public void onAppStop(IApplicationInstance appInstance) {
		String fullname = appInstance.getApplication().getName() + "/" + appInstance.getName();
		logger.debug("PushToAliyun onAppStop: " + fullname);
	}

	public void onStreamCreate(IMediaStream stream) {
		logger.debug("PushToAliyun onStreamCreate");
		stream.addClientListener(actionNotify);
	}

	public void onStreamDestroy(IMediaStream stream) {
		logger.debug("PushToAliyun onStreamDestroy");
	}
	

}
