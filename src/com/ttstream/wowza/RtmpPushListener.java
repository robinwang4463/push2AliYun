package com.ttstream.wowza;

import com.wowza.wms.pushpublish.protocol.rtmp.PushPublishRTMPNotifyBase;
import com.wowza.wms.amf.AMFDataList;
import com.wowza.wms.logging.WMSLogger;
import com.wowza.wms.logging.WMSLoggerFactory;
import com.wowza.wms.pushpublish.protocol.rtmp.PushPublishRTMPNetConnectionSession;

/*
 * we use this Listener to monitor the status of pushPublishRtmp
 */
public class RtmpPushListener extends PushPublishRTMPNotifyBase{
	
	private WMSLogger logger = WMSLoggerFactory.getInstance().getLoggerObj(this.getClass().getName());
	
	private String srcAppName = "";
	private String srcStreamName = "";
	private String destHost = "";
	private String destPort = "";
	private String destAppName = "";
	private String destStreamName = "";
	private String context = "";
	
	public RtmpPushListener(String srcAppName,String srcStreamName,String destHost,String destPort,String destAppName, String destStreamName ){
		this.srcAppName = srcAppName;
		this.srcStreamName = srcStreamName;
		this.destHost = destHost;
		this.destPort = destPort;
		this.destAppName = destAppName;
		this.destStreamName = destStreamName;
		this.context = "["+this.srcAppName+"/"+this.srcStreamName+"->rtmp://"+ this.destHost+":"+this.destPort+"/"+this.destAppName+"/"+this.destStreamName+"]";
		
	}
	
	public void onConnectStart(PushPublishRTMPNetConnectionSession pushPublisherSession)
	{
		logger.debug("rtmpPushListener : onConnectStart " + pushPublisherSession.toString());
	}

	public void onConnectSuccess(PushPublishRTMPNetConnectionSession pushPublisherSession)
	{
	
		logger.debug("rtmpPushListener : onConnectSuccess , context = " + this.context );
		
		//String streamName = pushPublisherSession.getPublishStream().getName();
		//String sessionId = pushPublisherSession.getSession();
	}
	
	public void onConnectFailure(PushPublishRTMPNetConnectionSession pushPublisherSession)
	{
		logger.debug("rtmpPushListener : onConnectFailure , context = " + this.context );
	}
	
	public void onPushPublisherSessionCreate(PushPublishRTMPNetConnectionSession pushPublisherSession)
	{
		logger.debug("rtmpPushListener : onPushPublisherSessionCreate " + pushPublisherSession.toString());
	}
	
	public void onSessionOpened(PushPublishRTMPNetConnectionSession pushPublisherSession)
	{
		logger.debug("rtmpPushListener : onSessionOpened " + pushPublisherSession.toString());
	}
	public void onSessionClosed(PushPublishRTMPNetConnectionSession pushPublisherSession)
	{
		logger.debug("rtmpPushListener : onSessionClosed " + pushPublisherSession.toString());
	}
	public void onPushPublisherSessionDestory(PushPublishRTMPNetConnectionSession pushPublisherSession)
	{
		logger.debug("rtmpPushListener : onPushPublisherSessionDestory " + pushPublisherSession.toString());
	}
	public void onStreamCreate(PushPublishRTMPNetConnectionSession pushPublisherSession,com.wowza.wms.request.RequestFunction function,AMFDataList params)
    {
		logger.debug("rtmpPushListener : onStreamCreate , context = " + this.context );
    }
}	
