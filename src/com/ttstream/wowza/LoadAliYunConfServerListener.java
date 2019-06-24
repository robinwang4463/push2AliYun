package com.ttstream.wowza;

import com.wowza.wms.logging.*;
import com.wowza.wms.server.*;

/*
 * read the AliCDN configuration when server was started
 */
public class LoadAliYunConfServerListener implements IServerNotify2 {
	
	private WMSLogger logger = WMSLoggerFactory.getInstance().getLoggerObj(this.getClass().getName());

	public void onServerConfigLoaded(IServer server) {
		WMSLoggerFactory.getLogger(null).info("onServerConfigLoaded");
	}

	public void onServerCreate(IServer server) {
		
	}

	public void onServerInit(IServer server) {
		logger.debug("onServerInit");
		
		String pushDomain = server.getProperties().getPropertyStr("AliYunPushDomain");
		String rtmpPort = server.getProperties().getPropertyStr("AliYunRtmpPort");
		String appName = server.getProperties().getPropertyStr("AliYunAppName");
		String primaryKey = server.getProperties().getPropertyStr("AliYunPrimaryKey");
		
		logger.debug("read pushDomain from the property AliYunPushDomain : "+pushDomain);
		logger.debug("read rtmpPort from the property AliYunPushDomain : "+rtmpPort);
		logger.debug("read appName from the property AliYunPushDomain : "+appName);
		logger.debug("read primaryKey from the property AliYunPushDomain : "+primaryKey);
		
		if (  (pushDomain != null)  &&  (!pushDomain.equals(""))  ){
			AliYunConf.pushDomain = pushDomain;
		}
		
		if (  (rtmpPort != null)  &&  (!rtmpPort.equals(""))  ){
			AliYunConf.rtmpPort = rtmpPort;
		}
		
		if (  (appName != null)  &&  (!appName.equals(""))  ){
			AliYunConf.appName = appName;
		}
		
		if (  (primaryKey != null)  &&  (!primaryKey.equals(""))  ){
			AliYunConf.primaryKey = primaryKey;
		}
		
	}

	public void onServerShutdownStart(IServer server) {
		
	}

	public void onServerShutdownComplete(IServer server) {
		
	}

}
