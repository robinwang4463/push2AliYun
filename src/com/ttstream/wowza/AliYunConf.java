package com.ttstream.wowza;

import java.util.Random;

import com.wowza.util.MD5DigestUtils;


/*
 * This is a config of AliCDN
 */
public class AliYunConf {
	
	
	static public String pushDomain = "push.haoxin.cn";
	
	static public String pullDomain = "pull.haoxin.cn";
	
	static public String rtmpPort = "1935";
	
	static public String appName = "live";
	
	static public String primaryKey = "";
	
	/*
	 * generate the dest streamName from the src streamName
	 */
	
	static public String generateStreamUri(String streamName){
		
		
		String streamUri = "";
		
	    String uid = "0";
	    
	    long expiredTime = System.currentTimeMillis()/1000 + 300;
	    
	    String timeStamp = String.valueOf(expiredTime);
	    
	    String rand = generateRandstr(6);
	    
	    String originMd5hash = "/"+appName+"/"+streamName+"-"+timeStamp+"-"+rand+"-"+uid+"-"+primaryKey;
		
	    String afterMd5hash = MD5DigestUtils.generateHash(originMd5hash);
		
	    streamUri = streamName+"?auth_key="+timeStamp+"-"+rand+"-"+uid+"-"+afterMd5hash;
	    
	    return streamUri;
		
	}
	
	static private String generateRandstr(int randLength){
	 
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
		int length = chars.length();
		Random rand  = null;
		String randstr = ""; 
        String randChar ="";
        
        for ( int i = 0; i < randLength; i++ ) 
        { 
        	rand= new Random();
    	    int j = rand.nextInt(length);
    	    randChar = chars.substring(j,j+1);
            randstr = randstr + randChar; 
        } 
        return randstr; 
    }

}