package com.ttstream.wowza;

import com.wowza.wms.amf.AMFPacket;
import com.wowza.wms.media.model.MediaCodecInfoAudio;
import com.wowza.wms.media.model.MediaCodecInfoVideo;
import com.wowza.wms.pushpublish.model.IPushPublish;
import com.wowza.wms.pushpublish.model.IPushPublishSession;
import com.wowza.wms.pushpublish.protocol.rtmp.PushPublishRTMP;
import com.wowza.wms.server.LicensingException;
import com.wowza.wms.stream.IMediaStream;
import com.wowza.wms.logging.WMSLogger;
import com.wowza.wms.logging.WMSLoggerFactory;
import com.wowza.wms.stream.IMediaStreamActionNotify3;
import com.wowza.wms.util.PushPublishUtils;

/*
 * This is a stream listener, In this case ,we just need to monitor the input live stream.
 */
public class StreamListener implements IMediaStreamActionNotify3{
		
	private WMSLogger logger = WMSLoggerFactory.getInstance().getLoggerObj(this.getClass().getName());
	
		
	
	public void onMetaData(IMediaStream stream, AMFPacket metaDataPacket)
	{
		
	}

	public void onPauseRaw(IMediaStream stream, boolean isPause, double location)
	{
		
	}

	public void onPause(IMediaStream stream, boolean isPause, double location)
	{
		
	}

	public void onPlay(IMediaStream stream, String streamName, double playStart, double playLen, int playReset)
	{
		
	}
	public void onSeek(IMediaStream stream, double location)
	{
		
	}

	public void onStop(IMediaStream stream)
	{
		
	}

	public void onCodecInfoAudio(IMediaStream stream,MediaCodecInfoAudio codecInfoAudio) {
		
	}

	public void onCodecInfoVideo(IMediaStream stream,MediaCodecInfoVideo codecInfoVideo) {
		
	}
	
	public void onPublish(IMediaStream stream, String streamName, boolean isRecord, boolean isAppend)
	{
	    logger.debug("StreamListener onPublish[" + stream.getContextStr() + "]: streamName : " + streamName);
		
	    /* if you call the method isTranscodeResult() to know whether the stream come from transcoder add or not
	    if (stream.isTranscodeResult()){
	    	
	    }
	    */
	    
	   	try{
	    	String dstStreamName = AliYunConf.generateStreamUri(streamName);
	    	pushToCdn(stream,streamName,AliYunConf.pushDomain,AliYunConf.rtmpPort,AliYunConf.appName,dstStreamName);
	    }catch(Exception e){
	    	logger.debug("StreamListener , meet exception when push stream to aliYun , streamName = "+streamName);
	    	e.printStackTrace();
	    }
	}
	
    
	public void onUnPublish(IMediaStream stream, String streamName, boolean isRecord, boolean isAppend)
	{
		logger.debug("StreamListener onUnPublish[" + stream.getContextStr() + "]: streamName:" + streamName);
		IPushPublishSession se = (IPushPublishSession)stream.getProperties().getProperty("IPushPublishSession");
		if(se == null){
			return;
		}
		
		IPushPublish publisher = se.getPushPublishImpl();
		publisher.disconnect(true);
		PushPublishUtils.notifySessionDestroy(streamName, stream, se, stream.getClient().getAppInstance());
	}
	
	
    private PushPublishRTMP pushToCdn(IMediaStream stream,String srcStreamName,String hostName,String port,String dstApp,String dstStreamName) throws LicensingException 
    {
    	logger.debug("StreamListener,prepare to pushToCdn, srcStreamName = "+srcStreamName +" dstStreamName = "+dstStreamName);
    	PushPublishRTMP publisher = new PushPublishRTMP();
    	// source stream
    	publisher.setAppInstance(stream.getClient().getAppInstance());
    	publisher.setSrcStreamName(srcStreamName);
    	
    	// dst stream
    	
    	publisher.setHostname(hostName);
    	publisher.setPort(port);
    	publisher.setDstApplicationName(dstApp);
    	publisher.setDstStreamName(dstStreamName);
    	//publisher.setWaitOnMetadataAvailable(false);
    	
    	publisher.setSendFCPublish(true);
    	publisher.setSendReleaseStream(false);
    	publisher.setDebugLog(true);
    	publisher.setHowToPublish("live");
    	//publisher.setRemoveDefaultAppInstance(true);
    	
    	//you can choice use to SSL
    	//publisher.setSSL(true);
    	String srcAppName = stream.getClient().getAppInstance().getApplication().getName();
    	publisher.addListener(new RtmpPushListener(srcAppName,srcStreamName,hostName,port,dstApp,dstStreamName));
    	
    	IPushPublishSession se = publisher.createPushPublishSession();
    	
    	PushPublishUtils.notifySessionCreate(srcStreamName, stream, se, stream.getClient().getAppInstance());
    	
    	stream.getProperties().setProperty("IPushPublishSession", se);    	
    	    	
    	publisher.connect();
    	
    	return publisher;
    }
	
}
